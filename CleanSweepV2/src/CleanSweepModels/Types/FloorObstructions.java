package CleanSweepModels.Types;

public enum FloorObstructions {
    UNKNOWN (0),
    OPEN (1),
    OBSTACLE (2),
    STAIRS (3);
    
    private final int value;
    FloorObstructions(int value) { this.value = value; }
    public int getValue() { return value; }
}