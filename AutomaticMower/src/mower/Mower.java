package mower;

public class Mower {
	private final Coordinate position;
	private String orientation;
	private final String[] orientations= {"N","E","S","W"};
	private int index;
	
	public Mower(Coordinate position, String orientation) {
		this.position = position;
		this.orientation = orientation;
		update();
	}
	private void update() {
		switch(orientation) {
		case "N" : index = 0;break;
		case "E" : index = 1;break;
		case "S" : index = 2;break;
		case "W" : index = 3;break;
		default : throw new IllegalArgumentException("Unknown orientation"+orientation);
		}
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
		update();
	}
	public Coordinate getPosition() {
		return position;
	}
	@Override
	public String toString() {
		return position.getX()+" "+position.getY()+" "+orientation;
	}
	public void turnRight() {
		index = (index + 1)%4; 
		orientation = orientations[index];
	}
	public void turnLeft() {
		index = (index + 3)%4; // 3 = -1 +4
		orientation = orientations[index];
	}

}
