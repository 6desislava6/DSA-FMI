import java.util.Arrays;

public class MedianOfMedians {

	public static int findMedian(int arr[]) {
		int[] originalArray = copyArray(arr);

		int indexSorted = findMedian(arr, (arr.length) / 2 + 1, 0, arr.length - 1);
		int indexOriginal = -1;
		for (int i = 0; i < originalArray.length; i++) {
			if(originalArray[i] == arr[indexSorted]){
				indexOriginal = i;
				break;
			}
		}
		return indexOriginal;
	}
	private static int[] copyArray(int[] arr){
		int[] copy = new int[arr.length];
		for (int i = 0; i < copy.length; i++) {
			copy[i] = arr[i];
		}
		return copy;
	}
	// k - очаквана медиана
	private static int findMedian(int arr[], int k, int low, int high) {

		if (low == high) {
			return low;
		}

		int m = partition(arr, low, high);
		int length = m - low + 1;

		// Ако м-тият елемент е медиана, го връщаме.
		if (length == k) {
			return m;
		}

		// Ако е по-голям от медианата, гледаме в левия подмасив.
		if (length > k) {
			return findMedian(arr, k, low, m - 1);
		}
		// Обратно.
		else {
			return findMedian(arr, k - length, m + 1, high);
		}

	}

	private static int partition(int arr[], int low, int high) {
		// Намираме медианата на медианите.
		int pivotValue = getPivotValue(arr, low, high);

		while (low < high) {
			while (arr[low] < pivotValue) {
				low++;
			}

			while (arr[high] > pivotValue) {
				high--;
			}

			if (arr[low] == arr[high]) {
				low++;
			} else if (low < high) {
				int temp = arr[low];
				arr[low] = arr[high];
				arr[high] = temp;
			}

		}
		return high;
	}

	private static int getPivotValue(int arr[], int low, int high) {
		if (high - low + 1 <= 9) {
			Arrays.sort(arr);
			return arr[arr.length / 2];
		}

		int temp[] = null;

		// държи медианите на подмасивите.
		int medians[] = new int[(int) Math.ceil((int) (high - low + 1) / 5) + 1];
		int medianIndex = 0;

		// Правим подмасивите temp, записване в medians само медианите им.
		while (low <= high) {
			temp = new int[Math.min(5, high - low + 1)];

			for (int j = 0; j < temp.length && low <= high; j++) {
				temp[j] = arr[low];
				low++;
			}
			Arrays.sort(temp);
			int l = temp.length / 2;
			medians[medianIndex] = temp[temp.length / 2];
			medianIndex++;
		}

		return getPivotValue(medians, 0, medians.length - 1);
	}

	/*public static void main(String args[]) {
		int nElements = 9;
		int arr[] = {   6, 7, 8, 9, 1, 2, 3, 4, 10 };

		System.out.print("Array");
		printArray(arr, 0, nElements);

		int median = MedianOfMedians2.findMedian(arr);

		java.util.Arrays.sort(arr);
		System.out.print(" Sorted Array - ");
		printArray(arr, 0, nElements);
		System.out.println("Median = " + median);
	}*/

	private static void printArray(int arr[], int low, int high) {
		System.out.print("[  ");
		for (int i = low; i < high; i++) {
			System.out.print(arr[i] + "  ");
		}
		System.out.println("]");
	}

}