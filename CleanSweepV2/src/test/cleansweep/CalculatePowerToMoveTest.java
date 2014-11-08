package test.cleansweep;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import CleanSweepModels.*;
import CleanSweepModels.Types.*;
import XMLParse.FloorCell;

public class CalculatePowerToMoveTest 
{

	private Robot robot =null;
	private FloorPlan fp =null;
	
	
	@Test
	public void testcalculatePowerToMoveHighLow() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot();
		FloorCell fcHigh = fp.getCellByPoint(new Point(1, 0)) ;
		fcHigh.setFloorType(FloorTypes.HIGH);
		robot.Move(fcHigh,true);
		FloorCell fclow = fp.getCellByPoint(new Point(0, 0)) ;
		fclow.setFloorType(FloorTypes.LOW);
	    double powerToMove=  robot.calculatePowerToMove(new Point(0, 0));
  	    assertEquals(3.0, powerToMove,0);
	}

	@Test
	public void testcalculatePowerToMoveHighHigh() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot();
		FloorCell fcHigh = fp.getCellByPoint(new Point(1, 0)) ;
		fcHigh.setFloorType(FloorTypes.HIGH);
		robot.Move(fcHigh,true);
		FloorCell fclow = fp.getCellByPoint(new Point(0, 0)) ;
		fclow.setFloorType(FloorTypes.HIGH);
	    double powerToMove=  robot.calculatePowerToMove(new Point(0, 0));
  	    assertEquals(4.0, powerToMove,0);
	}

	@Test
	public void testcalculatePowerToMoveHighBare() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot();
		FloorCell fcHigh = fp.getCellByPoint(new Point(1, 0)) ;
		fcHigh.setFloorType(FloorTypes.HIGH);
		robot.Move(fcHigh,true);
		FloorCell fclow = fp.getCellByPoint(new Point(0, 0)) ;
		fclow.setFloorType(FloorTypes.BARE);
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
		FloorCell fclow = fp.getCellByPoint(new Point(0, 0)) ;
		fclow.setFloorType(FloorTypes.BARE);
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
	    double powerToMove=  robot.calculatePowerToMove(new Point(0, 0));
  	    assertEquals(2.0, powerToMove,0);
	}
	private void initRobot()throws ParserConfigurationException, SAXException,IOException 
	{
		if(robot==null)
		{
		fp = (XMLParse.ParserFloorPlan.runParser("XMLParse/xml3x3.xml"));
		robot=fp.getRobot();
		
		}
		
	}
}
