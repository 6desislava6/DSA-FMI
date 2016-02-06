import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

public class TreeCreator {
	protected KDTree tree;
	protected KDTree treeCircles;
	private FileReader fileReader;
	protected ArrayList<Rectangle> rectangles;
	protected ArrayList<Circle> circles;
	protected ArrayList<CircleWrapper> circleWrappers;

	protected TreeCreator() throws KeySizeException, NumberFormatException, IOException {
		this.fileReader = new FileReader();

		this.tree = new KDTree(2, fileReader.data);
		this.treeCircles = new KDTree(2, fileReader.dataCircles);
		tree.print();
		treeCircles.print();

		this.rectangles = makeRectangles();
		this.circles = makeCircles();
		this.circleWrappers = makeCircleWrappers();
	}

	private ArrayList<Rectangle> makeRectangles() throws KeySizeException {
		ArrayList<Rectangle> rectangles = new ArrayList<>();
		for (int[] rectangle : fileReader.rectangles) {
			rectangles.add(new Rectangle(tree.search(new int[] { rectangle[0], rectangle[1] }),
					tree.search(new int[] { rectangle[2], rectangle[3] })));
		}

		return rectangles;

	}

	private ArrayList<Circle> makeCircles() throws KeySizeException{
		ArrayList<Circle> circles = new ArrayList<>();
		for (int[] circle : fileReader.circles) {
			circles.add(new Circle(new int[]{ circle[0], circle[1] }, circle[2]));
		}
		return circles;
	}

	private ArrayList<CircleWrapper> makeCircleWrappers() throws KeySizeException {
		ArrayList<CircleWrapper> wrappers = new ArrayList<>();
		for (int i = 0; i < fileReader.dataCircles.length / 2; i++) {
			int[] circleFirstPoint = fileReader.dataCircles[i];
			int[] circleSecondPoint = fileReader.dataCircles[++i];
			Node nodeFirst = treeCircles.search(new int[] { circleFirstPoint[0], circleFirstPoint[1] });
			Node nodeSecond = treeCircles.search(new int[] { circleSecondPoint[0], circleSecondPoint[1] });
			wrappers.add(new CircleWrapper(nodeFirst, nodeSecond, circles.get(i / 2)));
		}
		return wrappers;
	}

	protected ArrayList<Rectangle> makeIntersectingRectangles(Point intersectingPoint) {
		return IntersectingRectanglesSelector.selectRectangles(tree, intersectingPoint);
	}

	protected ArrayList<Circle> makeIntersectingCircles(Point intersectingPoint) {
		return IntersectingCirlce.selectCircles(treeCircles, intersectingPoint);
	}

}
