class Rectangle extends Figure {

	protected Node min;
	protected Node max;

	protected Rectangle(int dimension) {
		min = new Node (new Point(dimension));
		max = new Node (new Point(dimension));
		min.figure = this;
		max.figure = this;
	}
	
	public double getHeight(){
		return max.key.coord[1] - min.key.coord[1];
	}
	
	public double getWidth(){
		return min.key.coord[0] - max.key.coord[0];
	}

	public Rectangle(int[] pointFirst, int[] pointSecond){
		min = new Node (new Point(pointFirst));
		max = new Node (new Point(pointSecond));
		min.figure = this;
		max.figure = this;
	}
	public Rectangle(Node max, Node min){
		this.min = min;
		this.max = max;
		min.figure = this;
		max.figure = this;
	}
	@Override
	public Rectangle clone(){
		return new Rectangle(max, min);
	}
	@Override
	public String toString() {
		return "Rectangle [min=" + min + ", max=" + max + "]";
	}



}