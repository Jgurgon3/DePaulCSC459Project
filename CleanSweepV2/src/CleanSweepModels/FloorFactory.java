package CleanSweepModels;

public class FloorFactory {
    private FloorFactory() {}

    static public FloorObj newFloor(Point location, FloorTypes surface, int unitsOfDirt, boolean northOpen,
    boolean eastOpen, boolean southOpen, boolean westOpen) {

        FloorObj fo = new FloorObj(location, surface, unitsOfDirt, northOpen, eastOpen, southOpen, westOpen);
        return fo;
    }
}
