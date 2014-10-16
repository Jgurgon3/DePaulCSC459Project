package CleanSweepModels;
import CleanSweepModels.Types.*;

public class FloorFactory {
    private FloorFactory() {}

    static public Floor newFloor(Point location, FloorTypes surface, int unitsOfDirt, boolean northOpen,
    boolean eastOpen, boolean southOpen, boolean westOpen) {

        Floor fo = new Floor(location, surface, unitsOfDirt, northOpen, eastOpen, southOpen, westOpen);
        return fo;
    }
}
