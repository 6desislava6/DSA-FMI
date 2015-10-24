import java.util.HashSet;

public class LinkedList {
	Node head = null;
	Node tail = null;

	public void append(int value) {
		if (this.tail == null) {
			head = new Node(value);
			tail = head;
			tail.left = head;
			head.right = tail;
		} else {
			Node newNode = new Node(value);
			tail.right = newNode;
			newNode.left = tail;
			tail = newNode;
		}
	}

	public Node get(int index) {
		Node current = this.head;
		for (int i = 0; i < index; i++) {
			if (current == null) {
				return null;
			}
			current = current.right;
		}
		return current;
	}

	public int size() {
		Node current = head;
		int counterSize = 0;
		while (current != null) {
			current = current.right;
			++counterSize;
		}
		return counterSize;
	}

	public Node nodeOf(int value) {
		Node current = head;
		while (current != null && current.value != value) {
			current = current.right;
		}
		return current.value == value ? current : null;

	}

	public void removeAt(Node node) {
		if (node == head) {
			node.right.left = null;
			head = node.right;
		} else if (node == tail) {
			node.left.right = null;
			tail = node.left;
		} else {
			node.left.right = node.right;
			node.right.left = node.left;
		}
	}

	public boolean removeElement(int value) {
		Node searched = nodeOf(value);
		if (searched != null) {
			removeAt(searched);
			return true;
		}
		return false;
	}

	public void insert(int value, Node left) {
		if (left == tail) {
			append(value);
		} else {
			Node newNode = new Node(value);
			left.right.left = newNode;
			newNode.right = left.right;
			newNode.left = left;
			left.right = newNode;
		}
	}

	public LinkedList copy() {
		LinkedList newList = new LinkedList();
		Node current = head;
		while (current != null) {
			newList.append(current.value);
			current = current.right;
		}
		return newList;
	}

	public LinkedList reverse() {
		LinkedList newList = new LinkedList();
		Node current = tail;
		while (current != null) {
			newList.append(current.value);
			current = current.left;
		}
		return newList;
	}

	public boolean equals(LinkedList other) {
		Node current = head;
		Node otherCurrent = other.head;
		while (current != null && otherCurrent != null) {
			if (current.value != otherCurrent.value) {
				return false;
			}
			current = current.right;
			otherCurrent = otherCurrent.right;
		}
		return current == null && otherCurrent == null;
	}

	// Slow one!
	public boolean isPalindrome() {
		LinkedList reversed = this.reverse();
		return this.equals(reversed);
	}

	public LinkedList sortedMerge(LinkedList other) {
		Node nodeFirst = this.head;
		Node nodeSecond = other.head;
		LinkedList merged = new LinkedList();

		do {
			if (nodeFirst.value > nodeSecond.value) {
				appendNode(nodeFirst, merged);
			} else {
				appendNode(nodeSecond, merged);
			}
		} while (nodeFirst != this.tail || nodeSecond != other.tail);

		if (nodeFirst == this.tail) {
			while (nodeSecond != other.tail) {
				appendNode(nodeSecond, merged);
			}
		} else {
			while (nodeFirst != this.tail) {
				appendNode(nodeFirst, merged);
			}
		}
		return merged;
	}

	private void appendNode(Node node, LinkedList list) {
		list.append(node.value);
		node = node.right;
	}

	public LinkedList removeDuplicates() {
		LinkedList newList = new LinkedList();
		HashSet<Integer> set = new HashSet<Integer>();
		Node current = head;
		while (current != null) {
			if (!set.contains(current.value)) {
				set.add(current.value);
				newList.append(current.value);
			}
			current = current.right;
		}
		return newList;

	}
	@Override
	public String toString(){
		String resulting = "";
		Node current = head;

		while (current != null) {
			resulting += "Node: " + Integer.toString(current.value) + ", ";
			current = current.right;
		}
		return resulting;
		
	}
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.append(1);
		list.append(10);
		list.append(10);		
		list.append(1);
		list.append(1);
		list.append(7);
		list.append(7);
		list.append(6);
		list.append(6);
		System.out.println(list);
		LinkedList noDuplicate = list.removeDuplicates();
		System.out.println(noDuplicate);
	}

}
