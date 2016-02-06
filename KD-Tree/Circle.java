
public class Circle extends Figure{

	protected Node center;
	protected double radius;
	
	protected Circle(int dimension) {
		center = new Node (new Point(dimension));
		radius = 0;
	}
	
	public Circle(int[] point, double radius){
		center = new Node (new Point(point));
		this.radius = radius;
	}
	public Circle(Node node, double radius){
		this.center = node;
		this.radius = radius;
		node.figure = this;
	}

	@Override
	public String toString() {
		return "Circle [rad=" + radius + ", center=" + center + "]";
	}
}
