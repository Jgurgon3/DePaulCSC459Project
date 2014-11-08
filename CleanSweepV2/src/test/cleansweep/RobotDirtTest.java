package test.cleansweep;

import static org.junit.Assert.*;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Test;
import org.xml.sax.SAXException;
import XMLParse.FloorCell;
import CleanSweepModels.*;


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
		FloorPlan fp = (XMLParse.ParserFloorPlan.runParser("XMLParse/xml3x3.xml"));
		robot=fp.getRobot();
		}
		
	}
}
