/**************************************************************************************
 * Copyright (C) 2006-2015 EsperTech Inc. All rights reserved.                        *
 * http://www.espertech.com/esper                                                          *
 * http://www.espertech.com                                                           *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package org.camid.cep.jmx;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.camid.cep.listener.*;
import org.camid.cep.sqlite.DatabaseManager;
public class EPServiceProviderJMX implements EPServiceProviderJMXMBean
{
    private static Log log = LogFactory.getLog(EPServiceProviderJMX.class);
    private EPServiceProvider engine;

    public EPServiceProviderJMX(EPServiceProvider engine)
    {
        if (engine == null)
        {
            throw new IllegalArgumentException("No engine instance supplied");
        }
        this.engine = engine;
    }

    public void createEPL(String expression, String statementName)
    {
        log.error("Via Java Management JMX proxy: Creating statement '" + expression + "' named '" + statementName + "'");
       
        engine.getEPAdministrator().createEPL(expression, statementName);
    }

//    public void createEPL(String expression, String statementName, UpdateListener listener)
//    {
//        log.error("Via Java Management JMX proxy: Creating statement '" + expression + "' named '" + statementName + "'");
//        EPStatement stmt = engine.getEPAdministrator().createEPL(expression, statementName);
//        stmt.addListener(listener);
//    }
    
    public void createEPL(String expression, String statementName, String listener)
    {
        log.error("Via Java Management JMX proxy: Creating statement '" + expression + "' named '" + statementName + "'");
        EPStatement stmt = engine.getEPAdministrator().createEPL(expression, statementName);
        if(listener.equals("RestartDeviceListener")){

            stmt.addListener(new RestartDeviceListener());
        }else if(listener.equals("StartDeviceListener")){

            stmt.addListener(new StartDeviceListener());
        }else if(listener.equals("StopDeviceListener")){

            stmt.addListener(new StopDeviceListener());
        }else if(listener.equals("RestartDeviceListener")){

            stmt.addListener(new RestartDeviceListener());
        }
        DatabaseManager.insertEPL(statementName, expression, listener);
    }

    public void destroy(String statementName)
    {
        log.error("Via Java Management JMX proxy: Destroying statement '" + statementName + "'");
        engine.getEPAdministrator().getStatement(statementName).destroy();
        DatabaseManager.deleteEPL(statementName);
    }
}
