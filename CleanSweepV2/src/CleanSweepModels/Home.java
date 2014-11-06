package CleanSweepModels;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import XMLParse.FloorCell;

public class Home {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
    	
    	FloorPlan fp = (XMLParse.ParserFloorPlan.runParser("XMLParse/xml3x3.xml"));
    	//FloorPlan fp = (XMLParse.ParserFloorPlan.runParser("XMLParse/BigXML.xml"));
    	
    	
    	System.out.println(fp.getRobot().toString());
    	System.out.println(fp.toString());
    	
    	while(fp.floorPlanIsCleaned() == false)
    	{
    		fp.MoveRobot();    		
    	}
        System.out.println(fp.toString());
        System.out.println(fp.getRobot().toString());
        //fp.getRobot().dumpLog();
        
    }

}
