import java.util.HashSet;

public class SequentialList {
	private static final int INITIAL_SIZE = 2;
	private static final double SIZE_MULTIPLIER = 2;

	private int[] array;
	private int elementCount;

	public SequentialList() {
		this.array = new int[INITIAL_SIZE];
		this.elementCount = 0;
	}

	public void add(int element) {
		this.tryGrow();
		this.array[this.elementCount] = element;
		this.elementCount++;
	}

	public void insert(int element, int index) {
		if (index >= this.elementCount) {
			this.add(element);
			return;
		}
		this.tryGrow();
		for (int i = this.elementCount - 1; i >= index; i--) {
			this.array[i + 1] = this.array[i];
		}
		this.array[index] = element;
		this.elementCount++;
	}

	public void removeAt(int index) {
		for (int i = index; i < this.elementCount - 1; i++) {
			this.array[i] = this.array[i + 1];
		}
		this.elementCount--;
	}

	public int get(int index) {
		return this.array[index];
	}

	public int size() {
		return this.elementCount;
	}

	public int indexOf(int element) {
		for (int i = 0; i < this.elementCount; i++) {
			if (this.array[i] == element)
				return i;
		}
		return -1;
	}

	private void tryGrowBySize(int newSize) {
		if (newSize >= this.array.length) {
			int newLength = (int) (this.array.length * SIZE_MULTIPLIER);
			while (newSize >= newLength) {
				newLength = (int) (newLength * SIZE_MULTIPLIER);
			}
			int[] newArray = new int[newLength];
			for (int i = 0; i < this.array.length; i++) {
				newArray[i] = this.array[i];
			}
			this.array = newArray;
		}
	}

	private void tryGrow() {
		this.tryGrowBySize(this.elementCount);
	}

	public void removeAtIndex(int index) {
		index = (index + this.elementCount) % this.elementCount;
		this.removeAt(index);
	}

	public boolean removeElement(int element) {
		if (this.indexOf(element) != -1) {
			this.removeAt(this.indexOf(element));
			return true;
		}
		return false;
	}

	public SequentialList copy() {
		SequentialList newSeqList = new SequentialList();
		for (int i = 0; i < this.elementCount; i++) {
			newSeqList.add(this.array[i]);
		}
		return newSeqList;

	}

	public SequentialList reverse() {
		SequentialList newSeqList = new SequentialList();
		for (int i = this.elementCount - 1; i >= 0; i--) {
			newSeqList.add(this.array[i]);
		}
		return newSeqList;
	}

	public boolean equals(SequentialList other) {
		boolean equals = this.elementCount == other.elementCount;
		int index = 0;
		while (index < this.elementCount && equals) {
			equals = this.array[index] == other.array[index];
			++index;
		}
		return equals;
	}

	public boolean isPalindrome() {
		SequentialList reversedSeqList = this.reverse();
		return this.equals(reversedSeqList);
	}

	public SequentialList sortedMerge(SequentialList other) {
		SequentialList mergedSeqList = new SequentialList();
		int indexFirst = 0;
		int indexSecond = 0;

		while (indexFirst < this.elementCount && indexSecond < other.elementCount) {
			if (this.array[indexFirst] <= other.array[indexSecond]) {
				indexFirst = addingElement(mergedSeqList, indexFirst, this);
			} else {
				indexSecond = addingElement(mergedSeqList, indexSecond, other);
			}
		}
		if (indexFirst == this.elementCount) {
			while (indexSecond < other.elementCount){
				indexSecond = addingElement(mergedSeqList, indexSecond, other);
			}
		} else {
			while (indexFirst < this.elementCount){
				indexFirst = addingElement(mergedSeqList, indexFirst, this);
			}
		}
		return mergedSeqList;
	}

	private int addingElement(SequentialList list, int index, SequentialList from) {
		list.add(from.array[index]);
		return ++index;
	}

	public SequentialList removeDuplicates() {
		SequentialList newSeqList = new SequentialList();
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < this.elementCount; i++) {
			if (!set.contains(this.array[i])) {
				newSeqList.add(this.array[i]);
				set.add(this.array[i]);
			}
		}
		return newSeqList;
	}

	public void splice(SequentialList other, int start, int end) {
		int count = end - start;
		this.tryGrowBySize(this.elementCount + count);

		for (int i = 0; i > count; i++) {
			this.array[this.elementCount + count - i] = other.array[end - i];
		}
		for (int i = start; i <= end; i++) {
			this.array[i] = other.array[i];
		}

	}

	private int upperPowerOfTwo(int v) {
		v--;
		v |= v >> 1;
		v |= v >> 2;
		v |= v >> 4;
		v |= v >> 8;
		v |= v >> 16;
		v++;
		return v;
	}

	public SequentialList splitAt(int index) {
		int nextPowerOfTwo = upperPowerOfTwo(index);
		int[] splittedArray = new int[nextPowerOfTwo];
		SequentialList secondPartList = new SequentialList();
		for (int i = 0; i < index; i++) {
			splittedArray[i] = this.array[i];
		}
		for (int i = index; i < this.elementCount; i++) {
			secondPartList.add(this.array[i]);
		}
		this.elementCount = index;
		this.array = splittedArray;
		return secondPartList;
	}

	@Override
	public String toString() {
		String answer = "";
		for (int i = 0; i < this.elementCount; i++) {
			answer += Integer.toString(i) + ": " + Integer.toString(this.array[i]) + ", ";  
		}
		return answer;
	}
	

}