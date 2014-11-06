package test.cleansweep;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xml.sax.SAXException;

import XMLParse.FloorCell;
import CleanSweepModels.*;


public class RobotMoveTest {
	@Rule
    public ExpectedException exception = ExpectedException.none();

	private Robot robot =null;

	@Test
	public void testMove01() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot(); 
		robot.Move(new Point(0, 1));
		assertEquals(robot.getCoordinates().getX(),0);
		assertEquals(robot.getCoordinates().getY(),1);
	}

	@Test
	public void testMove02() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot(); 
		robot.Move(new Point(0, 1));
		robot.Move(new Point(0, 2));
		assertEquals(robot.getCoordinates().getX(),0);
		assertEquals(robot.getCoordinates().getY(),2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMoveMoreThanCell() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		robot.Move(new Point(1, 1));
		}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMoveMinusCordinate() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		robot.Move(new Point(-1, 1));
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
