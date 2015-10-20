import java.util.Scanner;

public class ReverseSquareRootCalculator {
	public static int[] readNumbers(int amount, Scanner sc){
		int[] resultingArray = new int[amount];
		for (int i = 0; i < amount; i++) {
			resultingArray[i] = sc.nextInt();
		}
		return resultingArray;
		
	}
	public static void printSquareRoots(int[] numbers){
		for(int counter= numbers.length - 1; counter >= 0;counter--) {
			//System.out.printf("%06d", Math.sqrt(numbers[counter]));
			System.out.println(String.format("%02f", Math.sqrt(numbers[counter])));
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("How many numbers?");
		int amount = sc.nextInt();
		printSquareRoots(readNumbers(amount, sc));
	}

}
