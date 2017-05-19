package org.camid.cep.main;

import java.io.InputStream;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.camid.cep.configure.EPLRegister;
import org.camid.cep.datatype.DeviceData;
import org.camid.cep.datatype.DeviceEvent;
import org.camid.cep.datatype.GatewayHardware;
import org.camid.cep.datatype.GatewayService;
import org.camid.cep.datatype.RequestService;
import org.camid.cep.inputadapter.MapDatatoJB;
import org.camid.cep.inputadapter.SensorMap;
import org.camid.cep.inputadapter.SubThread;
import org.camid.cep.jmx.EPServiceProviderJMX;
import org.camid.cep.jmx.ServerShellConstants;
import org.camid.cep.sqlite.DatabaseManager;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

public class CEPTestMain {

    private static Log log = LogFactory.getLog(CEPTestMain.class);
    private static MBeanServer mbs;
    private static EPServiceProvider engine;
	public static void main(String[] args) throws Exception {

		
		
		
		
		
//		ConfigLoader.loadAllConfig(CONF_PATH);
		DatabaseManager.setUpDatabase();
		DatabaseManager.getEPL();
		engine = EsperUnit();
		RMIUnit(engine);
		EPLRegister.EPLRegister(engine);

		MapDatatoJB.epRuntime = engine.getEPRuntime();
//		
		SubThread st = new SubThread("tcp://localhost:5563", "DeviceData");
		SubThread st1 = new SubThread("tcp://localhost:5563", "DeviceEvent");
		SubThread st2 = new SubThread("tcp://localhost:5563", "GatewayHardware");
		SubThread st3 = new SubThread("tcp://localhost:5563", "GatewayService");;
		SubThread st4 = new SubThread("tcp://localhost:5563", "RequestService");
		new Thread(st).start();
		new Thread(st1).start();
		new Thread(st2).start();
		new Thread(st3).start();
		new Thread(st4).start();
		
		
		
		
		
		
	}
	

	public static void RMIUnit(EPServiceProvider engine) throws Exception{
			log.info("Loading properties");
	        System.out.println("Loading properties");
	        Properties properties = new Properties();
	        InputStream propertiesIS = CEPTestMain.class.getClassLoader().getResourceAsStream(ServerShellConstants.CONFIG_FILENAME);
	        if (propertiesIS == null)
	        {
	            throw new RuntimeException("Properties file '" + ServerShellConstants.CONFIG_FILENAME + "' not found in classpath");
	        }
	        properties.load(propertiesIS);

	        // Start RMI registry
	        log.info("Starting RMI registry");
	        System.out.print("Starting RMI registry");
//	        int port = Integer.parseInt(properties.getProperty(ServerShellConstants.MGMT_RMI_PORT));
	        int port = 5554;
	        LocateRegistry.createRegistry(port);
	        System.out.println("-finish!");

	        // Obtain MBean server
	        log.info("Obtaining JMX server and connector");
	        System.out.print("Obtaining JMX server and connector");
	        MBeanServer mbs = MBeanServerFactory.createMBeanServer();
//	        String jmxServiceURL = properties.getProperty(ServerShellConstants.MGMT_SERVICE_URL);
	        
	        String jmxServiceURL = "service:jmx:rmi:///jndi/rmi://localhost:5554/server";
	        
//	        String jmxServiceURL = "service:jmx:rmi:///jndi/rmi://192.168.1.112:5554/server";
	        
	        JMXServiceURL jmxURL = new JMXServiceURL(jmxServiceURL);
	        JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(jmxURL, null, mbs);
	        cs.start();
	        System.out.println("-finish!");

	        
	        // Register MBean
	        log.info("Registering MBean");
	        System.out.print("Registering MBean");
	        ObjectName name = new ObjectName(ServerShellConstants.MGMT_MBEAN_NAME);
	        EPServiceProviderJMX mbean = new EPServiceProviderJMX(engine);
	        mbs.registerMBean(mbean, name);     
	        System.out.println("-finish!");
	        
	       
		}
	public static EPServiceProvider EsperUnit(){
		   log.info("Getting Esper engine instance");
	        System.out.print(" Getting Esper engine instance......");
	        Configuration configuration = new Configuration();
	        
	        configuration.addEventType("DeviceData", DeviceData.class.getName());
	        configuration.addEventType("DeviceEvent", DeviceEvent.class.getName());
	        configuration.addEventType("GatewayHardware", GatewayHardware.class.getName());
	        configuration.addEventType("GatewayService", GatewayService.class.getName());
	        configuration.addEventType("RequestService", RequestService.class.getName());
	        
	        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider(configuration);
	        
	        	    
	        log.info("Creating sample statement");
	        return engine;
	}
	
}
