package src.test.java;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;


import src.main.java.CleanSweepModels.*;
import src.main.java.XMLParse.*;





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
	FloorCell fc2 =new FloorCell();
	fc2.setFloorType(FloorTypes.BARE);
	
	double powerToMove= robot.getPowerForCellMoving(fc1, fc2);
	assertEquals(1, powerToMove,0);
	}

	@Test
	public void testMoveBetweenBareToLowPile() throws ParserConfigurationException, SAXException, IOException  {
	initRobot();	
    FloorCell fc1 = robot.getMemory().get(new Point(0, 0));
	fc1.setFloorType(FloorTypes.BARE);
	int size=  robot.getMemory().size();
	System.out.println(size);
	FloorCell fc2 =new FloorCell();
	fc2.setFloorType(FloorTypes.LOW);
	double powerToMove= robot.getPowerForCellMoving(fc1, fc2);
	assertEquals(1.5, powerToMove,0);
	}

	@Test
	public void testMoveBetweenBareToHighPile() throws ParserConfigurationException, SAXException, IOException  {
	initRobot();	
    FloorCell fc1 = robot.getMemory().get(new Point(0, 0));
	fc1.setFloorType(FloorTypes.BARE);
	int size=  robot.getMemory().size();
	System.out.println(size);
	FloorCell fc2 =new FloorCell();
	fc2.setFloorType(FloorTypes.HIGH);
	double powerToMove= robot.getPowerForCellMoving(fc1, fc2);
	assertEquals(2, powerToMove,0);
	}

	@Test
	public void testMoveBetweenLoTolowPile() throws ParserConfigurationException, SAXException, IOException  {
	initRobot();	
    FloorCell fc1 = robot.getMemory().get(new Point(0, 0));
	fc1.setFloorType(FloorTypes.LOW);
	int size=  robot.getMemory().size();
	System.out.println(size);
	FloorCell fc2 =new FloorCell();
	fc2.setFloorType(FloorTypes.LOW);
	double powerToMove= robot.getPowerForCellMoving(fc1, fc2);
	assertEquals(2, powerToMove,0);
	}

	@Test
	public void testMoveBetweenLowToHighPile() throws ParserConfigurationException, SAXException, IOException  {
	initRobot();	
    FloorCell fc1 = robot.getMemory().get(new Point(0, 0));
	fc1.setFloorType(FloorTypes.LOW);
	int size=  robot.getMemory().size();
	System.out.println(size);
	FloorCell fc2 =new FloorCell();
	fc2.setFloorType(FloorTypes.HIGH);
	double powerToMove= robot.getPowerForCellMoving(fc1, fc2);
	assertEquals(2.5, powerToMove,0);
	}

	@Test
	public void testMoveBetweenHighToHighPile() throws ParserConfigurationException, SAXException, IOException  {
	initRobot();	
    FloorCell fc1 = robot.getMemory().get(new Point(0, 0));
	fc1.setFloorType(FloorTypes.HIGH);
	int size=  robot.getMemory().size();
	System.out.println(size);
	FloorCell fc2 =new FloorCell();
	fc2.setFloorType(FloorTypes.HIGH);
	double powerToMove= robot.getPowerForCellMoving(fc1, fc2);
	assertEquals(3, powerToMove,0);
	}

	private void initRobot()throws ParserConfigurationException, SAXException,IOException  {
		if(robot==null) {
		FloorPlan fp = (ParserFloorPlan.runParser("xml3x3.xml"));
		robot=new Robot(0,0,fp);
		}
		
	}
}
