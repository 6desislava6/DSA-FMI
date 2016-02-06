import java.util.ArrayList;
import java.util.Iterator;


public class IntersectingRectanglesSelector {

	public static ArrayList<Rectangle> selectRectangles(KDTree tree, Point intersectingPoint){
		ArrayList<Node> potentialNodes = selectNodes(tree, intersectingPoint);
		ArrayList<Rectangle> rectangles = new ArrayList<>();
		for (Node node : potentialNodes) {
			if (node.figure instanceof Rectangle && intersectingPoint.isInside((Rectangle) node.figure))
				rectangles.add((Rectangle) node.figure);
		}
		
		return rectangles;
		
	}
	protected static ArrayList<Node> selectNodes(KDTree tree, Point intersectingPoint){
		ArrayList<Node> potentialPoints = new ArrayList<>();
		ArrayList<Node> nodesToBeVisited = new ArrayList<>();
		nodesToBeVisited.add(tree.root);
		selectNodes(nodesToBeVisited, potentialPoints, intersectingPoint, tree.getHeight(), 0, tree.k);
		return potentialPoints;
	}
	
	private static void selectNodes(ArrayList<Node> nodesToBeVisited, ArrayList<Node> potentialNodes,
											Point intersectingPoint, int maxLevel, int depth, int k){
		if (nodesToBeVisited.isEmpty()){
			return;
		}
		
		ArrayList<Node> newNodesToBeVisited = new ArrayList<Node>();
		for (Node node : nodesToBeVisited) {
			if (node == null)
				continue;
			if(depth % 2 == 0){
				if (intersectingPoint.coord[depth] >= node.key.coord[depth]){
					newNodesToBeVisited.add(node.left);
					newNodesToBeVisited.add(node.right);
					if (node.key.coord[(depth + 1) % k] >= intersectingPoint.coord[(depth + 1) % k])
						potentialNodes.add(node);
				} else {
					newNodesToBeVisited.add(node.left);
				}
			} else {
				if (intersectingPoint.coord[depth] <= node.key.coord[depth]){
					newNodesToBeVisited.add(node.left);
					newNodesToBeVisited.add(node.right);
					if (node.key.coord[(depth + 1) % k] <= intersectingPoint.coord[(depth + 1) % k])
						potentialNodes.add(node);
				} else {
					newNodesToBeVisited.add(node.right);
				}
			}
			
		}
		selectNodes(newNodesToBeVisited, potentialNodes, intersectingPoint, maxLevel, (depth + 1) % k, k);
	}
}
