import java.util.ArrayList;
import java.util.Arrays;

public class KDTree {
	// number of dimensions
	protected int k;
	protected Node root;
	private int countNodes;

	public KDTree(int k) {
		this.k = k;
		this.root = null;
	}

	public KDTree(int k, int[][] data) {
		ArrayList<Point> points = new ArrayList<Point>();
		for (int[] coordinates : data) {
			points.add(new Point(coordinates));
		}
		this.countNodes = data.length;
		KDTree(k, points);
	}

	public void KDTree(int k, ArrayList<Point> points) {
		this.k = k;
		// Намираме медианата, тя ще е коренът.
		int[] xs = Node.makeXs(points, 0);
		int medianIndex = MedianOfMedians.findMedian(xs);

		this.root = new Node(points.get(medianIndex));
		int medianValue = root.key.coord[0];
		ArrayList<Point> beforePoints = new ArrayList<>();
		ArrayList<Point> afterPoints = new ArrayList<>();
		// Разделяме точките на две половини.
		Node.delegatePoints(points, beforePoints, afterPoints, root.key, 0);
		// Построяваме лявата и дясната част, като сме разделили точките.
		root.right = Node.constructKDTree(1, k, afterPoints);
		root.left = Node.constructKDTree(1, k, beforePoints);

	}

	public void insert(int[] key) throws KeySizeException, KeyDuplicateException {
		if (key.length != k) {
			throw new KeySizeException();
		} else {
			this.root = Node.insert(new Point(key), root, 0, this.k);
		}
		this.countNodes++;

	}

	public Node search(int[] key) throws KeySizeException {
		if (key.length != k) {
			throw new KeySizeException();
		}
		Node node = Node.search(new Point(key), root, k);
		return node;
	}

	public void delete(int[] key) throws KeySizeException, KeyMissingException {

		if (key.length != k) {
			throw new KeySizeException();
		}

		else {
			Node node = Node.search(new Point(key), root, k);
			if (node == null) {
				throw new KeyMissingException();
			} else {
				Node.delete(new Point(key), root, k);
				countNodes--;
			}
		}
	}

	public Object[] nearestNeighbours(int[] key, int count) throws KeySizeException {
		if (key.length != k) {
			throw new KeySizeException();
		}
		Object[] neighbours = new Object[count];
		if (count < 0 || count > this.countNodes) {
			throw new IllegalArgumentException("Number of neighbors cannot"
					+ " be negative or greater than number of nodes " + Integer.toString(count));
		}

		return neighbours;

	}
	public int getHeight() {
		return getHeight(root, 0);
	}

	private int getHeight(Node currentNode, int current) {
		if (currentNode == null)
			return current;
		int left = getHeight(currentNode.left, current + 1);
		int right = getHeight(currentNode.right, current + 1);

		return Math.max(left, right);

	}

	public void print() {
		TreePrinter.printNode(this);
	}

}
