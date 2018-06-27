import java.util.Scanner;

// LT 840
// LC 307 
// Range Sum Query - Mutable 
// Binary Indexed Tree (Fenwick Tree)

public class RangeSumQueryBIT {

	// Time limit exceede
    /*
    private int[] sums = null;

    public NumArray(int[] nums) {
        if (nums == null) return;
        int n = nums.length;
        sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
    }
    
    public void update(int i, int val) {
        int diff = val - (sums[i + 1] - sums[i]);
        // System.out.println(diff);
        for (int k = i + 1; k < sums.length; k++) {
            sums[k] += diff;
        }
    }
    
    public int sumRange(int i, int j) {
        return sums[j + 1] - sums[i];
    }
    */


    /** Binary Indexed Tree */
    private int[] array = null;
    private int[] binaryIndexedTree = null;

    public RangeSumQueryBIT(int[] nums) {
        array = new int[nums.length];
        binaryIndexedTree = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            update(i, nums[i]);
        }
    }
    
    public void update(int index, int val) {
        if (array == null || index >= array.length)
            return;

        int diff = val - array[index];
        array[index] = val;
        for (int i = index + 1; i <= array.length; i += lowbit(i)) {
            binaryIndexedTree[i] += diff;
        }
        
    }

    public int getPrefixSum(int index) {
        int sum = 0;
        for (int i = index + 1; i > 0; i -= lowbit(i)) {
            sum += binaryIndexedTree[i];
        }
        return sum;
    }

    private int lowbit(int x) {
        return x & (-x);
    }

    public int sumRange(int lo, int hi) {
        return getPrefixSum(hi) - getPrefixSum(lo - 1);
    }
    

    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Show Binary Indexed Tree
        // for (int i = 0; i < 20; i++) {
        // 	System.out.println(i + " : " + (i & (-i)));
        // }

        System.out.println("Insert n: ");
        int n = sc.nextInt();

        System.out.println("Insert nums[]: ");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        RangeSumQueryBIT rsq = new RangeSumQueryBIT(nums);

        System.out.println("Insert range: ");
        int lo = sc.nextInt();
        int hi = sc.nextInt();

        System.out.println("Range Sum: ");
        System.out.println(rsq.sumRange(lo, hi));

        System.out.println("Insert index-value pair to update: ");
        int index = sc.nextInt();
        int val = sc.nextInt();
        rsq.update(index, val);

        System.out.println("Insert range: ");
        lo = sc.nextInt();
        hi = sc.nextInt();

        System.out.println("Range Sum: ");
        System.out.println(rsq.sumRange(lo, hi));

    }
}
