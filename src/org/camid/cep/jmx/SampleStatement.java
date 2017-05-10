package org.camid.cep.jmx;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class SampleStatement {
//	private static Log log = LogFactory.getLog(SampleStatement.class);
static String a = null;
	public static void createStatement(EPAdministrator admin) {
		
		EPStatement statement = admin.createEPL("select * from sensor(value>30)", "sensor");
		statement.addListener(new UpdateListener() {
		
			public void update(EventBean[] newEvents, EventBean[] oldEvents) {
				
				if (newEvents == null) {
					
					return;
				}
				
				for (int i = 0; i < newEvents.length; i++) {
					
					if (newEvents != null) {
						System.out.println("+++++++++++++++++++++++++"+newEvents[i].get("value")+"+++++++++++++++++++++++++++++++++ id: " + newEvents[i].get("id")
								+ " timestamp: " + newEvents[i].get("timestamp")+" Temp: "+newEvents[i].get("value")+" !!!!!!!!!!!!!!!!!!!!");
					a= "!!!!!!!!ID: "+newEvents[i].get("id")+" Timestamp: "+ newEvents[i].get("timestamp")+" Value: "+ newEvents[i].get("value")+"!!!!!!";
				System.out.println();
					}
				}
			}
		});
		
		
	}
	public static String getReurn() {
		String a2=a;
		a=null;
		return a2; 
		
	}
}

