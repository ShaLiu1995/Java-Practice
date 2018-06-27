import java.util.Scanner;

// LT 840
// LC 307 
// Range Sum Query - Mutable 
// Segment Tree

public class RangeSumQuerySegTree {

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

    /** Segment Tree */
    private int[] segmentTree;
    private int n;
    
    public RangeSumQuerySegTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        n = nums.length;
        segmentTree = new int[n * 2];
        buildSegmentTree(nums);
    }
    
    public void buildSegmentTree(int[] nums) {
        for (int i = n; i < 2 * n; i++) {
            segmentTree[i] = nums[i - n];
        }
        for (int i = n - 1; i >= 0; i--) {
            segmentTree[i] = segmentTree[2 * i] + segmentTree[2 * i + 1];
        }
    }
    
    public void update(int i, int val) {
        int index = i + n;
        segmentTree[index] = val;
        while (index > 0) {
            int left = index;
            int right = index;
            if (index % 2 == 0) {
                right =  index + 1;
            } else {
                left = index - 1;
            }
            segmentTree[index / 2] = segmentTree[left] + segmentTree[right];
            index /= 2;
        }
    }
    
    public int sumRange(int i, int j) {
        int left = i + n;
        int right = j + n;
        int sum = 0;
        while (left <= right) {
            if (left % 2 == 1) {
                sum += segmentTree[left];
                left++;
            }
            if (right % 2 == 0) {
                sum += segmentTree[right];
                right--;
            }
            left /= 2;
            right /= 2;
        }
        return sum;
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

        RangeSumQuerySegTree rsq = new RangeSumQuerySegTree(nums);

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
