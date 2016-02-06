import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreePrinter {
	private static final int DATA_LENGTH = 10;

	public static void printNode(KDTree tree) {
		int maxLevel = tree.getHeight();
		// Returns an immutable list containing only the specified object
		printNode(Collections.singletonList(tree.root), 1, maxLevel);
	}

	private static void printWhitespaces(int count) {
		for (int i = 0; i < count; i++)
			System.out.print(" ");
	}

	private static boolean isAllElementsNull(List<Node> list) {
		for (Object object : list) {
			if (object != null)
				return false;
		}

		return true;
	}

	private static void printNode(List<Node> nodes, int level, int maxLevel) {
		if (nodes.isEmpty() || TreePrinter.isAllElementsNull(nodes))
			return;

		int floor = maxLevel - level;
		int slashes = calcSlashes(floor);
		int firstSpaces = calcSpaceFirst(floor + 1);
		int betweenSpaces = calcSpaceBetween(floor + 1);

		printWhitespaces(firstSpaces);

		List<Node> newNodes = new ArrayList<Node>();
		printAndAddNewNodes(nodes, newNodes, betweenSpaces);
		System.out.println();
		printSlashes(slashes, firstSpaces, nodes, calcSpaceBetween(floor - 1));
		// TreePrinter.printWhitespaces(betweenSpaces);

		printNode(newNodes, level + 1, maxLevel);

	}

	private static void printAndAddNewNodes(List<Node> nodes, List<Node> newNodes, int betweenSpaces) {
		for (Node node : nodes) {
			if (node != null) {
				System.out.print(node);
				newNodes.add(node.left);
				newNodes.add(node.right);
			} else {
				newNodes.add(null);
				newNodes.add(null);
				printWhitespaces(DATA_LENGTH);
			}
			printWhitespaces(betweenSpaces);

		}
	}

	private static void printSlashes(int slashes, int firstSpaces, List<Node> nodes, int betweenSpacesNext) {
		int betweenSpacesSlashes = betweenSpacesNext + DATA_LENGTH;
		int firstSpacesSlashes = firstSpaces + (DATA_LENGTH / 2 + 1);
		for (int i = 1; i <= slashes; i++) {
			for (int j = 0; j < nodes.size(); j++) {
				printWhitespaces(firstSpacesSlashes - i);
				if (nodes.get(j) == null) {
					printWhitespaces(2 * DATA_LENGTH + betweenSpacesSlashes);
					continue;
				}

				if (nodes.get(j).left != null)
					System.out.print("/");
				else
					printWhitespaces(1);

				printWhitespaces(i + i - 1);

				if (nodes.get(j).right != null)
					System.out.print("\\");
				else
					printWhitespaces(1);
				printWhitespaces(slashes - i + betweenSpacesSlashes);
			}

			System.out.println("");
		}
	}

	private static int calcSlashes(int floor) {
		return (int) (floor >= 0 ? Math.pow(2, floor - 1) * (DATA_LENGTH / 2 + 1) : 0);
	}

	private static int calcSpaceBetween(int floor) {
		return calcSlashes(floor) * 2 + 1 - 2 * (DATA_LENGTH / 2 + 1);
	}

	private static int calcSpaceFirst(int floor) {
		return calcSlashes(floor - 1) + calcSlashes(floor - 2);
	}
}
