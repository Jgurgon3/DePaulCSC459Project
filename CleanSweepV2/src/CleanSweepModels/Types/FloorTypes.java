package CleanSweepModels.Types;

public enum FloorTypes {
    BARE (0),
    LOW (1),
    HIGH (2);
    
    private final int value;
    FloorTypes(int value) { this.value = value; }
    public int getValue() { return value; }
}
