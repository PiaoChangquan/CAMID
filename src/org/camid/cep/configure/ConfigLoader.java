package org.camid.cep.configure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigLoader {
	private static Log logger = LogFactory.getLog(ConfigLoader.class);
	private static ObjectMapper mapper;
	private static JsonNode rootNode;

	public static void loadAllConfig(String filePath) {
		logger.trace("Start loadAllConfig Method");

		logger.debug("Load Configuration from " + filePath);

		setLoader(filePath);

		loadCsnPodConfig();
		loadEplConfig();
		loadDataStreamConfig();
		loadCommConfig();

		logger.trace("End loadAllConfig Method");
	}

	public static void setLoader(String filePath) {
		logger.trace("Start setLoader Method");

		logger.info("Load the " + filePath + "+ configuration file");
		mapper = new ObjectMapper();
		try {
			rootNode = mapper.readTree(new File(filePath));
		} catch (IOException e) {
			logger.error("Can't load file: " + filePath);
			logger.error("Error: " + e.toString());
		}

		logger.trace("End setLoader Method");
	}

	public static void loadEplConfig() {
		logger.trace("Start loadSensorConfig Method");

		JsonNode eplUnitNode = rootNode.path("epl_unit");
		int eplCount = eplUnitNode.path("count").intValue();
		// SensorUnitConfig.snsrCount = sensorUnitNode.path("count").intValue();

		if (eplCount > 0) {
			JsonNode statementsListNode = eplUnitNode.path("statements");

			List<EPLinformation> EplConfig = new ArrayList<EPLinformation>();
			for (JsonNode eplNode : statementsListNode) {
				EPLinformation epl = new EPLinformation(eplNode.path("statement_name").textValue(),
						eplNode.path("expression").textValue(), eplNode.path("listener_name").textValue());
				EplConfig.add(epl);
				if (epl.getListener().length() == 0) {
					System.out.println("listener null");
				}
				System.out.println(epl.toString());
			}
			EPLUnitConfig.EPLinformation= EplConfig;
		}
	}

	private static void loadCommConfig() {
		// TODO Auto-generated method stub

	}

	private static void loadDataStreamConfig() {
		// TODO Auto-generated method stub

	}

	private static void loadCsnPodConfig() {
		// TODO Auto-generated method stub

	}

}
