package CleanSweepModels;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Home {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        
    	
    	
    	FloorPlan fp = (XMLParse.ParserFloorPlan.runParser("XMLParse/xml3x3.xml"));
    	
    	
    	
        System.out.println("Initial: " + fp.getRobot().toString());
        fp.MoveRobot(2, 1); // Move the right 2 space and up 1 space
        System.out.println(fp.getRobot().toString());
        fp.MoveRobot(1, -1); // Move the right 1 space and up 1 space
        System.out.println(fp.getRobot().toString());
        fp.MoveRobot(-2, 0); // Move the right 1 space and down 1 space
        
        
    }

}
