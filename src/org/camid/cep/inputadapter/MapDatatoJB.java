package org.camid.cep.inputadapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.camid.cep.datatype.SensorData;

import com.espertech.esper.client.EPRuntime;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapDatatoJB {
	public static EPRuntime epRuntime;
	
	private static Logger logger = Logger.getLogger(MapDatatoJB.class);  
	public static void MapDatatoJB(String data, String Datatype) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		logger.info("Get Data for Subcribe data:"+data+" datatype:"+ Datatype);
		
		if(Datatype.equals("SensorData")){
			SensorData sensor = mapper.readValue(data, SensorData.class);
			SensorMap sensorMap = new SensorMap();
			sensorMap.setId(sensor.getId());
			sensorMap.setTimestamp(sensor.getTimestamp());
			sensorMap.setValue(Double.parseDouble(sensor.getValue()));
			
//			
//			Map<String, Object> sensorMap = new HashMap<String, Object>();
//			sensorMap.put("id", );
//			sensorMap.put("timestamp", sensor.getTimestamp());
//			sensorMap.put("value", Double.parseDouble(sensor.getValue()));

			epRuntime.sendEvent(sensorMap);
			logger.info("Send Event to Engine:"+sensorMap);
		}else{
			System.out.println("did't have this DataType");
			 logger.error("Tdid't have this DataType:"+ Datatype);  
		}
		
		
		
	}

	
}
