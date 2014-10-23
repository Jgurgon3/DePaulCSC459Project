package cleansweep.service;


import java.util.List;

import CleanSweepModels.Robot;
import XMLParse.FloorCell;

public class PowerService {
//	private List<FloorCell> floorCellList;
//	private Double power;
//	private Boolean processed;
//	private Robot robot;
//	private Boolean printLog=true;
//	/*power required for three purpose
//	 * 	1. For moving to next cell
//	 * 	2. Cleaning the cell
//	 *  3. Moving to charing station
//	 *  before process any cell it will calculate power required for all three purpose
//	 *  If available power is available more that sum of three then process the  cell 
//	 */	
//	
//	public PowerService (List<FloorCell> _floorCellList,Double _powerUnitOnStart,Robot _robot )
//	{
//		floorCellList=_floorCellList;
//		power =_powerUnitOnStart;
//		robot =_robot ;
//	}
//	public Boolean getPrintLog() {
//		return printLog;
//	}
//
//	public void setPrintLog(Boolean printLog) {
//		this.printLog = printLog;
//	}
//
//	public void Process()
//	{
//	   this.processed=false;	
//	   Boolean powerAvailable= true;	
//	   FloorCell prevfloorCell=null;
//	   for (FloorCell floorCell : floorCellList) {
//		   if(powerAvailable)
//		   {
//			int cleanPower= getPowerForCellClean( floorCell);
//			Double movePower= getPowerForCellMoving( floorCell,prevfloorCell);
//			Double moveCSPower = 21.0; //TODO: Someone need to write a logic the cell need to travel CS
//			if((cleanPower+movePower+moveCSPower)<power  )
//				{
//					this.power= this.power- movePower-cleanPower;
//					if(printLog)
//						{
//						  String logString = "Cell(" +floorCell.getCoordinates().getX() + "," + floorCell.getCoordinates().getY() + ")FloorType:" + floorCell.getFloorType() + " Power after clean:" + power;
//						  logString =logString + "(To Clean :" + cleanPower  + ",To move:" + movePower.toString() + ") ";
//						  System.out.println(logString);
//						}
//					floorCell.setCleaned(true);
//			   }
//			else
//			{
//				powerAvailable=false; //not enough power need not process more
//				if(printLog)
//				{
//				  String logString = "Not enough power for Cell(" +floorCell.getCoordinates().getX() + "," + floorCell.getCoordinates().getY() + ")FloorType:" + floorCell.getFloorType() + " Power available:" + power;
//				  logString =logString + "(To Clean :" + cleanPower  + ",To move:" + movePower.toString() + "Power to Move"+ moveCSPower.toString() + ") ";
//				  System.out.println(logString);
//				}
//			}
//		prevfloorCell = floorCell;
//	    }
//	   }
//	   this.processed=true; 
//	}
//	
//	private int getPowerForCellClean(FloorCell floorCell)
//	{
//		//1 unit battery for cleaning bare floor 
//		//2 unit battery for cleaning low pile carpet floor 
//		//3 unit battery for cleaning high pile carpet floor 
//		
//		//1 The cell is bare floor.
//		//2 The cell is covered in low-pile carpet.
//		//4 The cell is covered in high-pile carpet.
//
//		if(floorCell.alreadyCleaned() == true)
//			return 0;  // just move to next cell. 
//		
//		
//		return floorCell.getFloorType().getValue();
//	}
//	
//	private Double getPowerForCellMoving(FloorCell floorCell ,FloorCell prevCell )
//	{
//		// 1 unit of power for moving between bare floors to bare floor 
//		//1.5 unit of power for moving between bare floor to low pile carpet floor
//		//2 unit of power for moving between bare floor to high pile carpet floor
//		//2.5 unit of power for) moving between low pile carpet floor to high pile carpet floor 
//		//3 unit of power for moving between high pile carpet floor to high pile carpet floor
//		if(prevCell==null)
//			return 0.0; // When we are at cell zero we need not move , just celan it
//		else
//		{
//		Double powerUnitPrev =0.0;
//		Double powerUnitCurrent =0.0;
//		switch(prevCell.getFloorType())
//		{
//		case 1:
//			powerUnitPrev=.5;
//			break;
//		case 2:
//			powerUnitPrev=1.0;
//			break;
//
//		case 4:
//			powerUnitPrev=1.5;
//			break;
//	
//		}
//		
//		switch(floorCell.getFloorType())
//		{
//		case 1:
//			powerUnitCurrent=.5;
//			break;
//		case 2:
//			powerUnitCurrent=1.0;
//			break;
//
//		case 4:
//			powerUnitCurrent=1.5;
//			break;
//	
//		}
//		
//		return powerUnitPrev + powerUnitCurrent;
//		}
//	}
//	public List<FloorCell> getFloorCellList() {
//		return floorCellList;
//	}
//
//	public void setFloorCellList(List<FloorCell> floorCellList) {
//		this.floorCellList = floorCellList;
//	}
//
//	public Double getPower() {
//		return power;
//	}
//
//	public void setPower(Double power) {
//		this.power = power;
//	}
//
//	public Boolean getProcessed() {
//		return processed;
//	}
//
//	public void setProcessed(Boolean processed) {
//		this.processed = processed;
//	}
//
//	public Robot getRobot() {
//		return robot;
//	}
//
//	public void setRobot(Robot robot) {
//		this.robot = robot;
//	}


}

