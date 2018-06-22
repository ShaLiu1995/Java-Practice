import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * return the postion where pattern s2 matching s1 for the first time
 */

public class SparseMatrixMult {

	public static int[][] multiply(int[][] A, int [][] B) {
		if (A == null || B == null)
			return null;

		if (A[0].length != B.length)
			return null;

		int[][] C = new int[A.length][B[0].length];

		if (A[0].length != B.length) {
			return null;
		}

		List<List<Integer>> col = new ArrayList<>();
		for (int i = 0; i < B.length; i++) {
			col.add(new ArrayList<>());
			for (int j = 0; j < B[0].length; j++) {
				if (B[i][j] != 0) {
					col.get(i).add(j);
				}
			}
		}

		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				if (A[i][j] == 0) 
					continue;

				for (int k = 0; k < col.get(j).size(); k++) {
					int temp = col.get(j).get(k);
					C[i][temp] += A[i][j] * B[j][temp];
				}
			}
		}

		return C;	
	}

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Input size of array A (row col): ");
	 	int m1 = sc.nextInt();
	 	int n1 = sc.nextInt();

	 	System.out.println("Insert array A: ");
	 	int[][] A = new int[m1][n1];
	 	for (int i = 0; i < m1; i++) {
	 		for (int j = 0; j < n1; j++) {
	 			A[i][j] = sc.nextInt();
	 		}

	 	}

	 	System.out.println("Input size of array B (row col): ");
	 	int m2 = sc.nextInt();
	 	int n2 = sc.nextInt();

	 	System.out.println("Insert array B: ");
	 	int[][] B = new int[m2][n2];
	 	for (int i = 0; i < m2; i++) {
	 		for (int j = 0; j < n2; j++) {
	 			B[i][j] = sc.nextInt();
	 		}

	 	}

	 	int[][] C = multiply(A, B);

	 	System.out.println("Result is: ");
	 	for (int i = 0; i < C.length; i++) {
	 		for (int j = 0; j < C[0].length; j++) {
	 			System.out.print(C[i][j] + " ");
	 		}
	 		System.out.println();
	 	}
		
	}
}
