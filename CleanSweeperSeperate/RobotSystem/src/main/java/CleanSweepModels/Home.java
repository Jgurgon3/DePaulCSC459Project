package src.main.java.CleanSweepModels;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import src.main.java.*;
import src.main.java.XMLParse.FloorPlan;
import src.main.java.XMLParse.ParserFloorPlan;


public class Home {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException  {
    	
    	String path = "src/main/java/CleanSweepModels/BigXML.xml";
    	if (args.length > 0) {
			if (isValidXML(args[0]))
				path = args[0];
		}

		startRobot(path);
        
    }
    public static void startRobot(String path) throws ParserConfigurationException, SAXException, IOException {
		FloorPlan fp = (ParserFloorPlan
				.runParser(path));
		Robot r = new Robot(0, 0, fp);
		System.out.println(r.toString());
		System.out.println(fp.toString());

		while(fp.floorPlanIsCleaned() == false)
    	{
    		r.MoveRobot();

    	}
		System.out.println(fp.toString());
		System.out.println("Floor is clean");
		r.dumpLog();
	}

	// returns false if not a existing XML file
	public static boolean isValidXML(String path) throws IOException {
		File f = new File(path);
		//String workingDir = System.getProperty("user.dir");
		//File f2 = new File(workingDir + "/" + path);
		       
		if (f.exists() && !f.isDirectory()) {
			// check if XML
			String extension = "";

			int i = path.lastIndexOf('.');
			if (i > 0) {
				extension = path.substring(i + 1);
				if (extension.equalsIgnoreCase("XML"))
					return true;
			}
			return false;
		} else
			return false;
	}


}
