
public class Point {
	// Координатите на точката.
	protected int[] coord;

	

	protected Point(int n) {

		this.coord = new int[n];
	}

	protected Point(int[] x) {

		coord = x.clone();
	}

	protected Point clone() {

		return new Point(coord);
	}

	protected boolean equals(Point p) {

		for (int i = 0; i < coord.length; ++i)
			if (coord[i] != p.coord[i])
				return false;

		return true;
	}

	protected static double sqrdist(Point x, Point y) {
		return Math.sqrt(dist(x.coord, y.coord));
	}
	
	
	protected static int dist(int[] a, int[] b) {

		int dist = 0;
		for (int i = 0; i < a.length; ++i) {
			int diff = (a[i] - b[i]);
			dist += diff * diff;
		}
		return dist;
	}

	//Само за 2D точки :(
	protected boolean isInside(Rectangle rect){
		return rect.max.key.coord[0] <= this.coord[0] && rect.max.key.coord[1] >= this.coord[1] &&
				rect.min.key.coord[0] >= this.coord[0] && rect.min.key.coord[1] <= this.coord[1];
	}
	
	protected boolean isInside(Circle circle){
		return sqrdist(circle.center.key, this) <= circle.radius;
	}
}
