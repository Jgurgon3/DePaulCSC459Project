package src.test.java;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import src.main.java.*;
import src.main.java.CleanSweepModels.*;
import src.main.java.XMLParse.FloorPlan;
import src.main.java.XMLParse.ParserFloorPlan;

/*

public class RobotDirtTest {

	private Robot robot =null;
	
	@Test
	public void testStoreMoreDirtYes() throws ParserConfigurationException, SAXException, IOException  {
		initRobot(); 
		for(int i=0;i<40;i++)
		{
			robot.addToDirtCollected();
		}
	    Boolean powerToClean=  robot.canStoreMoreDirt();
  	    assertEquals(true, powerToClean);
	}

	@Test
	public void testStoreMoreDirtYesAtZero() throws ParserConfigurationException, SAXException, IOException  {
		initRobot(); 
	    Boolean powerToClean=  robot.canStoreMoreDirt();
  	    assertEquals(true, powerToClean);
	}
	
	@Test
	public void testStoreMoreDirtYesAtOneUnitLeft() throws ParserConfigurationException, SAXException, IOException  {
		initRobot(); 
		for(int i=0;i<48;i++)
		{
			robot.addToDirtCollected();
		}
	    Boolean powerToClean=  robot.canStoreMoreDirt();
  	    assertEquals(true, powerToClean);
	}
	@Test
	public void testStoreMoreDirtNo() throws ParserConfigurationException, SAXException, IOException  {
		initRobot(); 
		for(int i=0;i<51;i++)
		{
			robot.addToDirtCollected();
		}
	    Boolean powerToClean=  robot.canStoreMoreDirt();
  	    assertEquals(false, powerToClean);
	}

	private void initRobot()throws ParserConfigurationException, SAXException,IOException 
	{
		if(robot==null)
		{
		FloorPlan fp = (ParserFloorPlan.runParser("src/main/java/CleanSweepModels/xml3x3.xml"));
		robot=fp.getRobot();
		}
		
	}
}*/
