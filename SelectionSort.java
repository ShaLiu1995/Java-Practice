import java.util.Scanner;

public class SelectionSort {

	public static void selectionSort(int[] a) {
		int n = a.length;
		
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
				if (a[j] < a[min]) {
					min = j;
				}
			}
			exch(a, i, min);
		}
	}
	
	public static void exch(int[] a, int i, int j) {
		int swap = a[i];
		a[i] = a[j];
		a[j] = swap;	
	}
	
	public static void main(String[] args) {
		// int[] p = new int[] {-1, 8, 0, 2, 10};
		Scanner sc = new Scanner(System.in);

		System.out.println("Input length:");
		int n = sc.nextInt();
		int[] p = new int[n];

		System.out.println("Input array:");
		for (int i = 0; i < n; i++) {
			p[i] = sc.nextInt();
		}		

		selectionSort(p);
		System.out.println("Sorted array: ");
		for (int num : p) {
			System.out.print(num + " ");
		}
	}
}
