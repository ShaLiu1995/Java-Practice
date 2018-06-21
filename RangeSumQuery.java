import java.util.Scanner;

// LT 840

public class RangeSumQuery {

	private int[] array = null;
	private int[] binaryIndexTree = null;

    // public RangeSumQuery(int[] nums) {
    //     array = new int[nums.length];
    //     for (int i = 0; i < nums.length; i++) {
    //         array[i] = nums[i];
    //     }
    // }

    public RangeSumQuery(int[] nums) {
        array = new int[nums.length];
        binaryIndexTree = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            update(i, nums[i]);
        }
    }
    
    // public void update(int i, int val) {
    //     if (array != null && i < array.length) {
    //         array[i] = val;
    //     }
        
    // }
	
	public void update(int index, int val) {
        if (array == null || index >= array.length)
            return;

        int delta = val - array[index];
        array[index] = val;
        for (int i = index + 1; i <= array.length; i += lowbit(i)) {
            binaryIndexTree[i] += delta;
        }
        
    }

    public int getPrefixSum(int index) {
        int sum = 0;
        for (int i = index + 1; i > 0; i -= lowbit(i)) {
            sum += binaryIndexTree[i];
        }
        return sum;
    }

    private int lowbit(int x) {
        return x & (-x);
    }

    // public int sumRange(int i, int j) {
    //     if (i < 0 || i > j || j >= array.length) {
    //         return 0;
    //     }
        
    //     int sum = 0;
    //     for (int k = i; k <= j; k++) {
    //         sum += array[k];
    //     }
        
    //     return sum;
    // }

    public int sumRange(int lo, int hi) {
        return getPrefixSum(hi) - getPrefixSum(lo - 1);
    }
	

	
	public static void main(String[] args) {
		// int[] p = new int[] {-1, 8, 0, 2, 10};
		Scanner sc = new Scanner(System.in);

		System.out.println("Inset n: ");
		int n = sc.nextInt();

		System.out.println("Inset nums[]: ");
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
		}

		RangeSumQuery rsq = new RangeSumQuery(nums);

		System.out.println("Inset range: ");
		int lo = sc.nextInt();
		int hi = sc.nextInt();

		System.out.println("Range Sum: ");
		System.out.print(rsq.sumRange(lo, hi));
	}
}
