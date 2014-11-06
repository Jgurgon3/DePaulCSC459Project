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
	
	
	@Test
	public void testcalculatePowerToMoveHighLow() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot();
		robot.Move(new Point(1, 0));
		FloorCell fcHigh = robot.getMemory().get(new Point(1, 0));
		fcHigh.setFloorType(FloorTypes.HIGH);
		FloorCell fclow = robot.getMemory().get(new Point(0, 0));
		fclow.setFloorType(FloorTypes.LOW);

	    double powerToMove=  robot.calculatePowerToMove(new Point(0, 0));
  	    assertEquals(2.0, powerToMove,0);
	}

	@Test
	public void testcalculatePowerToMoveHighHigh() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot();
		robot.Move(new Point(1, 0));
		FloorCell fcHigh = robot.getMemory().get(new Point(1, 0));
		fcHigh.setFloorType(FloorTypes.HIGH);
		FloorCell fcHigh2 = robot.getMemory().get(new Point(0, 0));
		fcHigh2.setFloorType(FloorTypes.HIGH);

	    double powerToMove=  robot.calculatePowerToMove(new Point(0, 0));
  	    assertEquals(3.0, powerToMove,0);
	}

	@Test
	public void testcalculatePowerToMoveHighBare() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot();
		robot.Move(new Point(1, 0));
		FloorCell fcHigh = robot.getMemory().get(new Point(1, 0));
		fcHigh.setFloorType(FloorTypes.HIGH);
		FloorCell fcbare = robot.getMemory().get(new Point(0, 0));
		fcbare.setFloorType(FloorTypes.BARE);

	    double powerToMove=  robot.calculatePowerToMove(new Point(0, 0));
  	    assertEquals(2.0, powerToMove,0);
	}
	@Test
	public void testcalculatePowerToMoveBareBare() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot();
		robot.Move(new Point(1, 0));
		FloorCell fcbare1 = robot.getMemory().get(new Point(1, 0));
		fcbare1.setFloorType(FloorTypes.BARE);
		FloorCell fcbare2 = robot.getMemory().get(new Point(0, 0));
		fcbare2 .setFloorType(FloorTypes.BARE);

	    double powerToMove=  robot.calculatePowerToMove(new Point(0, 0));
  	    assertEquals(1.0, powerToMove,0);
	}
	
	@Test
	public void testcalculatePowerToMoveLowLow() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot();
		robot.Move(new Point(1, 0));
		FloorCell fclow1 = robot.getMemory().get(new Point(1, 0));
		fclow1.setFloorType(FloorTypes.LOW);
		FloorCell fclow2 = robot.getMemory().get(new Point(0, 0));
		fclow2  .setFloorType(FloorTypes.LOW);

	    double powerToMove=  robot.calculatePowerToMove(new Point(0, 0));
  	    assertEquals(2.0, powerToMove,0);
	}
	
	@Test
	public void testcalculatePowerToMoveBareHigh() throws ParserConfigurationException, SAXException, IOException  {
		robot =null;
		initRobot();
		robot.Move(new Point(1, 0));
		FloorCell fcBare = robot.getMemory().get(new Point(1, 0));
		fcBare.setFloorType(FloorTypes.BARE);
		FloorCell fchigh = robot.getMemory().get(new Point(0, 0));
		fchigh.setFloorType(FloorTypes.HIGH);

	    double powerToMove=  robot.calculatePowerToMove(new Point(0, 0));
  	    assertEquals(2.0, powerToMove,0);
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
