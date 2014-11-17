package src.test.java;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import src.main.java.*;
import src.main.java.CleanSweepModels.*;
import src.main.java.XMLParse.Point;
import src.main.java.XMLParse.FloorCell;
import src.main.java.XMLParse.FloorPlan;
import src.main.java.XMLParse.FloorTypes;
import src.main.java.XMLParse.ParserFloorPlan;

public class CalculatePowerToMoveTest 
{

	private Robot robot =null;
	private FloorPlan fp =null;
	
	
	@Test
	public void testcalculatePowerToMoveHighLow() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot();
		FloorCell fcHigh = fp.getCellByPoint(new Point(0, 1));
		fcHigh.setFloorType(FloorTypes.HIGH);
		fcHigh.setCleaned(false);
		robot.Move(fcHigh,true);
		FloorCell fclow = fp.getCellByPoint(new Point(0, 0)) ;
		fclow.setFloorType(FloorTypes.LOW);
		fclow.setCleaned(false);
	    double powerToMove=  robot.calculatePowerToMove(new Point(0, 0));
  	    assertEquals(3.0, powerToMove,0);
	}

	@Test
	public void testcalculatePowerToMoveHighHigh() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot();
		FloorCell fcHigh = fp.getCellByPoint(new Point(1, 0)) ;
		fcHigh.setFloorType(FloorTypes.HIGH);
		fcHigh.setCleaned(false);
		robot.Move(fcHigh,true);
		FloorCell fclow = fp.getCellByPoint(new Point(0, 0)) ;
		fclow.setFloorType(FloorTypes.HIGH);
		fclow.setCleaned(false);
	    double powerToMove=  robot.calculatePowerToMove(new Point(0, 0));
  	    assertEquals(4.0, powerToMove,0);
	}

	@Test
	public void testcalculatePowerToMoveHighBare() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot();
		FloorCell fcHigh = fp.getCellByPoint(new Point(1, 0)) ;
		fcHigh.setFloorType(FloorTypes.HIGH);
		fcHigh.setCleaned(false);
		robot.Move(fcHigh,true);
		FloorCell fclow = fp.getCellByPoint(new Point(0, 0)) ;
		fclow.setFloorType(FloorTypes.BARE);
		fclow.setCleaned(false);
	    double powerToMove=  robot.calculatePowerToMove(new Point(0, 0));
  	    assertEquals(2.0, powerToMove,0);
	}
	@Test
	public void testcalculatePowerToMoveBareBare() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot();
		FloorCell fcHigh = fp.getCellByPoint(new Point(1, 0)) ;
		fcHigh.setFloorType(FloorTypes.BARE);
		robot.Move(fcHigh,true);
		fcHigh.setCleaned(false);
		FloorCell fclow = fp.getCellByPoint(new Point(0, 0)) ;
		fclow.setFloorType(FloorTypes.BARE);
		fclow.setCleaned(false);
	    double powerToMove=  robot.calculatePowerToMove(new Point(0, 0));
  	    assertEquals(1.0, powerToMove,0);
	}
	
	@Test
	public void testcalculatePowerToMoveLowLow() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot();
		FloorCell fcHigh = fp.getCellByPoint(new Point(1, 0)) ;
		fcHigh.setFloorType(FloorTypes.LOW);
		robot.Move(fcHigh,true);
		FloorCell fclow = fp.getCellByPoint(new Point(0, 0)) ;
		fclow.setFloorType(FloorTypes.LOW);
		fclow.setCleaned(false);
		fcHigh.setCleaned(false);
	    double powerToMove=  robot.calculatePowerToMove(new Point(0, 0));
  	    assertEquals(2.0, powerToMove,0);
	}
	
	@Test
	public void testcalculatePowerToMoveBareHigh() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot();
		FloorCell fcHigh = fp.getCellByPoint(new Point(1, 0)) ;
		fcHigh.setFloorType(FloorTypes.BARE);
		robot.Move(fcHigh,true);
		FloorCell fclow = fp.getCellByPoint(new Point(0, 0)) ;
		fclow.setFloorType(FloorTypes.HIGH);
		fcHigh.setCleaned(false);
		fclow.setCleaned(false);
	    double powerToMove=  robot.calculatePowerToMove(new Point(0, 0));
  	    assertEquals(2.0, powerToMove,0);
	}
	private void initRobot()throws ParserConfigurationException, SAXException,IOException 
	{
		if(robot==null)
		{
		//fp = (ParserFloorPlan.runParser("src/main/java/CleanSweepModels/xml3x3.xml"));
		fp = (ParserFloorPlan.runParser("xml3x3.xml"));
		robot = new Robot(0, 0, fp);
		
		}
		
	}
}
