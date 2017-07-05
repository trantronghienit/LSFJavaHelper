package org.alljoyn.ioe.controlpanelbrowser;
/******************************************************************************
* Copyright (c) 2013-2014, AllSeen Alliance. All rights reserved.
*
*    Permission to use, copy, modify, and/or distribute this software for any
*    purpose with or without fee is hereby granted, provided that the above
*    copyright notice and this permission notice appear in all copies.
*
*    THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
*    WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
*    MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
*    ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
*    WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
*    ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
*    OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
******************************************************************************/
import java.util.Collection;
import java.util.Locale;

import org.alljoyn.ioe.controlpaneladapter.ControlPanelAdapter;
import org.alljoyn.ioe.controlpaneladapter.ControlPanelExceptionHandler;
import org.alljoyn.ioe.controlpanelservice.ControlPanelCollection;
import org.alljoyn.ioe.controlpanelservice.ControlPanelException;
import org.alljoyn.ioe.controlpanelservice.ControlPanelService;
import org.alljoyn.ioe.controlpanelservice.ControllableDevice;
import org.alljoyn.ioe.controlpanelservice.DeviceEventsListener;
import org.alljoyn.ioe.controlpanelservice.Unit;
import org.alljoyn.ioe.controlpanelservice.ui.AlertDialogWidget;
import org.alljoyn.ioe.controlpanelservice.ui.ContainerWidget;
import org.alljoyn.ioe.controlpanelservice.ui.ControlPanelEventsListener;
import org.alljoyn.ioe.controlpanelservice.ui.DeviceControlPanel;
import org.alljoyn.ioe.controlpanelservice.ui.UIElement;
import org.alljoyn.ioe.controlpanelservice.ui.UIElementType;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A fragment representing a single Appliance detail screen. This fragment is
 * either contained in a {@link DeviceListActivity} in two-pane mode (on
 * tablets) or a {@link DeviceDetailActivity} on handsets.
 */
public class DeviceDetailFragment extends Fragment {
	/**
	 * For logging
	 */
	private final static String TAG = "cpappApplianceDetailFragment";
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private DeviceList.DeviceContext deviceContext;

	/**
	 * The device controller this fragment is presenting.
	 */
	private DeviceController deviceController;
	private View rootView;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public DeviceDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			deviceContext = getArguments().getParcelable(ARG_ITEM_ID);
			
			// get the controllable device
			try {
				
				ControllableDevice controllableDevice = ControlPanelService.getInstance().getControllableDevice(deviceContext.getDeviceId(), deviceContext.getBusName());

				if (controllableDevice != null) {
					for (String objPath: deviceContext.getBusObjects()) {
						controllableDevice.addControlPanel(objPath, deviceContext.getInterfaces(objPath));
					}
					deviceController = new DeviceController(controllableDevice);
					deviceController.start();
				}
			} catch (ControlPanelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		rootView = inflater.inflate(R.layout.fragment_device_detail,
				container, false);

		return rootView;
	}


	@Override
	public void onDestroy()
	{
		if (deviceController != null) {
			deviceController.stop();
		}
		super.onDestroy();
	}

	class DeviceController implements DeviceEventsListener, ControlPanelExceptionHandler, ControlPanelEventsListener
	{
		final ControllableDevice device;
		private DeviceControlPanel deviceControlPanel;
		private ControlPanelAdapter controlPanelAdapter;
		private AlertDialog alertDialog;
		
		DeviceController(ControllableDevice controllableDevice)
		{
			this.device = controllableDevice; 
		}
		
