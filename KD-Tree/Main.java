public class Main {

	public static void main(String[] args) throws KeySizeException, KeyMissingException {
		int[][] data = new int[][] { { 4, 8 }, { 1, 3 }, { 2, 10 }, { 2, 8 }, { 6, 6 }, { 1, 4 }, { 9, 1 }, { 10, 5 },
				{ 11, 2 }, { 7, 1 }, { 12, 0 } };
		KDTree tree = new KDTree(2, data);
		tree.print();
		tree.delete(new int[] { 4, 8 });
		tree.print();
		double[] distance = new double[1];
		Node.nearestNeighbour(tree.root, new Point(new int[]{1, 2}), 2);
	}
}
