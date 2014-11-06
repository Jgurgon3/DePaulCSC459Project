package CleanSweepModels;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Home {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
    	
    	String path = "XMLParse/BigXML.xml";
    	
    	if (args.length > 0) {
			if (isValidXML(args[0]))
				path = args[0];
		}

		startRobot(path);
        
    }
    public static void startRobot(String path) throws ParserConfigurationException, SAXException, IOException {
		FloorPlan fp = (XMLParse.ParserFloorPlan
				.runParser(path));
		
		System.out.println(fp.getRobot().toString());
		System.out.println(fp.toString());

		while(fp.floorPlanIsCleaned() == false)
    	{
    		fp.MoveRobot();    		
    	}
		System.out.println(fp.toString());
		System.out.println("Floor is clean");
		//fp.getRobot().dumpLog();
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
