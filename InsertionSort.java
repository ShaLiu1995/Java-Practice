import java.util.Scanner;

public class InsertionSort {

    public static void insertionSort(int[] a) {
        int n = a.length;
        int exchange = 0;
        for (int i = n - 1; i > 0; i--) {
            if (a[i] < a[i-1]) {
                exch(a, i, i-1);
                exchange++;
            }
        }
        if (exchange == 0) return;
        
        for (int i = 2; i < n; i++) {
            int v = a[i];
            int j = i;
            while (v < a[j-1]) {
                a[j] = a[j-1];
                j--;
            }
            a[j] = v;
        }
    }
    
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;    
    }

    public static void insertionSort2(int[] a) {
        insertionSort2(a, 0 , a.length - 1);
    }

    private static void insertionSort2(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && a[j] < a[j-1]; j--)
                exch(a, j, j-1);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input length:");
        int n = sc.nextInt();
        int[] p = new int[n];

        System.out.println("Input array:");
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt();
        }       

        insertionSort(p);
        System.out.println("Sorted array: ");
        for (int num : p) {
            System.out.print(num + " ");
        }
    }
}
