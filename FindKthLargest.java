import java.util.Scanner;
import java.util.PriorityQueue;

// Solution 1: Quick Select
// Solution 2: Priority Queue
public class FindKthLargest {

    public static int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }
    
    public static int quickSelect(int[] nums, int start, int end, int k) {
        int left = start, right = end;
        int randomIndex = start + (int)(Math.random() * (end - start + 1));
        int pivot = nums[randomIndex];
        
        while (left <= right) {
            while (nums[left] > pivot && left <= right) {
                left++;
            }
            
            while (nums[right] < pivot && left <= right) {
                right--;
            }
            
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        
        if (start + k - 1 <= right) {
            return quickSelect(nums, start, right, k);
        }
            
        if (start + k - 1 >= left) {
            return quickSelect(nums, left, end, k - (left - start));
        }
            
        return nums[right + 1];
    }
    
    public static void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public static int findKthLargestPQ(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (pq.size() < k) {
                pq.offer(nums[i]);
            } else {
                int min = pq.peek();
                if (nums[i] > min) {
                    pq.poll();
                    pq.offer(nums[i]);
                }
            }
        }
        return pq.peek();
    }
    
    public static void main(String[] args) {    
        
        Scanner sc = new Scanner(System.in);  
        System.out.println("Please input the size of array");
        int n = sc.nextInt();
        
        System.out.println("Please input the array");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.println("Please input k: ");
        int k = sc.nextInt();

        System.out.println("The Kth largeest number is (Quick Select): " + findKthLargest(nums, k));
        System.out.println("The Kth largeest number is (Priority Queue): " + findKthLargestPQ(nums, k));
 
    }
}
