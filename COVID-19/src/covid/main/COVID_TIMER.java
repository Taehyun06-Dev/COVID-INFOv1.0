package covid.main;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import covid.gui.Logger;

public class COVID_TIMER{

	private static Timer timer;

    public void schedulePrune(Integer delay) {
    	if (timer != null) {
			new Logger().log_Info("Timer Stopped!");
    		timer.cancel();
    	}
    	timer = new Timer();
    	timer.schedule(
    		new TimerTask() {
    			@Override
    			public void run() {
    				try {
						new COVID().updateCOVID(false);
					} catch (InterruptedException | ExecutionException e) {
						new Logger().log_Warn("Timer Exception!");
					}
    			}
    		}, delay*60000, delay*60000
    	);
    }
}