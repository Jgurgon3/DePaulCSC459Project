package CleanSweepModels.Types;

public enum FloorTypes {
    BARE (1),
    LOW (2),
    HIGH (3);
    
    private final int value;
    FloorTypes(int value) { this.value = value; }
    public int getValue() { return value; }
}
