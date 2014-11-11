package src.main.java.XMLParse;


public enum FloorObstructions {
    UNKNOWN (0),
    OPEN (1),
    OBSTACLE (2),
    STAIRS (4);
    
    private final int value;
    FloorObstructions(int value) { this.value = value; }
    public int getValue() { return value; }
    
    public static FloorObstructions setValue(String x) {
    	int intX = Integer.parseInt(x);
        switch(intX) {
        case 0:
            return UNKNOWN;
        case 1:
            return OPEN;
        case 2:
        	return OBSTACLE;
        case 4:
        	return STAIRS;
        }
        return null;
    }
    
}