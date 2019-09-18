package xbmc.extentreports;

import java.io.File;




import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Detailedlog {
	private static final Logger log = Logger.getLogger(Detailedlog.class);
	
	private static Detailedlog Detailedlog_instance = null;
	
	//locked for calling
	private Detailedlog( ) { 
		try {
			String profle = "./Config/log4j.properties";
			PropertyConfigurator.configure(profle);
		} catch (Exception e) {
			System.out.println("Detailed log Constructor error.");
		}
		
}
	
	public static  Detailedlog getDetailedLogInstance() {
		if(Detailedlog_instance == null) {
			Detailedlog_instance =  new Detailedlog();
		}
		return Detailedlog_instance;
	}

	public  Logger log() {
	     return log;
		}
	
	

	
}
