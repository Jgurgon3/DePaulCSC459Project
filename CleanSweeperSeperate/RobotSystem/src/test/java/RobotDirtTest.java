package src.test.java;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import src.main.java.CleanSweepModels.*;
import src.main.java.XMLParse.FloorCell;
import src.main.java.XMLParse.FloorPlan;
import src.main.java.XMLParse.FloorTypes;
import src.main.java.XMLParse.ParserFloorPlan;
import src.main.java.XMLParse.Point;



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

	@Test
	public void testDirtCollectedUnitBare() throws ParserConfigurationException, SAXException, IOException  {
		initRobot(); 
		Point p= new Point(0, 1);
		FloorCell fcBare = robot.getFloorPlan().getCellByPoint(p);
		fcBare.setFloorType(FloorTypes.BARE);
		fcBare.setCleaned(false);
		robot.Clean(fcBare);
  	    assertEquals(1, robot.getDirtCollected());
	}
	
	@Test
	public void testDirtCollectedUnitLow() throws ParserConfigurationException, SAXException, IOException  {
		initRobot(); 
		Point p= new Point(0, 1);
		FloorCell fcLow = robot.getFloorPlan().getCellByPoint(p);
		fcLow.setFloorType(FloorTypes.LOW);
		fcLow.setCleaned(false);
		robot.Clean(fcLow);
  	    assertEquals(1, robot.getDirtCollected());
	}
	
	@Test
	public void testDirtCollectedUnitHigh() throws ParserConfigurationException, SAXException, IOException  {
		initRobot(); 
		Point p= new Point(0, 1);
		FloorCell fcHigh = robot.getFloorPlan().getCellByPoint(p);
		fcHigh.setFloorType(FloorTypes.HIGH);
		fcHigh.setCleaned(false);
		robot.Clean(fcHigh);
  	    assertEquals(1, robot.getDirtCollected());
	}
	
	@Test
	public void testDirtCollectedUnitTwoBare() throws ParserConfigurationException, SAXException, IOException  {
		initRobot(); 
		Point p= new Point(0, 0);
		FloorCell fcBare = robot.getFloorPlan().getCellByPoint(p);
		fcBare.setFloorType(FloorTypes.BARE);
		fcBare.setCleaned(false);
		robot.Clean(fcBare);
		robot.Move(p);
		
		Point p2= new Point(0, 1);
		FloorCell fcBare2 = robot.getFloorPlan().getCellByPoint(p2);
		fcBare2.setFloorType(FloorTypes.BARE);
		fcBare2.setCleaned(false);
		robot.Clean(fcBare2);

  	    assertEquals(2, robot.getDirtCollected());
	}

	@Test
	public void testDirtCollectedUnitTwoLow() throws ParserConfigurationException, SAXException, IOException  {
		initRobot(); 
		Point p= new Point(0, 0);
		FloorCell fcLow = robot.getFloorPlan().getCellByPoint(p);
		fcLow.setFloorType(FloorTypes.LOW);
		fcLow.setCleaned(false);
		robot.Clean(fcLow);
		robot.Move(p);
		
		Point p2= new Point(0, 1);
		FloorCell fcLow2 = robot.getFloorPlan().getCellByPoint(p2);
		fcLow2.setFloorType(FloorTypes.LOW);
		fcLow2.setCleaned(false);
		robot.Clean(fcLow2);

  	    assertEquals(2, robot.getDirtCollected());
	}

	@Test
	public void testDirtCollectedUnitTwoHigh() throws ParserConfigurationException, SAXException, IOException  {
		initRobot(); 
		Point p= new Point(0, 0);
		FloorCell fcHigh = robot.getFloorPlan().getCellByPoint(p);
		fcHigh.setFloorType(FloorTypes.HIGH);
		fcHigh.setCleaned(false);
		robot.Clean(fcHigh);
		robot.Move(p);
		
		Point p2= new Point(0, 1);
		FloorCell fcHigh2 = robot.getFloorPlan().getCellByPoint(p2);
		fcHigh2.setFloorType(FloorTypes.HIGH);
		fcHigh2.setCleaned(false);
		robot.Clean(fcHigh2);

  	    assertEquals(2, robot.getDirtCollected());
	}
	
	@Test
	public void testDirtCollectedUnitHighLow() throws ParserConfigurationException, SAXException, IOException  {
		initRobot(); 
		Point p= new Point(0, 0);
		FloorCell fcHigh = robot.getFloorPlan().getCellByPoint(p);
		fcHigh.setFloorType(FloorTypes.HIGH);
		fcHigh.setCleaned(false);
		robot.Clean(fcHigh);
		robot.Move(p);
		
		Point p2= new Point(0, 1);
		FloorCell fclow = robot.getFloorPlan().getCellByPoint(p2);
		fclow.setFloorType(FloorTypes.LOW);
		fclow.setCleaned(false);
		robot.Clean(fclow);

  	    assertEquals(2, robot.getDirtCollected());
	}

	@Test
	public void testDirtCollectedUnitHighBare() throws ParserConfigurationException, SAXException, IOException  {
		initRobot(); 
		Point p= new Point(0, 0);
		FloorCell fcHigh = robot.getFloorPlan().getCellByPoint(p);
		fcHigh.setFloorType(FloorTypes.HIGH);
		fcHigh.setCleaned(false);
		robot.Clean(fcHigh);
		robot.Move(p);
		
		Point p2= new Point(0, 1);
		FloorCell fcBare = robot.getFloorPlan().getCellByPoint(p2);
		fcBare.setFloorType(FloorTypes.BARE);
		fcBare.setCleaned(false);
		robot.Clean(fcBare);

  	    assertEquals(2, robot.getDirtCollected());
	}
	
	@Test
	public void testDirtCollectedUnitLowBare() throws ParserConfigurationException, SAXException, IOException  {
		initRobot(); 
		Point p= new Point(0, 0);
		FloorCell fclow = robot.getFloorPlan().getCellByPoint(p);
		fclow.setFloorType(FloorTypes.LOW);
		fclow.setCleaned(false);
		robot.Clean(fclow);
		robot.Move(p);
		
		Point p2= new Point(0, 1);
		FloorCell fcBare = robot.getFloorPlan().getCellByPoint(p2);
		fcBare.setFloorType(FloorTypes.BARE);
		fcBare.setCleaned(false);
		robot.Clean(fcBare);

  	    assertEquals(2, robot.getDirtCollected());
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