		public void start() throws ControlPanelException
		{
			try {
				Log.d(TAG, "Starting the session with the device");
				if (device != null)
					device.startSession(this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void stop()
		{
			try {
				Log.d(TAG, "Releasing the device control panel");
				if (deviceControlPanel != null) {
					deviceControlPanel.release();
				}
				Log.d(TAG, "Stopping the session with the device");
				if (device != null) {
					device.endSession();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void sessionLost(final ControllableDevice device) {
			if (this.device.getDeviceId().equalsIgnoreCase(device.getDeviceId())) {
				getActivity().runOnUiThread(new Runnable(){
					@Override
					public void run()
					{
						String text = "Received SESSION_LOST for device: '" + device.getDeviceId() + "'";
						Log.d(TAG, text);
						Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
					}});
			}
		}
		
		@Override
		public void sessionEstablished(final ControllableDevice device,
				java.util.Collection<ControlPanelCollection> controlPanelContainer)
		{
			Log.d(TAG, "Received sessionEstablished for device: '" + device.getDeviceId() + "'");
			//		public void sessionEstablished(ControllableDevice device, Collection<DeviceControlPanel> controlPanelContainer)
			//		{

			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					selectControlPanel(device);
				}
				
			});

		}

		private void onControlPanelSelected() {
			try {

				UIElement rootContainerElement = deviceControlPanel.getRootElement(this);

				if ( rootContainerElement == null ) {
					Log.e(TAG, "RootContainerElement wasn't created!!! Can't continue");
					return;
				}

				controlPanelAdapter = new ControlPanelAdapter(getActivity(), this);

				UIElementType elementType = rootContainerElement.getElementType();
				Log.d(TAG, "Found root container of type: '" + elementType + "'");

				if ( elementType == UIElementType.CONTAINER ) {
					ContainerWidget container = ((ContainerWidget)rootContainerElement);
					/* create an android view for the abstract container */
					final View adapterView = controlPanelAdapter.createContainerView(container);

					getActivity().runOnUiThread(new Runnable(){
						@Override
						public void run()
						{
							if (rootView != null) {
								LinearLayout body = (LinearLayout) rootView.findViewById(R.id.control_panel);
								body.removeAllViews();
								body.addView(adapterView);
							}
						}});
				}
				else if ( elementType == UIElementType.ALERT_DIALOG ) {
					AlertDialogWidget alertDialogWidget = ((AlertDialogWidget)rootContainerElement);
					AlertDialog alertDialog = controlPanelAdapter.createAlertDialog(alertDialogWidget);
					alertDialog.setCancelable(false);
					alertDialog.setCanceledOnTouchOutside(false);
					alertDialog.setOnDismissListener(new AlertDialog.OnDismissListener() {

						@Override
						public void onDismiss(DialogInterface arg0) {
							String text = "Dialog dismissed.";
							Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
							Log.d(TAG, text);
						}
					});
					alertDialog.show();
				}
			}//try
			catch(ControlPanelException cpe) {
				String errMsg = "Failed to access remote methods of control panel, Error: '" + cpe.getMessage() + "'";
				Log.e(TAG, errMsg);
				final TextView returnView = new TextView(getActivity());
				returnView.setText(errMsg);
				getActivity().runOnUiThread(new Runnable(){
					@Override
					public void run()
					{
						if (rootView != null) {
							LinearLayout body = (LinearLayout) rootView.findViewById(R.id.control_panel);
							body.removeAllViews();
							body.addView(returnView);
						}
					}});
				return;
			}
		}	

		private void selectControlPanel(ControllableDevice device) {
			if (rootView != null) {
				Spinner unitSelector = (Spinner) rootView.findViewById(R.id.unit_selector);
				Collection<Unit> unitCollection = device.getUnitCollection();
				if (unitCollection.size() == 0) {
					Log.w(TAG, "No units found");
					unitSelector.setEnabled(false);
				} else {
					final ArrayAdapter<LabelValuePair> adapter = new ArrayAdapter<LabelValuePair>(getActivity(), android.R.layout.simple_spinner_item);
					for (Unit unit: unitCollection) {
						adapter.add(new LabelValuePair(unit.getUnitId(), unit));
					}
					unitSelector.setAdapter(adapter);
					
					if (unitCollection.size() == 1) {
						unitSelector.setEnabled(false);
						onUnitSelection(unitCollection.iterator().next());
					} else {
					// register a selection listener
					OnItemSelectedListener listener = new OnItemSelectedListener() {
						int currentSelection = 1000;
						public void onItemSelected(AdapterView<?> parent, View view, final int pos, long id) {
							if (pos == currentSelection) {
								Log.d(TAG, String.format("Selected position %d already selected. No action required", pos));
							} else {
								currentSelection = pos;
								LabelValuePair item = adapter.getItem(pos);
								Unit selectedUnit = (Unit) item.value;
								onUnitSelection(selectedUnit);
							}
						}

						public void onNothingSelected(AdapterView<?> parent) {
							// Another interface callback
						}
					};
					unitSelector.setOnItemSelectedListener(listener);
					}
				}
			}
		}

		private void onUnitSelection(Unit selectedUnit) {
			Log.d(TAG, String.format("Unit selected: '%s'", selectedUnit.getUnitId()));
			
			Collection<ControlPanelCollection> controlPanelContainer = selectedUnit.getControlPanelCollection();
			
			selectControlPanelCollection(controlPanelContainer);
		}

		private void selectControlPanelCollection(
				Collection<ControlPanelCollection> controlPanelContainer) {
			if (rootView != null) {
				Spinner cpCollectionSelector = (Spinner) rootView.findViewById(R.id.cp_collection_selector);
				if (controlPanelContainer.size() == 0) {
					Log.w(TAG, "No control panel collections found");
					cpCollectionSelector.setEnabled(false);
				} else {
					final ArrayAdapter<LabelValuePair> adapter = new ArrayAdapter<LabelValuePair>(getActivity(), android.R.layout.simple_spinner_item);
					for (ControlPanelCollection cpCollection: controlPanelContainer) {
						adapter.add(new LabelValuePair(cpCollection.getName(), cpCollection));
					}
					cpCollectionSelector.setAdapter(adapter);
					
					if (controlPanelContainer.size() == 1) {
						cpCollectionSelector.setEnabled(false);
						onControlPanelCollectionSelection(controlPanelContainer.iterator().next());
					} else {
					// register a selection listener
					OnItemSelectedListener listener = new OnItemSelectedListener() {
						int currentSelection = 1000;
						public void onItemSelected(AdapterView<?> parent, View view, final int pos, long id) {
							if (pos == currentSelection) {
								Log.d(TAG, String.format("Selected position %d already selected. No action required", pos));
							} else {
								currentSelection = pos;
								LabelValuePair item = adapter.getItem(pos);
								ControlPanelCollection cpCollection = (ControlPanelCollection) item.value;
								onControlPanelCollectionSelection(cpCollection);
							}
						}

						public void onNothingSelected(AdapterView<?> parent) {
							// Another interface callback
						}
					};
					cpCollectionSelector.setOnItemSelectedListener(listener);
					}
				}
			}
		}

		private void onControlPanelCollectionSelection(ControlPanelCollection controlPanelCollection) {
			Collection<DeviceControlPanel> controlPanels = controlPanelCollection.getControlPanels();
			String language_IETF_RFC5646_java = Locale.getDefault().toString(); //"en_US", "es_SP"
			String language_IETF_RFC5646 = language_IETF_RFC5646_java.replace('_', '-');
			String languageISO639 = Locale.getDefault().getLanguage(); //"en", "es"
			DeviceControlPanel previousControlPanel = deviceControlPanel;
			boolean found = false;
			for(DeviceControlPanel controlPanel : controlPanels) {
				String cpLanugage = controlPanel.getLanguage();
				Log.d(TAG, String.format("Control Panel language: %s", cpLanugage));
				if (cpLanugage.equalsIgnoreCase(language_IETF_RFC5646) 
						|| cpLanugage.equalsIgnoreCase(languageISO639)
						// phone language=de_DE (de), cp language=de_AT 
						|| cpLanugage.startsWith(languageISO639))
				{
					deviceControlPanel = controlPanel;
					found = true;
					Log.d(TAG, String.format("Found a control panel that matches phone languages: RFC5646=%s, ISO639=%s, Given language was: %s", language_IETF_RFC5646, languageISO639, cpLanugage));
					break;
				}
			}
			if (!found  && !controlPanels.isEmpty())
			{
				Log.w(TAG, String.format("Could not find a control panel that matches phone languages: RFC5646=%s, ISO639=%s", language_IETF_RFC5646, languageISO639));
				deviceControlPanel =  controlPanels.iterator().next();
				Log.d(TAG, String.format("Defaulting to the control panel of language: %s", deviceControlPanel.getLanguage()));
			}

			Log.d(TAG, "Releasing the previous device control panel");
			if (previousControlPanel != null) {
				previousControlPanel.release();
			}
			onControlPanelSelected();
		}

		public void metadataChanged(ControllableDevice device, final UIElement uielement) {
			UIElementType elementType = uielement.getElementType();
			Log.d(TAG, "METADATA_CHANGED : Received metadata changed signal, device: '" + device.getDeviceId() + "', ObjPath: '" + uielement.getObjectPath() + "', element type: '" + elementType + "'");
			FragmentActivity activity = getActivity();
			if (activity != null) {
				activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						controlPanelAdapter.onMetaDataChange(uielement);
					}
				});
			}
		}
		
