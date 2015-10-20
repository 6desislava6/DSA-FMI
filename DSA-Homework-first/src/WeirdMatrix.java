import java.util.Scanner;

public class WeirdMatrix {
	public static int[][] makeWeirdMatrix(int side){
		int[][] resultingMatrix = new int[side][side];
		int top = 0;
		int down = side - 1;
		int left = 0;
		int right = side - 1;
		int counter = 1;
		// when you have filled e.g. the
		// top row, you increase top++ because you don't want
		// to go through it again
		// same thing with happens with down, left, right :)
		while(counter <= side * side){
			for (int i = left; i <= right; i++) {
				resultingMatrix[top][i] = counter++;
			}
			top++;
			for (int i = top; i <= down; i++) {
				resultingMatrix[i][right] = counter++;
			}
			right--;
			for (int i = down; i >= top; i--) {
				resultingMatrix[i][right] = counter++;
			}
			right--;
			for (int i = right; i >= left; i--) {
				resultingMatrix[top][i] = counter++;
			}
			top++;
			
		}
		return resultingMatrix;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MatrixMultiplication.printMatrix(makeWeirdMatrix(sc.nextInt()));
	}

}
