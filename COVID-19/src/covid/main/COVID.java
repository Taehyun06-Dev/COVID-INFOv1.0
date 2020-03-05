package covid.main;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import covid.gui.Logger;
import covid.util.Jsoup_Util;
import covid.util.Jsoup_Util2;
import covid.util.Tray_Util;

public class COVID {
	
	private COVID_DATA cd = new COVID_DATA();
	private Boolean debug = cd.getDebugStat();
	
	public void updateCOVID(Boolean type) throws InterruptedException, ExecutionException {
	    FutureTask<Integer> ju2 = new FutureTask<Integer>(new Jsoup_Util2());
		Thread t2 = new Thread(ju2); 
	    t2.start(); 
	    while(!ju2.isDone()) {
	    	if(debug == true) {
				new Logger().log_Info("Waiting for response...");
			}
	    	Thread.sleep(1000);
	    }
	    if(debug == true) {
			new Logger().log_Info("Successfully grabbed information!");
		}
	    Logger logger = new Logger();
	    COVID_DATA cd = new COVID_DATA();
	    logger.update_time();
	    cd.setLastUpdate();
	    if(ju2.get() != cd.getDataWhole()) {
	    	 new Tray_Util().Info_Warining2(ju2.get()-cd.getDataWhole());
	    	 cd.setDataWhole(ju2.get());
	    }
	    if(new COVID_DATA().getDataMap().size() == 0){	
	    	this.updateCOVID_SUB();
	    }
	    if(type == true) {
	    	new Tray_Util().Info_Succes();
	    }
	}	
	public void updateCOVID_SUB() throws InterruptedException, ExecutionException {
		if(debug == true) {
			new Logger().log_Info("Trying to update Data...");
		}
		FutureTask<Set<String>> ft = new FutureTask<Set<String>>(new Jsoup_Util());
		Thread t = new Thread(ft); 
	    t.start(); 
	    while(!ft.isDone()) {
	    	if(debug == true) {
				new Logger().log_Info("Waiting for response...");
			}
	    	Thread.sleep(1000);
	    }

	    Logger logger = new Logger();
	    COVID_DATA cd = new COVID_DATA();
	    logger.update_Map();
	    logger.update_time();
	    cd.setLastUpdate();
   	    if(ft.get().size() > 1) {
	    	 new Tray_Util().Info_Warining(ft.get());
   	    }
	}
	


	
}
