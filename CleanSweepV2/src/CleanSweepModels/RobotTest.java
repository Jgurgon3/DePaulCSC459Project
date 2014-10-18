package CleanSweepModels;
import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import junit.framework.TestCase;

public class RobotTest extends TestCase {
	public void testConstructorAndAttributes() throws ParserConfigurationException, SAXException, IOException {
		
	FloorPlan fp = (XMLParse.ParserFloorPlan.runParser("XMLParse/xml3x3.xml"));
	
	assertSame(fp.getRobot().getCoordinates().getX(), 0);
	assertSame(fp.getRobot().getCoordinates().getY(), 0);
	
	assertFalse(fp.getRobot().getReturnToChargerFlag());
	
	assertSame(fp.getRobot().getPower(), 50);
	
	}
	
	public void testMovement() throws ParserConfigurationException, SAXException, IOException {
		FloorPlan fp = (XMLParse.ParserFloorPlan.runParser("XMLParse/xml3x3.xml"));
		
		
		//test east
		fp.getRobot().Move(new Point(1,0));
		
		assertSame(fp.getRobot().getCoordinates().getX(), 1);
		assertSame(fp.getRobot().getCoordinates().getY(), 0);
		
		//test west
		fp.getRobot().Move(new Point(0,0));
		
		assertSame(fp.getRobot().getCoordinates().getX(), 0);
		assertSame(fp.getRobot().getCoordinates().getY(), 0);
		
		//test north
		fp.getRobot().Move(new Point(0,1));
		assertSame(fp.getRobot().getCoordinates().getX(), 0);
		assertSame(fp.getRobot().getCoordinates().getY(), 1);
		
		//test south
		fp.getRobot().Move(new Point(0,0));
		assertSame(fp.getRobot().getCoordinates().getX(), 0);
		assertSame(fp.getRobot().getCoordinates().getY(), 0);
		
		//test northeast
		fp.getRobot().Move(new Point(1,1));
		assertSame(fp.getRobot().getCoordinates().getX(), 0);
		assertSame(fp.getRobot().getCoordinates().getY(), 0);
		
		fp.getRobot().Move(new Point(1,0));
		fp.getRobot().Move(new Point(1,1));
		assertSame(fp.getRobot().getCoordinates().getX(), 1);
		assertSame(fp.getRobot().getCoordinates().getX(), 1);
		
		//test southwest
		fp.getRobot().Move(new Point(1,1));
		assertSame(fp.getRobot().getCoordinates().getX(), 1);
		assertSame(fp.getRobot().getCoordinates().getY(), 1);
		
		System.out.println(fp.getRobot().getPower());
		
	}
	
}
