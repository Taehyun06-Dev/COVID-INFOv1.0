package covid.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import covid.gui.Logger;

public class COVID_DATA {
	
	private static Map<String, Integer> COVID_MAP = new HashMap<String, Integer>();
	private static int COVID_TOTAL;
	private static int COVID_WHOLE;
	private static String UPDATE_TIME;
	private static String PAGE_TIME;
	private static Boolean USE_ALERT;
	private static Boolean USE_SOUND;
	private static Boolean USE_DEBUG;		
	
	public String getLastUpdate() {
		if(!(COVID_DATA.UPDATE_TIME != null)) {
			return "업데이트 필요";
		}
		return COVID_DATA.UPDATE_TIME;
	}
	public void setLastUpdate() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");  
		COVID_DATA.UPDATE_TIME = formatter.format(new Date());
		new Logger().update_time();
	}
	public String getPageTime() {
		return PAGE_TIME;
	}
	public void setPageTime(String val) {
		PAGE_TIME = val;
	}
	public Map<String, Integer> getDataMap(){
		return COVID_DATA.COVID_MAP;
	}	
	public int getDataWhole(){
		return COVID_DATA.COVID_WHOLE;		
	}	
	public void setDataWhole(Integer val) {
		COVID_DATA.COVID_WHOLE = val;
	}
	public int getDataTotal(){
		return COVID_DATA.COVID_TOTAL;		
	}	
	public void setDataTotal(Integer val) {
		COVID_DATA.COVID_TOTAL = val;
		new Logger().updateNum();
	}
	public Boolean getAlertStat() {
		return USE_ALERT;
	}
	public void SetAlertStat() {
		USE_ALERT = new Logger().getAlertStat();
	}
	public Boolean getSoundStat() {
		return USE_SOUND;
	}
	public void SetSoundtStat() {
		USE_SOUND = new Logger().getSoundStat();
	}
	public Boolean getDebugStat() {
		return USE_DEBUG;
	}
	public void SetDebugStat() {
		USE_DEBUG = new Logger().getDebugStat();
	}
	
	
}
