package mower;

public class Area {
	private final Coordinate bottomLeft;
	private final Coordinate topRight;
	public Area(Coordinate bottomLeft, Coordinate topRight) {
		this.bottomLeft = bottomLeft;
		this.topRight = topRight;
	}
	public Coordinate getBottomLeft() {
		return bottomLeft;
	}
	public Coordinate getTopRight() {
		return topRight;
	}
	@Override
	public String toString() {
		return "Bottom left : "+bottomLeft+"\t Top right : "+topRight;
	}


}
