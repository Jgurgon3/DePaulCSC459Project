package CleanSweepModels;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Home {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
  
    	FloorPlan fp = (XMLParse.ParserFloorPlan.runParser("XMLParse/xml3x3.xml"));
    	
    	
    	System.out.println(fp.getRobot().toString());
    	System.out.println(fp.getChargingStation().toString()+"\n");
    	System.out.println(fp.toString());
    	
        fp.MoveRobot(new ArrayList<Point>());
        System.out.println("Floor is clean");
        
        
    }

}
