
public class CircleWrapper extends Rectangle {
	protected Circle circle;
	protected CircleWrapper(int dimension) {
		super(dimension);
	}
	public CircleWrapper(Circle circle){
		super(new Node(new Point(new int[] {(int) (circle.center.key.coord[0] + circle.radius), (int) (circle.center.key.coord[1] + circle.radius)})),
				new Node(new Point(new int[] {(int) (circle.center.key.coord[0] - circle.radius), (int) (circle.center.key.coord[1] - circle.radius)})));
		this.circle = circle;
	}
	public CircleWrapper(Node search, Node search2, Circle circle) {
		super(search, search2);
		this.circle = circle;
	}

}
