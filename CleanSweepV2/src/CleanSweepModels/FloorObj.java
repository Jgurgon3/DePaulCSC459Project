package CleanSweepModels;


class FloorObj implements Floor{
	private final Point _location;
	private final FloorTypes _surface;
	private boolean _containsDirt;
	private boolean _northCellOpen;
	private boolean _eastCellOpen;
	private boolean _southCellOpen;
	private boolean _westCellOpen;
	private boolean _visited = false;
	private int _unitsOfDirt;

	FloorObj(Point location, FloorTypes surface, int unitsOfDirt, boolean northOpen,
			boolean eastOpen, boolean southOpen, boolean westOpen) {

		_location = location;
		_surface = surface;
		_unitsOfDirt = unitsOfDirt;
		_northCellOpen = northOpen;
		_eastCellOpen = eastOpen;
		_southCellOpen = southOpen;
		_westCellOpen = westOpen;

		if (_unitsOfDirt > 0  )
			_containsDirt =  true;
		else
			_containsDirt = false;

	}
	public Point getLocation()
	{
		return _location;
	}

	public FloorTypes getSurface()
	{
		return _surface;
	}

	public boolean containsDirt(){
		return _containsDirt;
	}

	public boolean northCellOpen()
	{
		return _northCellOpen;
	}

	public boolean eastCellOpen()
	{
		return _eastCellOpen;
	}

	public boolean southCellOpen()
	{
		return _southCellOpen;
	}

	public boolean westCellOpen()
	{
		return _westCellOpen;
	}

	public void setVisited()
	{
		_visited = true;
	}

	public boolean visited()
	{
		return _visited;
	}

	public void cleanedUnitOfDirt()
	{
		if (_unitsOfDirt > 0)
			_unitsOfDirt--;

		if (_unitsOfDirt == 0)
			_containsDirt = false;
	}
}