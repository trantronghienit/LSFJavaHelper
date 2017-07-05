/******************************************************************************
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
******************************************************************************/

#import "LSFSDKAboutManager.h"
#import "LSFConstants.h"
#import "LSFSDKAllJoynManager.h"
#import "LSFLampAnnouncementData.h"
#include <alljoyn/AboutListener.h>
#include <alljoyn/BusAttachment.h>
#include <alljoyn/AboutProxy.h>

using namespace ajn;

class LSFSDKAboutManagerCpp : AboutListener {
public:
    LSFSDKAboutManagerCpp(BusAttachment *bus, id<LSFLampAnnouncementDelegate> lad);
    ~LSFSDKAboutManagerCpp();
    void RegisterAnnouncementHandler(void);
    void UnregisterAnnouncementHandler(void);
    void GetAboutData(const char* busName, uint16_t port);
    virtual void Announced(const char* busName, uint16_t version, SessionPort port, const MsgArg& objectDescriptionArg, const MsgArg& aboutDataArg);

private:
    ajn::BusAttachment *busAttachment;
    id<LSFLampAnnouncementDelegate> delegate;
};

LSFSDKAboutManagerCpp::LSFSDKAboutManagerCpp(ajn::BusAttachment *bus, id<LSFLampAnnouncementDelegate> lad) : busAttachment(bus), delegate(lad)
{
    busAttachment->EnableConcurrentCallbacks();
    busAttachment->AddMatch("sessionless='t',type='error'");
}

LSFSDKAboutManagerCpp::~LSFSDKAboutManagerCpp()
{
    if (busAttachment) {
        delete busAttachment;
        busAttachment = NULL;
    }

    delegate = nil;
}

void LSFSDKAboutManagerCpp::RegisterAnnouncementHandler(void)
{
    LSFConstants *constants = [LSFConstants getConstants];

    NSArray *lampInterfaces = [[NSArray alloc] initWithObjects: constants.lampStateInterfaceName, constants.lampDetailsInterfaceName, constants.lampParametersInterfaceName, constants.lampServiceInterfaceName, nil];
    NSArray *controllerInterfaces = [[NSArray alloc] initWithObjects: constants.controllerServiceInterfaceName, constants.controllerServiceLampInterfaceName, constants.controllerServiceLampGroupInterfaceName, constants.controllerServicePresetInterfaceName, constants.controllerServiceSceneInterfaceName, constants.controllerServiceMasterSceneInterfaceName, constants.configServiceInterfaceName, constants.aboutInterfaceName, nil];

    unsigned long lengthOfLampsArray = [lampInterfaces count];
    const char **listLampInterfaces = new const char*[lengthOfLampsArray];
    for (int i = 0; i < lengthOfLampsArray; i++) {
        NSString *interfaceName = [lampInterfaces objectAtIndex: i];
        listLampInterfaces[i] = [interfaceName UTF8String];
    }

    unsigned long lengthOfControllerArray = [controllerInterfaces count];
    const char **listControllerInterfaces = new const char*[lengthOfControllerArray];
    for (int i = 0; i < lengthOfControllerArray; i++) {
        NSString *interfaceName = [controllerInterfaces objectAtIndex: i];
        listControllerInterfaces[i] = [interfaceName UTF8String];
    }

    QStatus status;
    busAttachment->RegisterAboutListener(*this);

    status = busAttachment->WhoImplements(listLampInterfaces, lengthOfLampsArray);
    if (status != ER_OK) {
        NSLog(@"Failed on WhoImplements lamp interfaces");
    }

    status = busAttachment->WhoImplements(listControllerInterfaces, lengthOfControllerArray);
    if (status != ER_OK) {
        NSLog(@"Failed on WhoImplements controller interfaces");
    }

    delete [] listLampInterfaces;
    delete [] listControllerInterfaces;
    listLampInterfaces = NULL;
    listControllerInterfaces = NULL;
}

