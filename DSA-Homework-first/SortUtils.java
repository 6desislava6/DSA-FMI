import java.util.Scanner;

public class SortUtils {
	public static void init(int[] array) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < array.length; i++) {
			array[i] = sc.nextInt();
		}
	}

	public static void print(int[] array) {
		String answer = "[ ";
		for (int i = 0; i < array.length - 1; i++) {
			answer += Integer.toString(array[i]) + ", ";
		}
		answer += Integer.toString(array[array.length - 1]) + "]";
	}

	public void sortBubble(int[] arr) {
		boolean swapped = true;
		int j = 0;
		int tmp;
		while (swapped) {
			swapped = false;
			j++;
			for (int i = 0; i < arr.length - j; i++) {
				if (arr[i] > arr[i + 1]) {
					tmp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = tmp;
					swapped = true;
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