		@Override
		public void errorOccurred(ControllableDevice device, final String reason)
		{
			final String text = "Error: '" + reason + "'";
			Log.e(TAG, text);
			if (this.device.getDeviceId().equalsIgnoreCase(device.getDeviceId())) {
				final FragmentActivity activity = getActivity();
				if (activity != null) {
					activity.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(activity, text, Toast.LENGTH_LONG).show();
						}
					});
				}
			}
		}

		@Override
		public void handleControlPanelException(ControlPanelException e) {
			String text = getString(R.string.action_failed);
			Log.e(TAG, text + ", error in calling remote object: '" + e.getMessage() + "'");
			Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
		}
		
		@Override
		public void errorOccurred(DeviceControlPanel deviceControlPanel, String reason) {
			errorOccurred(deviceControlPanel.getDevice(), reason);
		}

		@Override
		public void metadataChanged(DeviceControlPanel deviceControlPanel, final UIElement uielement) {
			Log.d(TAG, "Received metadataChanged signal, device: '" + deviceControlPanel.getDevice().getDeviceId() + "', ObjPath: '" + uielement.getObjectPath() + "'");
			if (device.getDeviceId().equalsIgnoreCase(deviceControlPanel.getDevice().getDeviceId())) {
				UIElementType elementType = uielement.getElementType();
				Log.d(TAG, "Received metadataChanged : Received metadata changed signal, device: '" + device.getDeviceId() + "', ObjPath: '" + uielement.getObjectPath() + "', element type: '" + elementType + "'");
				FragmentActivity activity = getActivity();
				if (activity != null) {
					activity.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							controlPanelAdapter.onMetaDataChange(uielement);
						}
					});
				}
			}
		}

		@Override
		public void valueChanged(DeviceControlPanel deviceControlPanel, final UIElement uielement, final Object newValue) {
			Log.d(TAG, "Received valueChanged signal, device: '" + deviceControlPanel.getDevice().getDeviceId() + "', ObjPath: '" + uielement.getObjectPath() + "', NewValue: '" + newValue + "'");
			if (device.getDeviceId().equalsIgnoreCase(deviceControlPanel.getDevice().getDeviceId())) {
				if (controlPanelAdapter != null) {

					final FragmentActivity activity = getActivity();
					if (activity != null) {
						activity.runOnUiThread(new Runnable() {
							@Override
							public void run() {
								controlPanelAdapter.onValueChange(uielement,
										newValue);
								String text = "Received value changed signal, ObjPath: '"
										+ uielement.getObjectPath()
										+ "', NewValue: '" + newValue + "'";
								Toast.makeText(activity, text,
										Toast.LENGTH_SHORT).show();
							}
						});
					}
				}
			}
		}

		@Override
		public void notificationActionDismiss(DeviceControlPanel deviceControlPanel) {
			
			Log.d(TAG,"Received notificationActionDismiss");
			if (alertDialog != null && alertDialog.isShowing()) {
				Log.d(TAG,"Dismissing the dialog");
				alertDialog.dismiss(); 
			}
		}
		
	};

	/**
	 * A wrapper class for hosting a {label,value} pair inside an ArrayAdapter.
	 * So that the label is displayed, while practically the real value is used.
	 */
	class LabelValuePair {

		final String label;
		final Object value;

		public LabelValuePair(String label, Object value) {
			super();
			this.value = value;
			this.label = label;
		}

		@Override
		// This does the trick of displaying the label and not the value in the Adapter
		public String toString() {
			return label;
		}
	}


}
