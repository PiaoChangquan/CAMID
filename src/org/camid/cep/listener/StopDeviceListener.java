package org.camid.cep.listener;

import java.io.IOException;

import org.camid.cep.controlfunction.ControlFunction;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class StopDeviceListener implements UpdateListener{

	@Override
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		// TODO Auto-generated method stub
		if (newEvents != null) {
			for (int i = 0; i < newEvents.length; i++) {
				EventBean event = newEvents[i];
				System.out.println("");
				System.out.println("Received NewEvents: " + event.getUnderlying());
//				System.out.println("Received NewEventsTypes: " + event.getEventType());
				String ss;
				try {
					ss = ControlFunction.StopDevice();
					System.out.println(ss);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (oldEvents != null) {
			for (int i = 0; i < oldEvents.length; i++) {
				EventBean event = oldEvents[i];
				System.out.println("Received OldEvents: " + event.getUnderlying());
			}
		}
	}
}