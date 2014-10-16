package CleanSweepModels;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Home {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        
    	
    	
    	FloorPlan fp = (XMLParse.ParserFloorPlan.runParser("XMLParse/xml3x3.xml"));
    	
    	
    	
        fp.MoveRobot();
        
        
    }

}
