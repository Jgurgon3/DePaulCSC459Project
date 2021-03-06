package src.main.java.CleanSweepModels;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import src.main.java.XMLParse.*;

public class Home {
    private final static Logger logger = Logger.getLogger(Home.class.getName());

    public static void main(String[] args) throws IOException,
			ParserConfigurationException, SAXException {

		String path = "BIGxml.xml";
		boolean error = false;
		try {
			if (args.length > 0) {

				if (isValidXML(args[0])) {
					path = args[0];
				}
				else {
					System.out
							.println("Invalid file path. Path must be to a valid XML file");
					error = true;
				}
			}
			if (!error) {
				startRobot(path);
			}
		} catch (Exception exp) {
            logger.log(Level.SEVERE, "Error starting robot with log", exp);
		}

	}

	public static void startRobot(String path)
			throws ParserConfigurationException, SAXException, IOException {
		FloorPlan fp = (ParserFloorPlan.runParser(path));
		Robot r = new Robot(0, 0, fp);
		System.out.println(r.toString());
		System.out.println(fp.toString());

		while (fp.floorPlanIsCleaned() == false) {
			r.MoveRobot();

		}
		System.out.println(fp.toString());
		System.out.println("Floor is clean");
		r.dumpLog();
	}

	// returns false if not a existing XML file
	public static boolean isValidXML(String path) throws IOException {
		File f = new File(path);

		if (f.exists() && !f.isDirectory()) {
			// check if XML
			String extension = "";

			int i = path.lastIndexOf('.');
			if (i > 0) {
				extension = path.substring(i + 1);
				if (extension.equalsIgnoreCase("XML")) {
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

}