void LSFSDKAboutManagerCpp::UnregisterAnnouncementHandler(void)
{
    LSFConstants *constants = [LSFConstants getConstants];

    NSArray *lampInterfaces = [[NSArray alloc] initWithObjects: constants.lampStateInterfaceName, constants.lampDetailsInterfaceName, constants.lampParametersInterfaceName, constants.lampServiceInterfaceName, nil];
    NSArray *controllerInterfaces = [[NSArray alloc] initWithObjects: constants.controllerServiceInterfaceName, constants.controllerServiceLampInterfaceName, constants.controllerServiceLampGroupInterfaceName, constants.controllerServicePresetInterfaceName, constants.controllerServiceSceneInterfaceName, constants.controllerServiceMasterSceneInterfaceName, constants.configServiceInterfaceName, constants.aboutInterfaceName, nil];

    unsigned long lengthOfLampsArray = [lampInterfaces count];
    const char **listLampInterfaces = new const char*[lengthOfLampsArray];
    for (int i = 0; i < lengthOfLampsArray; i++) {
        NSString *interfaceName = [lampInterfaces objectAtIndex: i];
        listLampInterfaces[i] = [interfaceName UTF8String];
    }

    unsigned long lengthOfControllerArray = [controllerInterfaces count];
    const char **listControllerInterfaces = new const char*[lengthOfControllerArray];
    for (int i = 0; i < lengthOfControllerArray; i++) {
        NSString *interfaceName = [controllerInterfaces objectAtIndex: i];
        listControllerInterfaces[i] = [interfaceName UTF8String];
    }

    QStatus status;
    busAttachment->UnregisterAboutListener(*this);

    status = busAttachment->CancelWhoImplements(listLampInterfaces, lengthOfLampsArray);
    if (status != ER_OK) {
        NSLog(@"Failed on CancelWhoImplements lamp interfaces");
    }

    status = busAttachment->CancelWhoImplements(listControllerInterfaces, lengthOfControllerArray);
    if (status != ER_OK) {
        NSLog(@"Failed on CancelWhoImplements controller interfaces");
    }

    delete [] listLampInterfaces;
    delete [] listControllerInterfaces;
    listLampInterfaces = NULL;
    listControllerInterfaces = NULL;
}

void LSFSDKAboutManagerCpp::GetAboutData(const char* busName, uint16_t port)
{
    SessionId sessionID = 0;

    SessionOpts opts;
    opts.transports &= (~TRANSPORT_UDP);
    opts.isMultipoint = true;

    QStatus status = busAttachment->JoinSession(busName, port, NULL, sessionID, opts);
    if (status != ER_OK) {
        NSLog(@"JoinSession failed (status=%s)", QCC_StatusText(status));
    } else {
        NSLog(@"Successfully joined session with %s on port %u and SessionID = %u", busName, port, sessionID);

        MsgArg aboutArg;
        AboutProxy aboutProxy(*busAttachment, busName, sessionID);

        QStatus status = ER_OK;
        for (int i = 0; i < 5; i++) {
            status = aboutProxy.GetAboutData("en", aboutArg);

            if (status != ER_OK) {
                NSLog(@"Failed attempt (%i) to get about data from lamp (%s). Retrying.", i, busName);
            } else {
                NSLog(@"Breaking on attemp %i", i);
                break;
            }
        }

        if (status != ER_OK) {
            NSLog(@"Failed 5 attempts to get about data from lamp (%s)", busName);
        } else {
            LSFLampAbout *myAboutData = [[LSFLampAbout alloc] init];

            QStatus status;
            char* stringContent;
            AboutData aboutData(aboutArg);

            //AppID
            NSMutableData *appIDData;
            uint8_t *AppIdBuffer;
            size_t numElements;
            status = aboutData.GetAppId(&AppIdBuffer, &numElements);
            appIDData = [NSMutableData dataWithBytes: AppIdBuffer length: (NSInteger)numElements];
            myAboutData.appID = [[appIDData description] stringByTrimmingCharactersInSet: [NSCharacterSet characterSetWithCharactersInString: @"<>"]];

            //DefaultLanguage
            status = aboutData.GetDefaultLanguage(&stringContent);
            myAboutData.defaultLanguage = [NSString stringWithUTF8String: stringContent];

            //DeviceName
            status = aboutData.GetDeviceName(&stringContent);
            myAboutData.deviceName = [NSString stringWithUTF8String: stringContent];

            //DeviceID
            status = aboutData.GetDeviceId(&stringContent);
            myAboutData.deviceID = [NSString stringWithUTF8String: stringContent];

            //AppName
            status = aboutData.GetAppName(&stringContent);
            myAboutData.appName = [NSString stringWithUTF8String: stringContent];

            //Manufacturer
            status = aboutData.GetManufacturer(&stringContent);
            myAboutData.manufacturer = [NSString stringWithUTF8String: stringContent];

            //ModelNumber
            status = aboutData.GetModelNumber(&stringContent);
            myAboutData.modelNumber = [NSString stringWithUTF8String: stringContent];

            //SupportedLanguages
            NSMutableString *supportedLanguages = [[NSMutableString alloc] initWithString: @""];
            size_t numLanguages = aboutData.GetSupportedLanguages();
            NSLog(@"%lu Languages are supported", numLanguages);
            const char **supportedLangaugesArray = new const char*[numLanguages];
            aboutData.GetSupportedLanguages(supportedLangaugesArray, numLanguages);

            for (int i = 0; i < numLanguages; i++) {
                const char* suppLang = supportedLangaugesArray[i];
                NSString *supportedLanguage = [NSString stringWithUTF8String: suppLang];

                if (i == (numLanguages - 1)) {
                    [supportedLanguages appendFormat: @"%@", supportedLanguage];
                } else {
                    [supportedLanguages appendFormat: @"%@, ", supportedLanguage];
                }
            }

            NSLog(@"Final Supported Languages = %@", supportedLanguages);
            myAboutData.supportedLanguages = [NSString stringWithString: supportedLanguages];
            delete [] supportedLangaugesArray;

            //Description
            status = aboutData.GetDescription(&stringContent);
            myAboutData.description = [NSString stringWithUTF8String: stringContent];

            //DateOfManufacture
            status = aboutData.GetDateOfManufacture(&stringContent);
            myAboutData.dateOfManufacture = [NSString stringWithUTF8String: stringContent];

            //SoftwareVersion
            status = aboutData.GetSoftwareVersion(&stringContent);
            myAboutData.softwareVersion = [NSString stringWithUTF8String: stringContent];

            //AJSoftwareVersion
            status = aboutData.GetAJSoftwareVersion(&stringContent);
            myAboutData.ajSoftwareVersion = [NSString stringWithUTF8String: stringContent];

            //HardwareVersion
            status = aboutData.GetHardwareVersion(&stringContent);
            myAboutData.hardwareVersion = [NSString stringWithUTF8String: stringContent];

            //SupportUrl
            status = aboutData.GetSupportUrl(&stringContent);
            myAboutData.supportURL = [NSString stringWithUTF8String: stringContent];

            //Leave session
            status = busAttachment->LeaveSession(sessionID);
            if (status != ER_OK) {
                NSLog(@"Failed to leave session %u with %s", sessionID, busName);
            } else {
                NSLog(@"Successfully left session %u with %s", sessionID, busName);
            }

            if (delegate) {
                [delegate saveAboutData: myAboutData];
            }
        }
    }
}

