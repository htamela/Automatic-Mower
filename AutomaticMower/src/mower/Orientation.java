package mower;

public interface Orientation {
	public void goDirection(Area area,Coordinate coordinate);
	
	public class North implements Orientation{
		@Override
		public void goDirection(Area area,Coordinate coordinate) {
			int y = area.getTopRight().getY();
			if(coordinate.getY() < y) {
				coordinate.setY(coordinate.getY()+1);
			}
		}
	}
	public class South implements Orientation{
		@Override
		public void goDirection(Area area,Coordinate coordinate) {
			int y = area.getBottomLeft().getY();
			if(coordinate.getY() > y) {
				coordinate.setY(coordinate.getY()-1);
			}
		}
	}
	public class East implements Orientation{
		@Override
		public void goDirection(Area area,Coordinate coordinate) {
			int x = area.getTopRight().getX();
			if(coordinate.getX() < x) {
				coordinate.setX(coordinate.getX()+1);
			}
		}
	}
	public class West implements Orientation{
		@Override
		public void goDirection(Area area,Coordinate coordinate) {
			int x = area.getBottomLeft().getX();
			if(coordinate.getX() > x) {
				coordinate.setX(coordinate.getX()-1);
			}
		}
	}
	
}
