package CleanSweepModels;



public interface Floor {
    public Point getLocation();

    public FloorTypes getSurface();

    public boolean containsDirt();

    public boolean northCellOpen();

    public boolean eastCellOpen();

    public boolean southCellOpen();

    public boolean westCellOpen();

    public void setVisited();

    public boolean visited();

    public void cleanedUnitOfDirt(); //subtracts a unit of dirt from the cell
}
