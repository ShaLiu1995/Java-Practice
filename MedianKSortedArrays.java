import java.util.Scanner;

// LT 931

public class MedianKSortedArrays {

	public static double findMedian(int[][] nums) {
        // write your code here
        int len = getLen(nums);
        if (len == 0) 
            return 0;
        
        if (len % 2 != 0) 
            return findKth(nums, len/2 + 1);
            
        return (findKth(nums, len/2) + findKth(nums, len/2 + 1)) / 2.0;
    }
    
    public static int getLen(int[][] nums) {
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            len += nums[i].length;
        }
        return len;
    }
    
    private static int findKth(int[][] nums, int k) {
        int start = 0, end = Integer.MAX_VALUE;   
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (countGreaterOrEqual(nums, mid) >= k) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (countGreaterOrEqual(nums, start) >= k) {
            return start;
        }   
        return end;
    }
    
    private static int countGreaterOrEqual(int[][] nums, int val) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += countGreaterOrEqual(nums[i], val);
        }
        return sum;
    }
    
    private static int countGreaterOrEqual(int[] nums, int val) {
        if (nums.length == 0)
            return 0;
        
        int start = 0, end = nums.length -1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= val) {
                end = mid;
            } else {
                start = mid;
            }
        }
           
        if (nums[start] >= val)
            return nums.length - start;
        
        if (nums[end] >= val)
            return nums.length - end;
        
        return 0;
    }

	
	public static void main(String[] args) {
		// int[] p = new int[] {-1, 8, 0, 2, 10};
		Scanner sc = new Scanner(System.in);

		System.out.println("Insert number of rows (sorted): ");
		int m = sc.nextInt();

		System.out.println("Insert number of columns: ");
		int n = sc.nextInt();


		System.out.println("Insert nums[][]: ");
		int[][] nums = new int[m][n];
		for (int i = 0; i < m * n; i++) {
			nums[i / m][i % m] = sc.nextInt();
		}
		
		System.out.println("Medians: " + findMedian(nums));
	}
}
