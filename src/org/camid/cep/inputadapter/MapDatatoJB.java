package org.camid.cep.inputadapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.camid.cep.datatype.DeviceData;
import org.camid.cep.datatype.DeviceEvent;
import org.camid.cep.datatype.GatewayHardware;
import org.camid.cep.datatype.GatewayService;
import org.camid.cep.datatype.RequestService;

import com.espertech.esper.client.EPRuntime;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapDatatoJB {
	public static EPRuntime epRuntime;
	
	private static Logger logger = Logger.getLogger(MapDatatoJB.class);  
	
//	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException{
//		MapDatatoJB( "{\"deviceId\":\"5042d537\",\"timestamp\":\"2017-05-15 22:00:59\",\"value\":40.70929799836529,\"dataType\":\"sensor\"}",  "DeviceData");
//	}
	public static void MapDatatoJB(String data, String Datatype) throws JsonParseException, JsonMappingException, IOException  {
		ObjectMapper mapper = new ObjectMapper();

		logger.info("Get Data for Subcribe data:"+data+" datatype:"+ Datatype);
		
		if(Datatype.equals("DeviceData")){
			DeviceData DeviceData = mapper.readValue(data, DeviceData.class);

			epRuntime.sendEvent(DeviceData);
			logger.info("Send Event to Engine:"+DeviceData.toString());
		}else if (Datatype.equals("DeviceEvent")){
			DeviceEvent DeviceEvent = mapper.readValue(data, DeviceEvent.class);

			epRuntime.sendEvent(DeviceEvent);
			logger.info("Send Event to Engine:"+DeviceEvent.toString());
			
			
		}else if(Datatype.equals("GatewayService")){
			GatewayService GatewayService = mapper.readValue(data, GatewayService.class);

			epRuntime.sendEvent(GatewayService);
			logger.info("Send Event to Engine:"+GatewayService.toString());
			
		}else if (Datatype.equals("GatewayHardware")){

			GatewayHardware GatewayHardware = mapper.readValue(data, GatewayHardware.class);

			epRuntime.sendEvent(GatewayHardware);
			logger.info("Send Event to Engine:"+GatewayHardware.toString());
		}else if (Datatype.equals("RequestService")){

			RequestService RequestService = mapper.readValue(data, RequestService.class);

			epRuntime.sendEvent(RequestService);
			logger.info("Send Event to Engine:"+RequestService.toString());
		}
		else{

			System.out.println("did't have this DataType");
			 logger.error("Tdid't have this DataType:"+ Datatype);  
		}
		
		
		
	}

	
}
