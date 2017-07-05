/*
 *    Copyright (c) Open Connectivity Foundation (OCF), AllJoyn Open Source
 *    Project (AJOSP) Contributors and others.
 *    
 *    SPDX-License-Identifier: Apache-2.0
 *    
 *    All rights reserved. This program and the accompanying materials are
 *    made available under the terms of the Apache License, Version 2.0
 *    which accompanies this distribution, and is available at
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 *    Copyright (c) Open Connectivity Foundation and Contributors to AllSeen
 *    Alliance. All rights reserved.
 *    
 *    Permission to use, copy, modify, and/or distribute this software for
 *    any purpose with or without fee is hereby granted, provided that the
 *    above copyright notice and this permission notice appear in all
 *    copies.
 *    
 *    THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL
 *    WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED
 *    WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE
 *    AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL
 *    DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR
 *    PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
 *    TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 *    PERFORMANCE OF THIS SOFTWARE.
*/
package org.allseen.lsf.sdk.manager;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultLightingSystemQueue implements LightingSystemQueue {

    private final DelayQueue<DelayedRunnable> delayQueue;
    private volatile Thread backgroundThread;
    private AtomicLong indexCounter;

    private class DelayedRunnable implements Runnable, Delayed {

        private final Runnable runnable;
        private final long expiration;
        private final long priority;

        public DelayedRunnable(Runnable runnable, int delay) {
            this.runnable = runnable;
            this.expiration = System.currentTimeMillis() + delay;
            this.priority = getNextIndex();
        }

        @Override
        public int compareTo(Delayed that) {
            // Long.compare is not available until 1.7
            return Long.valueOf(this.getDelay(TimeUnit.MILLISECONDS)).compareTo(Long.valueOf(that.getDelay(TimeUnit.MILLISECONDS)));
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(expiration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public void run() {
            runnable.run();
        }

        public long getPriority() {
            return priority;
        }
    }

    private class DelayedRunnablePriorityComparator implements Comparator<DelayedRunnable> {
        @Override
        public int compare(DelayedRunnable a, DelayedRunnable b) {
            return Long.valueOf(a.getPriority()).compareTo(b.getPriority());
        }
    }

    public DefaultLightingSystemQueue() {
        delayQueue = new DelayQueue<DelayedRunnable>();
        indexCounter = new AtomicLong(0);
        backgroundThread = new Thread() {
            PriorityQueue<DelayedRunnable> pQueue = new PriorityQueue<DelayedRunnable>(10, new DelayedRunnablePriorityComparator());

            @Override
            public void run() {
                while (backgroundThread != null) {
                    try {
                        DelayedRunnable task = null;

                        // grab all expired tasks (to run)
                        pQueue.add(task = delayQueue.take());
                        while ((task = delayQueue.poll()) != null) {
                            pQueue.add(task);
                        }

                        // run tasks in priority order
                        while ((task = pQueue.poll()) != null) {
                            task.run();
                        }
                    } catch (InterruptedException e){
                        // If stopped, we will drop out of the while loop
                    }
                }
            }};
        backgroundThread.start();
    }

    @Override
    public void post(Runnable runnable) {
        postDelayed(runnable, 0);
    }

    @Override
    public void postDelayed(Runnable runnable, int delay) {
        delayQueue.put(new DelayedRunnable(runnable, delay));
    }

    @Override
    public void stop() {
        Thread tempThread = backgroundThread;

        backgroundThread = null;
        tempThread.interrupt();
    }

    private long getNextIndex() {
        return indexCounter.getAndIncrement();
    }
}