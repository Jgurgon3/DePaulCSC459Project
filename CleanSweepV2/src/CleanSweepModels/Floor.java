package CleanSweepModels;

public class Floor {
	
	public enum FloorTypes {
		BARE,
		LOW,
		HIGH 
	}
	private boolean _isDirty;
	private FloorTypes _floorType;
	
	public Floor(boolean isDirty,FloorTypes floorType)
	{
		this.setIsDirty(isDirty);
		this.setFloorType(floorType);
	}
	public Floor setIsDirty(boolean isDirty)
	{
		this._isDirty = isDirty;
		return this;
	}
	public Floor setFloorType(FloorTypes floorType)
	{
		this._floorType = floorType;
		return this;
	}
	public boolean getIsDirty(boolean isDirty)
	{
		return this._isDirty;
		
	}
	public FloorTypes getFloorType(FloorTypes floorType)
	{
		return this._floorType;
		
	}

}
