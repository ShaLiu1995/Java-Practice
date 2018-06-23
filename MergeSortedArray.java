import java.util.Scanner;

// LT 42

public class MergeSortedArray {

    public static void mergeSortedArray(int[] A, int m, int[] B, int n) {      
        int i = 0, j = 0;      
        while (i < m && j < n) {
            if (A[i+j] <= B[j]) {
                i++;
            } else {
                for (int k = m + j; k > i + j; k--) {
                    A[k] = A[k-1];
                }
                A[i + j] = B[j];
                j++;
            }
        }
        
        while (j < n) {
            A[i+j] = B[j];
            j++;
        }
    }
    

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insert m: ");
        int m = sc.nextInt();

        System.out.println("Insert n: ");
        int n = sc.nextInt();

        System.out.println("Insert A[]: ");
        int[] A = new int[m + n];
        for (int i = 0; i < m; i++) {
            A[i] =sc.nextInt();
        }

        System.out.println("Insert B[]: ");
        int[] B = new int[m];
        for (int i = 0; i < n; i++) {
            B[i] =sc.nextInt();
        }

        mergeSortedArray(A, m, B, n);
        System.out.println("Results: ");
        for (int num : A) {
            System.out.print(num + " ");
        }   
    }
}
