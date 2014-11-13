package src.test.java;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xml.sax.SAXException;

import src.main.java.CleanSweepModels.Robot;
import src.main.java.XMLParse.FloorCell;
import src.main.java.XMLParse.FloorPlan;
import src.main.java.XMLParse.ParserFloorPlan;




public class RobotMoveTest {
	@Rule
    public ExpectedException exception = ExpectedException.none();

	private Robot robot =null;

	@Test
	public void testMoveY() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot(); 
		FloorCell fc = new FloorCell() ;
		fc.setXCoordinates(0);
		fc.setYCoordinates(1);
		robot.Move(fc,true);
		assertEquals(0,robot.getCoordinates().getX());
		assertEquals(1,robot.getCoordinates().getY());

	}

	@Test
	public void testMoveX() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot(); 
		FloorCell fc = new FloorCell() ;
		fc.setXCoordinates(1);
		fc.setYCoordinates(0);
		robot.Move(fc,true);
		assertEquals(robot.getCoordinates().getX(),1);
		assertEquals(robot.getCoordinates().getY(),0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMoveTwoYCordinate() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		FloorCell fc = new FloorCell() ;
		fc.setXCoordinates(0);
		fc.setYCoordinates(2);
		robot.Move(fc,true);
		
		}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMoveTwoXCordinate() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		FloorCell fc = new FloorCell() ;
		fc.setXCoordinates(2);
		fc.setYCoordinates(0);
		robot.Move(fc,true);
		

		}
	
	private void initRobot()throws ParserConfigurationException, SAXException,IOException 
	{
		if(robot==null)
		{
			FloorPlan fp = (ParserFloorPlan.runParser("xml3x3.xml"));
			robot=new Robot(0,0,fp);
		}

	}
}