import java.util.Scanner;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class CommonNumInArrays {

	public static List<Integer> getCommonNumUnsorted(int[] nums1, int[] nums2) {
		if (nums1 == null || nums2 == null)
			return null;

		if (nums1.length == 0 || nums2.length == 0)
			return null;

		int n1 = nums1.length;
		int n2 = nums2.length;

		HashSet<Integer> set = new HashSet<>();
		List<Integer> res = new ArrayList<>();
		if (n1 < n2) {
			for (int num : nums1) 
				set.add(num);
			for (int num : nums2) {
				if (set.contains(num))
					res.add(num);

			}
		} else {
			for (int num : nums2)
				set.add(num);
			for (int num : nums1) {
				if (set.contains(num)) 
					res.add(num);
			}
		}

		return res;
	}

	public static List<Integer> getCommonNumSorted(int[] nums1, int[] nums2) {
		if (nums1 == null || nums2 == null)
			return null;

		if (nums1.length == 0 || nums2.length == 0)
			return null;

		int n1 = nums1.length;
		int n2 = nums2.length;
		List<Integer> res = new ArrayList<>();

		int i = 0, j = 0;
		while (i < n1 && j < n2) {
			if (nums1[i] < nums2[j]) {
				i++;
			} else if (nums1[i] > nums2[j]) {
				j++;
			} else {
				res.add(nums1[i]);
				i++;
				j++;
			}
		}

		return res;
	}
	

	
	public static void main(String[] args) {
		// int[] p = new int[] {-1, 8, 0, 2, 10};
		Scanner sc = new Scanner(System.in);

		System.out.println("Inset length of nums1: ");
		int n1 = sc.nextInt();

		System.out.println("Insert nums1: ");
		int[] nums1 = new int[n1];
		for (int i = 0; i < n1; i++) {
			nums1[i] = sc.nextInt();
		}

		System.out.println("Inset length of nums2: ");
		int n2 = sc.nextInt();

		System.out.println("Insert nums2: ");
		int[] nums2 = new int[n2];
		for (int i = 0; i < n2; i++) {
			nums2[i] = sc.nextInt();
		}

	 	
	 	System.out.println("Common numbers are (Unsorted): ");
		List<Integer> res = getCommonNumUnsorted(nums1, nums2);
		for (Integer n : res) {
			System.out.print(n + " ");
		}
		System.out.println();	

		System.out.println("Common numbers are (Sorted): ");
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		List<Integer> res2 = getCommonNumSorted(nums1, nums2);
		for (Integer n : res2) {
			System.out.print(n + " ");
		}	
	}
}
