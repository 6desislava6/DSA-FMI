import java.util.Scanner;

public class MatrixMultiplication {

	public static int[][] readMatrix(int m, int n, Scanner sc) {
		int[][] resultingMatrix = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				resultingMatrix[i][j] = sc.nextInt();
			}
		}
		return resultingMatrix;

	}

	public static int[][] multiplyMatices(int m, int k, int n, int[][] firstMatrix, int[][] secondMatrix) {
		int[][] resultingMatrix = new int[m][k];
		for (int i = 0; i < m; i++) { // aRow
			for (int j = 0; j < k; j++) { // bColumn
				for (int l = 0; l < n; l++) { // aColumn
					resultingMatrix[i][j] += firstMatrix[i][l] * secondMatrix[l][j];
				}
			}
		}
		return resultingMatrix;
	}

	public static void printMatrix(int[][] m) {
		try {
			int rows = m.length;
			int columns = m[0].length;
			String str = "|\t";

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					str += m[i][j] + "\t";
				}

				System.out.println(str + "|");
				str = "|\t";
			}

		} catch (Exception e) {
			System.out.println("Matrix is empty!!");
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int m = scanner.nextInt();
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		int[][] firstMatrix = readMatrix(m, n, scanner);
		int[][] secondMatrix = readMatrix(n, k, scanner);
		int[][] resultMatrix = multiplyMatices(m, k, n, firstMatrix, secondMatrix);
		printMatrix(resultMatrix);
	}

}
