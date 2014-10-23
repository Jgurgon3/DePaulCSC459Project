package CleanSweepModels;

import java.util.Calendar;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RobotLog {
	
	public enum LogTypes {
	    MOVE ("MOVE"),
	    WAKEUP ("WAKE UP"),
	    OUTOFPOWER ("OUT OF POWER"),
	    NOTENOUGHPOWER ("NOT ENOUGH POWER"),
	    CLEANED ("CLEANED"),
	    RETURNTOCHARGER("RETURNING TO CHARGER");

	    private final String name;       

	    private LogTypes(String s) {
	        name = s;
	    }

	    public boolean equalsName(String otherName){
	        return (otherName == null)? false:name.equals(otherName);
	    }

	    public String toString(){
	       return name;
	    }

	}
	 private List<String> _log = new ArrayList<String>();
	public RobotLog() {
		
	}
	public void addLog(LogTypes type, String action) {
		String now = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		
		_log.add(now + " - " + type + " - " + action);
	}
	
	public void dumpLog() {
		for (String s : _log) {
			System.out.println(s);
		}
	}
}
