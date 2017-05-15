package org.camid.cep.configure;

import java.util.List;

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
		List<EPLinformation> Epl=EPLUnitConfig.EPLinformation;
		for(int i=0;i<Epl.size();i++){
			jxmb.createEPL(Epl.get(i).getStatement(), Epl.get(i).getName(),Epl.get(i).getListener());
		}
		

		log.info("Register EPL Finish!");
	}

}
