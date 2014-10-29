package CleanSweepModels;

import static org.junit.Assert.*;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Test;
import org.xml.sax.SAXException;
import XMLParse.FloorCell;
import CleanSweepModels.Types.*;


public class RobotPowerTest {

	private Robot robot =null;
	
	@Test
	public void testBareCarpetClean() throws ParserConfigurationException, SAXException, IOException  {
		FloorCell fc1 = new FloorCell();
		fc1.setXCoordinates(0);
		fc1.setYCoordinates(0);
		fc1.setFloorType(FloorTypes.BARE);
		initRobot(); 
	    double powerToClean=  robot.getPowerForCellClean(fc1);
  	    assertEquals(1, powerToClean,0);
	}

	@Test
	public void testLowPileCarpetClean()  throws ParserConfigurationException, SAXException, IOException  {
		FloorCell fc1 = new FloorCell();
		fc1.setXCoordinates(2);
		fc1.setYCoordinates(3);
		fc1.setFloorType(FloorTypes.LOW);
		initRobot(); 
		double powerToClean= robot.getPowerForCellClean(fc1);
		assertEquals(2, powerToClean,0);
	}
	
	
	@Test
	public void testHighPileCarpetClean() throws ParserConfigurationException, SAXException, IOException  {
		initRobot(); 
		FloorCell fc1 = new FloorCell();
		fc1.setXCoordinates(0);
		fc1.setYCoordinates(0);
		fc1.setFloorType(FloorTypes.HIGH);
		
		double powerToClean= robot.getPowerForCellClean(fc1);
		assertEquals(3, powerToClean,0);
	}
	
	@Test
	public void testMoveBetweenBareCarpet() throws ParserConfigurationException, SAXException, IOException  {
	initRobot();	
    FloorCell fc1 = robot.getMemory().get(new Point(0, 0));
	//fc1.setXCoordinates(0);
	//fc1.setYCoordinates(0);
	fc1.setFloorType(FloorTypes.BARE);
	int size=  robot.getMemory().size();
	System.out.println(size);
	FloorCell fc2 =new FloorCell();
	fc2.setFloorType(FloorTypes.BARE);
	
	double powerToMove= robot.getPowerForCellMoving(fc1, fc2);
	assertEquals(1, powerToMove,0);
	}
	
	private void initRobot()throws ParserConfigurationException, SAXException,IOException 
	{
		if(robot==null)
		{
		FloorPlan fp = (XMLParse.ParserFloorPlan.runParser("XMLParse/xml3x3.xml"));
		robot=fp.getRobot();
		}
		
	}
}
