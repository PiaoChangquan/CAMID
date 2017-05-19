package org.camid.cep.inputadapter;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import com.espertech.esper.client.EPRuntime;

public class SubThread  implements Runnable{
	private static Logger logger = Logger.getLogger(SubThread.class); 
	
	String URL;
	String DataType;
	int i =0;
//	EPRuntime epRuntime;
	public SubThread(String URL,String DataType ){
		this.URL=URL;
		this.DataType = DataType;
	}
	
	
	@Override
	public void run() {
		
        Context context = ZMQ.context(1);
        Socket subscriber = context.socket(ZMQ.SUB);

        subscriber.connect(URL);
        subscriber.subscribe(DataType.getBytes());
        
        logger.info("write the data which datatype is :"+ DataType);
        System.out.println(i++);
		// TODO Auto-generated method stub
		System.out.println("finish!");
		
        while (!Thread.currentThread ().isInterrupted ()) {
            String address = subscriber.recvStr ();
            String contents = subscriber.recvStr ();
//           	System.out.println("get data Start");
//            System.out.println(contents);
            logger.info("Get Data for ZeroMQ data:"+contents+" datatype:"+ DataType);
            try {
				MapDatatoJB.MapDatatoJB(contents,DataType);
				logger.info("send to map data to java bean");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
        }
        subscriber.close ();
        context.term ();
	}
	

}
