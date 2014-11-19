package src.main.java.XMLParse;

public enum FloorTypes {
	BARE(1), LOW(2), HIGH(4);

	private final int value;

	FloorTypes(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static FloorTypes setValue(String x) {
		int intX = Integer.parseInt(x);
		switch (intX) {
		case 1:
			return BARE;
		case 2:
			return LOW;
		case 4:
			return HIGH;
		}
		return null;
	}
}