void LSFSDKAboutManagerCpp::Announced(const char* busName, uint16_t version, SessionPort port, const MsgArg& objectDescriptionArg, const MsgArg& aboutDataArg)
{
    NSLog(@"LSFAboutManagerCpp Announced(): version=%u, port=%u, busName=%s", version, port, busName);

    NSString *lampObjectPath = [[LSFConstants getConstants] lampServiceObjectDescription];

    AboutObjectDescription objectDescs(objectDescriptionArg);

    if (objectDescs.HasPath([lampObjectPath UTF8String])) {
        NSLog(@"Received lamp announcment");

        AboutData aboutData(aboutDataArg);
        char* uniqueId;
        aboutData.GetDeviceId(&uniqueId);

        char* uniqueName;
        aboutData.GetDeviceName(&uniqueName);

        NSString *lampID = [NSString stringWithUTF8String: uniqueId];
        NSString *myBusName = [NSString stringWithUTF8String: busName];
        NSLog(@"lampID = %@", lampID);
        NSLog(@"myBusName = %@", myBusName);

        if (delegate) {
            [delegate lampAnnouncementReceivedFromLamp: lampID onBus: myBusName onPort: port];
        }
    } else {
        NSLog(@"Received some other about announcment\n");
    }
}

@interface LSFSDKAboutManager() {
    LSFSDKAboutManagerCpp *aboutManagerCpp;
}

-(void)getAboutDataFrom: (NSString *)peerName onPort: (unsigned int)port;

@end

@implementation LSFSDKAboutManager

-(id)initWithBusAttachment: (BusAttachment *)bus
{
    self = [super init];

    if (self)
    {
        aboutManagerCpp = new LSFSDKAboutManagerCpp(bus, self);
    }

    return self;
}

-(void)registerAnnouncementHandler
{
    NSLog(@"LSFAboutManager - registerAnnouncementHandler() executing");
    aboutManagerCpp->RegisterAnnouncementHandler();
}

-(void)unregisterAnnouncementHandler
{
    NSLog(@"LSFAboutManager - unregisterAnnouncementHandler() executing");
    aboutManagerCpp->UnregisterAnnouncementHandler();
}

-(void)getAboutDataFromBusName: (NSString *)busName onPort: (unsigned int)port
{
    [self getAboutDataFrom:busName onPort:port];
}

/*
 * LampAnnouncementDelegate Implementation
 */
-(void)lampAnnouncementReceivedFromLamp: (NSString *)lampID onBus: (NSString *)busName onPort: (uint16_t)port
{
    LSFLampAnnouncementData *lampAnnData = [[LSFLampAnnouncementData alloc] initPort: port busName: busName];

    dispatch_async(dispatch_queue_create("GetAboutData", NULL), ^{
        [LSFSDKAllJoynManager addNewLamp: lampID lampAnnouncementData: lampAnnData];
        [[LSFSDKAllJoynManager getLampManagerCallback] postGetLampQueriedAboutDataForID: lampID];
    });
}

-(void)saveAboutData: (LSFLampAbout *)myAboutData
{
    [[LSFSDKAllJoynManager getLampManagerCallback] postUpdateLampID: myAboutData.deviceID withAboutData: myAboutData andDelay: 0];
}

/*
 * Private Functions
 */
-(void)getAboutDataFrom: (NSString *)peerName onPort: (unsigned int)port
{
    aboutManagerCpp->GetAboutData([peerName UTF8String], port);
}

@end