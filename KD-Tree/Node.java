import java.util.ArrayList;
import java.util.Arrays;

import org.w3c.dom.css.Rect;

// http://www.geeksforgeeks.org/k-dimensional-tree-set-3-delete/
public class Node {
	protected Point key;
	protected Node left, right;
	protected Figure figure;

	public Node(Point key) {
		this.key = key;
		left = null;
		right = null;
		figure = null;
	}

	public Node(Point key, Figure figure) {
		this.key = key;
		left = null;
		right = null;
		this.figure = figure;
	}

	public static Node constructKDTree(int depth, int k, ArrayList<Point> points) {
		if (points.isEmpty()) {
			return null;
		}
		int[] xs = makeXs(points, depth);

		int medianIndex = MedianOfMedians.findMedian(xs);

		Node nodeMedian = new Node(points.get(medianIndex));
		int medianValue = nodeMedian.key.coord[depth];
		Node node = new Node(points.get(medianIndex));

		ArrayList<Point> beforePoints = new ArrayList<>();
		ArrayList<Point> afterPoints = new ArrayList<>();

		// Разделяме точките на две половини.
		delegatePoints(points, beforePoints, afterPoints, nodeMedian.key, depth);
		// Построяваме лявата и дясната част, като сме разделили точките.
		node.left = constructKDTree((depth + 1) % k, k, beforePoints);
		node.right = constructKDTree((depth + 1) % k, k, afterPoints);
		return node;
	}

	@Override
	public String toString() {

		return Arrays.toString(this.key.coord) + "*";
	}

	public static void delegatePoints(ArrayList<Point> points, ArrayList<Point> beforePoints,
			ArrayList<Point> afterPoints, Point medianPoint, int depth) {
		for (int i = 0; i < points.size(); i++) {
			if (points.get(i).coord[depth] < medianPoint.coord[depth]) {
				beforePoints.add(points.get(i));
			} else if (points.get(i).coord[depth] > medianPoint.coord[depth]) {
				afterPoints.add(points.get(i));
			} else if (points.get(i) != medianPoint) {
				afterPoints.add(points.get(i));
			}
		}
	}

	protected static int[] makeXs(ArrayList<Point> points, int depth) {
		int[] xs = new int[points.size()];
		for (int i = 0; i < xs.length; i++) {
			xs[i] = points.get(i).coord[depth];
		}
		return xs;
	}

	protected static Node insert(Point key, Node node, int depth, int k) throws KeyDuplicateException {
		if (node == null) {
			// стигнали сме листо
			node = new Node(key);
		} else if (key.equals(node.key)) {
			throw new KeyDuplicateException();

		} else if (key.coord[depth] > node.key.coord[depth]) {
			// ако е по-голям отива вдясното поддърво
			node.right = insert(key, node.right, (depth + 1) % k, k);
			// Нивата са толкова, защото при всяка координата делим, като така и
			// добавяме ниво
			// т.е. нивата ще са от 0 - корен, до k - "децата" на "медианата",
			// получена при последния
		} else {
			node.left = insert(key, node.left, (depth + 1) % k, k);
		}
		return node;
	}

	protected static Node search(Point key, Node node, int k) {
		// Търсим node и слизаме надолу, докато не го намерим.
		Node searched = null;
		for (int depth = 0; node != null; depth = (depth + 1) % k) {
			if (key.equals(node.key)) {
				searched = node;
				break;
			} else if (key.coord[depth] >= node.key.coord[depth]) {
				node = node.right;
			} else {
				node = node.left;
			}
		}
		return searched;
	}

	protected static Node delete(Point key, Node parent, Node node, int depth, int k) {
		if (node == null) {
			return null;
		}
		if (key.equals(node.key)) {
			if (parent != null) {
				deleteParentNotNull(node, parent);
			}
			if (node.right != null) {
				Node min = findMin(node.right, depth, k);
				copyPoint(node.key, min.key);
				node.right = delete(min.key, node, node.right, (depth + 1) % k, k);
			} else if (node.left != null) {
				Node min = findMin(node.left, depth, k);
				copyPoint(node.key, min.key);
				node.right = delete(min.key, node, node.left, (depth + 1) % k, k);
			}
		} else if (key.coord[depth] >= node.key.coord[depth]) {
			delete(key, node, node.right, (depth + 1) % k, k);
		} else {
			delete(key, node, node.left, (depth + 1) % k, k);
		}
		return node;
	}

	private static void deleteParentNotNull(Node node, Node parent) {
		if (node.left == null && node.right == null) {
			if (parent.left == node) {
				parent.left = null;
			} else {
				parent.right = null;
			}
		}
	}

	protected static Node delete(Point key, Node node, int k) {
		return delete(key, null, node, 0, k);
	}

	private static void copyPoint(Point nodeKey, Point minKey) {
		nodeKey.coord = minKey.coord.clone();
		return;
	}

	// Търсим минимума по някоя координата - dimension, като постепенно влизаме
	// по-надолу
	private static Node findMin(Node node, int dimension, int depth, int k) {
		if (node == null)
			return null;

		if (depth == dimension) {
			// Ако няма по-малки (които по принцип са вляво) го връщаме него
			if (node.left == null)
				return node;
			return findMin(node.left, dimension, (depth + 1) % k, k);
		}
		return min(node, findMin(node.left, dimension, (depth + 1) % k, k),
				findMin(node.right, dimension, (depth + 1) % k, k), dimension);
	}

	private static Node findMin(Node node, int dimension, int k) {
		return findMin(node, dimension, 0, k);
	}

	protected static Node min(Node nodeFirst, Node nodeSecond, Node nodeThird, int dimension) {
		Node res = nodeFirst;
		if (nodeSecond != null && nodeSecond.key.coord[dimension] < res.key.coord[dimension])
			res = nodeSecond;
		if (nodeThird != null && nodeThird.key.coord[dimension] < res.key.coord[dimension])
			res = nodeThird;
		return res;
	}

	public static void nearestNeighbour(Node root, Point pointTarget, int k) {
		double[] res = new double[] { Double.MAX_VALUE };
		Node []resultNode = {new Node(root.key)};
		nearestNeighbour(root, resultNode, pointTarget, res, 0, k);
		System.out.println(res);
	}

	// Първо отиваме към по-близкия клон
	protected static void nearestNeighbour(Node node, Node[] resultNode, Point pointTarget, double[] distance, int depth,
			int k) {
		if (node == null)
			return;
		
		if (node.left == null && node.right == null) {
			double newDistance = Math.abs(Point.sqrdist(node.key, pointTarget));
			if (newDistance < distance[0]) {
				distance[0] = newDistance;
				resultNode[0] = new Node(node.key);
			}
			return;
		}
		if (pointTarget.coord[depth] <= node.key.coord[depth]) {
			if (pointTarget.coord[depth] - distance[0] <= node.key.coord[depth])
				nearestNeighbour(node.left, resultNode, pointTarget, distance, (depth + 1) % k, k);
			if (pointTarget.coord[depth] + distance[0] > node.key.coord[depth])
				nearestNeighbour(node.right, resultNode, pointTarget, distance, (depth + 1) % k, k);
		} else {
			if (pointTarget.coord[depth] + distance[0] > node.key.coord[depth])
				nearestNeighbour(node.right, resultNode, pointTarget, distance, (depth + 1) % k, k);
			if (pointTarget.coord[depth] - distance[0] <= node.key.coord[depth])
				nearestNeighbour(node.left, resultNode, pointTarget, distance, (depth + 1) % k, k);
		}

	}

}
