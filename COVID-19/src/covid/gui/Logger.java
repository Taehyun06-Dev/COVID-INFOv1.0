package covid.gui;

import java.util.stream.Collectors;

import covid.main.COVID_DATA;

public class Logger {
	
	private static FrmMain gui;

	public void setGUI(FrmMain myGUI){
		gui = myGUI;
	}
	public void log_Info(String input) {
		if(gui != null){
			gui.updateLogDisplay("[Info] "+input+"\n");
		}
	}
	public void log_Warn(String input) {
		if(gui != null){
			gui.updateLogDisplay("[Warn] "+input+"\n");
		}
	}
	public void update_time() {
		if(gui != null){
			gui.updateTime("마지막 업데이트: "+new COVID_DATA().getLastUpdate());
		}
	}
	public void update_Map() {
		String str =  new COVID_DATA().getDataMap().keySet().stream()
			      .map(key -> key + ": " + new COVID_DATA().getDataMap().get(key))
			      .collect(Collectors.joining("\n", "", ""));
		if(gui != null){
			gui.updateDisplay(str);
		}
	}
	public Boolean getAlertStat() {
		if(gui != null){
			return gui.getAlarmStat();
		}
		return false;
	}
	public Boolean getDebugStat() {
		if(gui != null){
			return gui.getDebugStat();
		}
		return false;
	}
	public Boolean getSoundStat() {
		if(gui != null){
			return gui.getSoundStat();
		}
		return false;
	}
	public void updateNum() {
		if(gui != null){
			gui.updateNum("총 확진자: "+new COVID_DATA().getDataTotal()+"명(경로 확인) / "+new COVID_DATA().getDataWhole()+"명");
		}
	}
	

}
