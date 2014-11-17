package src.test.java;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xml.sax.SAXException;

import src.main.java.CleanSweepModels.*;
import src.main.java.XMLParse.FloorCell;
import src.main.java.XMLParse.FloorPlan;
import src.main.java.XMLParse.FloorTypes;
import src.main.java.XMLParse.ParserFloorPlan;
import src.main.java.XMLParse.Point;

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
	
	@Test
	public void testMoveToChargerOneCellMove() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		FloorCell fc = new FloorCell() ;
		fc.setXCoordinates(1);
		fc.setYCoordinates(0);
		robot.Move(fc,true);
		robot.ChargeAndEmpty();
		assertEquals(50, robot.getPower(),0);
		//assertEquals(0, robot.getDirtCollected(),0);
		
		}

	@Test
	public void testMoveToChargertwoCellMove() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		FloorCell fc = new FloorCell() ;
		fc.setXCoordinates(1);
		fc.setYCoordinates(0);
		robot.Move(fc,true);
		FloorCell fc2 = new FloorCell() ;
		fc2.setXCoordinates(2);
		fc2.setYCoordinates(0);
		robot.Move(fc2,true);
		robot.ChargeAndEmpty();
		assertEquals(50, robot.getPower(),0);

		
		}
	
	@Test
	public void testEmptyBinCleanOneCell() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		FloorCell fc = new FloorCell() ;
		fc.setXCoordinates(1);
		fc.setYCoordinates(0);
		robot.Move(fc,true);
		robot.ChargeAndEmpty();
		assertEquals(0, robot.getDirtCollected(),0);
		}

	@Test
	public void testEmptyBinCleanMoreCell() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		FloorCell fc = new FloorCell() ;
		fc.setXCoordinates(1);
		fc.setYCoordinates(0);
		robot.Move(fc,true);
		
		FloorCell fc2 = new FloorCell() ;
		fc2.setXCoordinates(2);
		fc2.setYCoordinates(0);
		robot.Move(fc2,true);

		robot.ChargeAndEmpty();
		assertEquals(0, robot.getDirtCollected(),0);
		}

	@Test
	public void testResetBreadCrumbPowerNeededCleanMoreCell() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		FloorCell fc = new FloorCell() ;
		fc.setXCoordinates(1);
		fc.setYCoordinates(0);
		robot.Move(fc,true);
		
		FloorCell fc2 = new FloorCell() ;
		fc2.setXCoordinates(2);
		fc2.setYCoordinates(0);
		robot.Move(fc2,true);

		robot.resetBreadCrumbPowerNeeded();
		assertEquals(0.0, robot.getBreadCrumbPowerNeeded(),0);
		}
	
	@Test
	public void testresetBreadCrumbPowerNeededCleanOneCell() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		FloorCell fc = new FloorCell() ;
		fc.setXCoordinates(1);
		fc.setYCoordinates(0);
		robot.Move(fc,true);
		robot.resetBreadCrumbPowerNeeded();
		assertEquals(0.0, robot.getBreadCrumbPowerNeeded(),0);
		}
	
	@Test
	public void testAddToBreadCrumbPowerNeededHighCarpet() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		Point p= new Point(0, 1);
		FloorCell fcHigh = robot.getFloorPlan().getCellByPoint(p);
		fcHigh.setFloorType(FloorTypes.HIGH);
		fcHigh.setCleaned(false);
		robot.addToBreadCrumbPowerNeeded(p);
		assertEquals(3, robot.getBreadCrumbPowerNeeded(),0);
		}
	@Test
	public void testAddToBreadCrumbPowerNeededLawCarpet() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		Point p= new Point(0, 1);
		FloorCell fcHigh = robot.getFloorPlan().getCellByPoint(p);
		fcHigh.setFloorType(FloorTypes.LOW);
		fcHigh.setCleaned(false);
		robot.addToBreadCrumbPowerNeeded(p);
		assertEquals(2, robot.getBreadCrumbPowerNeeded(),0);
		}
	
	@Test
	public void testAddToBreadCrumbPowerNeededBare() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		Point p= new Point(0, 1);
		FloorCell fcHigh = robot.getFloorPlan().getCellByPoint(p);
		fcHigh.setFloorType(FloorTypes.BARE);
		fcHigh.setCleaned(false);
		robot.addToBreadCrumbPowerNeeded(p);
		assertEquals(1, robot.getBreadCrumbPowerNeeded(),0);
		}
	
	@Test
	public void testAddToBreadCrumbPowerNeededHighBare() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		Point p= new Point(0, 1);
		FloorCell fcHigh = robot.getFloorPlan().getCellByPoint(p);
		fcHigh.setFloorType(FloorTypes.HIGH);
		fcHigh.setCleaned(false);
		robot.Move(p);
		robot.addToBreadCrumbPowerNeeded(p);
		
		Point p2= new Point(0, 2);
		FloorCell fcBare = robot.getFloorPlan().getCellByPoint(p2);
		fcBare.setFloorType(FloorTypes.BARE);
		fcBare.setCleaned(false);
		robot.addToBreadCrumbPowerNeeded(p2);
		assertEquals(4, robot.getBreadCrumbPowerNeeded(),0);
		}

	@Test
	public void testAddToBreadCrumbPowerNeededHighLow() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		Point p= new Point(0, 1);
		FloorCell fcHigh = robot.getFloorPlan().getCellByPoint(p);
		fcHigh.setFloorType(FloorTypes.HIGH);
		fcHigh.setCleaned(false);
		robot.Move(p);
		robot.addToBreadCrumbPowerNeeded(p);
		
		Point p2= new Point(0, 2);
		FloorCell fclow = robot.getFloorPlan().getCellByPoint(p2);
		fclow.setFloorType(FloorTypes.LOW);
		fclow.setCleaned(false);
		robot.addToBreadCrumbPowerNeeded(p2);
		assertEquals(5, robot.getBreadCrumbPowerNeeded(),0);
		}

	@Test
	public void testAddToBreadCrumbPowerNeededHighHigh() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		Point p= new Point(0, 1);
		FloorCell fcHigh = robot.getFloorPlan().getCellByPoint(p);
		fcHigh.setFloorType(FloorTypes.HIGH);
		fcHigh.setCleaned(false);
		robot.Move(p);
		robot.addToBreadCrumbPowerNeeded(p);
		
		Point p2= new Point(0, 2);
		FloorCell fcHigh2 = robot.getFloorPlan().getCellByPoint(p2);
		fcHigh2.setFloorType(FloorTypes.HIGH);
		fcHigh2.setCleaned(false);
		robot.addToBreadCrumbPowerNeeded(p2);
		assertEquals(6, robot.getBreadCrumbPowerNeeded(),0);
		}
	

	@Test
	public void testAddToBreadCrumbPowerNeededLowLow() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		Point p= new Point(0, 1);
		FloorCell fcLow = robot.getFloorPlan().getCellByPoint(p);
		fcLow.setFloorType(FloorTypes.LOW);
		fcLow.setCleaned(false);
		robot.Move(p);
		robot.addToBreadCrumbPowerNeeded(p);
		
		Point p2= new Point(0, 2);
		FloorCell fcLow2 = robot.getFloorPlan().getCellByPoint(p2);
		fcLow2.setFloorType(FloorTypes.LOW);
		fcLow2.setCleaned(false);
		robot.addToBreadCrumbPowerNeeded(p2);
		assertEquals(4, robot.getBreadCrumbPowerNeeded(),0);
		}


	@Test
	public void testAddToBreadCrumbPowerNeededBareBare() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		Point p= new Point(0, 1);
		FloorCell fcBare = robot.getFloorPlan().getCellByPoint(p);
		fcBare.setFloorType(FloorTypes.BARE);
		fcBare.setCleaned(false);
		robot.Move(p);
		robot.addToBreadCrumbPowerNeeded(p);
		
		Point p2= new Point(0, 2);
		FloorCell fcBare2 = robot.getFloorPlan().getCellByPoint(p2);
		fcBare2.setFloorType(FloorTypes.BARE);
		fcBare2.setCleaned(false);
		robot.addToBreadCrumbPowerNeeded(p2);
		assertEquals(2, robot.getBreadCrumbPowerNeeded(),0);
		}
	

	@Test
	public void testAddToBreadCrumbPowerNeededBareLow() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		Point p= new Point(0, 1);
		FloorCell fcBare = robot.getFloorPlan().getCellByPoint(p);
		fcBare.setFloorType(FloorTypes.BARE);
		fcBare.setCleaned(false);
		robot.Move(p);
		robot.addToBreadCrumbPowerNeeded(p);
		
		Point p2= new Point(0, 2);
		FloorCell fclow = robot.getFloorPlan().getCellByPoint(p2);
		fclow.setFloorType(FloorTypes.LOW);
		fclow.setCleaned(false);
		robot.addToBreadCrumbPowerNeeded(p2);
		assertEquals(3, robot.getBreadCrumbPowerNeeded(),0);
		}

	@Test
	public void testAddToBreadCrumbPowerNeededBareHigh() throws ParserConfigurationException, SAXException, IOException, IllegalArgumentException  {
		robot =null;
		initRobot(); 
		Point p= new Point(0, 1);
		FloorCell fcBare = robot.getFloorPlan().getCellByPoint(p);
		fcBare.setFloorType(FloorTypes.BARE);
		fcBare.setCleaned(false);
		robot.Move(p);
		robot.addToBreadCrumbPowerNeeded(p);
		
		Point p2= new Point(0, 2);
		FloorCell fcHigh = robot.getFloorPlan().getCellByPoint(p2);
		fcHigh.setFloorType(FloorTypes.HIGH);
		fcHigh.setCleaned(false);
		robot.addToBreadCrumbPowerNeeded(p2);
		assertEquals(4, robot.getBreadCrumbPowerNeeded(),0);
		}

	private void initRobot()throws ParserConfigurationException, SAXException,IOException  {
		if(robot==null) {
			FloorPlan fp = (ParserFloorPlan.runParser("xml3x3.xml"));
			robot=new Robot(0,0,fp);
		}

	}
}
