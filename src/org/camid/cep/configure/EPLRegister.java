package org.camid.cep.configure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.camid.cep.inputadapter.MapDatatoJB;
import org.camid.cep.jmx.EPServiceProviderJMX;
import org.camid.cep.jmx.SampleStatement;

import com.espertech.esper.client.EPServiceProvider;

public class EPLRegister {

    private static Log log = LogFactory.getLog(EPLRegister.class);
	public static void EPLRegister(EPServiceProvider engine) {
		log.info("start register EPL");
		SampleStatement.createStatement(engine.getEPAdministrator());
		EPServiceProviderJMX jxmb = new EPServiceProviderJMX(engine);

		String expression = "select * from sensor(value<20)";
		jxmb.createEPL(expression, "sensor1c", "AggergationListener");

		MapDatatoJB.epRuntime = engine.getEPRuntime();

		log.info("Register EPL Finish!");
	}

}
