import java.util.ArrayList;

public class IntersectingCirlce {
	public static ArrayList<Circle> selectCircles(KDTree tree, Point intersectingPoint){
		ArrayList<Node> potentialNodes = IntersectingRectanglesSelector.selectNodes(tree, intersectingPoint);
		ArrayList<Circle> circles = new ArrayList<>();
		for (Node node : potentialNodes) {
			CircleWrapper wrapper = ((CircleWrapper) node.figure);
			if (node.figure != null && node.figure instanceof CircleWrapper)
				if (intersectingPoint.isInside(wrapper.circle))
					circles.add(((CircleWrapper) node.figure).circle);
		}
		return circles;
		
	}
}
