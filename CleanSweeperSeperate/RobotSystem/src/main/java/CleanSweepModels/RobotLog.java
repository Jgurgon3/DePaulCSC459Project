package src.main.java.CleanSweepModels;

import java.util.Calendar;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RobotLog {
    private final static Logger logger = Logger.getLogger(DrawPanel.class.getName());

	public enum LogActivityTypes {
		MOVE("MOVE"), WAKEUP("WAKE UP"), OUTOFPOWER("OUT OF POWER"), NOTENOUGHPOWER(
				"NOT ENOUGH POWER"), CLEANED("CLEANED"), RETURNTOCHARGER(
				"RETURNING TO CHARGER");

		private final String name;

		private LogActivityTypes(String s) {
			name = s;
		}

		public boolean equalsName(String otherName) {
			return (otherName == null) ? false : name.equals(otherName);
		}

		public String toString() {
			return name;
		}

	}

	private List<String> _log = new ArrayList<String>();

	public RobotLog() {

	}

	public void addLog(LogActivityTypes type, String action) {
		String now = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
				.format(Calendar.getInstance().getTime());

		_log.add(now + " - " + type + " - " + action);
	}

	public void clearLog() {
		_log.clear();
	}

	public void dumpLog() {
		for (String s : _log) {
			System.out.println(s);
		}
		writeLog();

	}

	public void writeLog() {
		// write to file
		try {
			File f = new File("RobotLog.txt");
			if (!f.exists()) {
				f.createNewFile();
			}
			FileWriter fw = new FileWriter(f.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			for (String s : _log) {
				bw.write(s + System.getProperty("line.separator"));
			}
			bw.close();
		} catch (Exception ex) {
            logger.log(Level.SEVERE, "Error writing to log file", ex);
		}
	}
}
