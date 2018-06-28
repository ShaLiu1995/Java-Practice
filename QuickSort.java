import java.util.Scanner;

// https://algs4.cs.princeton.edu/
// LC 88 Merge Sorted Array
// LC 148 Sort List

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class QuickSort {

    public static void quickSort2Way(int[] nums) {
        shuffleArray(nums);
        quickSort2Way(nums, 0, nums.length - 1);   
    }

    public static void quickSort2Way(int[] nums, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(nums, lo, hi);
        quickSort2Way(nums, lo, j - 1);
        quickSort2Way(nums, j + 1, hi);
    }

    public static int partition(int[] nums, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = nums[lo];

        // a[lo]             pivot
        // a[lo+1 ... i-1]   <= pivot
        // a[i ... j]        undiscovered
        // a[j+1 ... hi]     >= pivot
        while (true) {

            // find item on lo to swap
            while (nums[++i] < v) {
                if (i == hi) break;
            }

            // find item on hi to swap
            while (v < nums[--j]) {
                if (j == lo) break;
            }

            // check if pointers cross
            if (i >= j) break;
            exch(nums, i, j);
        }

        // put partitioning item v at a[j]
        exch(nums, lo, j);

        // now, a[lo ... j-1] <= a[j] <= a[j+1 ... hi]
        return j;
    }

    /** 3-Way Quick Sort */
    public static void quickSort3Way(int[] nums) {
        shuffleArray(nums);
        quickSort3Way(nums, 0, nums.length - 1);
    }

    private static void quickSort3Way(int[] nums, int lo, int hi) {
        if (hi <= lo)   return;
        
        // a[lo]                pivot
        // a[lo+1 ... lt-1]     < pivot
        // a[lt ... i - 1]      == pivot
        // a[i ... gt]          to be discovered
        // a[gt+1 .. hi]        > pivot
        int lt = lo, gt = hi;
        int pivot = nums[lo];
        int i = lo + 1;
        while (i <= gt) {
            if (nums[i] < pivot)        exch(nums, lt++, i++);
            else if (nums[i] > pivot)   exch(nums, i, gt--);
            else                        i++;
        }
        
        quickSort3Way(nums, lo, lt - 1);
        quickSort3Way(nums, gt + 1, hi);
    }


    /** helper function */
    private static void shuffleArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int j = (int)(Math.random() * nums.length);
            exch(nums, i, j);
        }
    }

    private static void exch(int[] nums, int i, int j) {
        int swap = nums[i];
        nums[i] = nums[j];
        nums[j] = swap;
    }
    


    /** quickSort for linkedlist */
    public static ListNode quickSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pivot = head;

        ListNode leftDummy = new ListNode(0);
        ListNode middleDummy = new ListNode(0);
        ListNode rightDummy = new ListNode(0);
        ListNode leftTail = leftDummy;
        ListNode middleTail = middleDummy;
        ListNode rightTail = rightDummy;
        
        ListNode node = head;
        while(node != null) {
            if (node.val < pivot.val) {
                leftTail.next = node;
                leftTail = node;
            } else if (node.val > pivot.val) {
                rightTail.next = node;
                rightTail = node;
            } else {
                middleTail.next = node;
                middleTail = node;
            }
            node = node.next;
        }
        
        leftTail.next = null;
        middleTail.next = null;
        rightTail.next = null;
        
        ListNode left = quickSortList(leftDummy.next);
        ListNode right = quickSortList(rightDummy.next);
        
        return concat3List(left, middleDummy.next, right);
    }
    
    private static ListNode concat3List(ListNode head1, ListNode head2, ListNode head3) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        tail.next = head1;
        tail = findTail(tail);
        tail.next = head2;
        tail = findTail(tail);
        tail.next = head3;
        return dummy.next;
    }
    
    private static ListNode findTail(ListNode head) {
        if (head == null) {
            return null;
        }
        
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }

    public static void usage() {
        System.out.println("Usage: java QuickSort <options>");
        System.out.println("Options:");
        System.out.println("    --help        Check usage");
        System.out.println("    --array       Sort array");
        System.out.println("    --linkedlist  Sort linkedlist");
    }
    
    public static void main(String[] args) {
        if (args.length != 1 || args[0].equals("--help")) {
            usage();
            return;
        }

        if (args[0].equals("--array")) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Input length:");
            int n = sc.nextInt();
            int[] p = new int[n];

            System.out.println("Input array:");
            for (int i = 0; i < n; i++) {
                p[i] = sc.nextInt();
            }       

            quickSort2Way(p);
            System.out.println("Sorted array (2-way): ");
            for (int num : p) {
                System.out.print(num + " ");
            }
            System.out.println();

            quickSort3Way(p);
            System.out.println("Sorted array (3-way): ");
            for (int num : p) {
                System.out.print(num + " ");
            }

        } else if (args[0].equals("--linkedlist")) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Input length:");
            int n = sc.nextInt();

            System.out.println("Input the linkedlist:");
            ListNode head = null, prev = null, curr = null;
            head = new ListNode(sc.nextInt());
            curr = head;
            int i = 1;
            while (i < n) {
                curr.next = new ListNode(sc.nextInt());
                prev = curr;
                curr = curr.next;
                i++;
            }

            ListNode sortedListNode = quickSortList(head);
            System.out.println("Sorted linkedlist: ");
            while (sortedListNode != null) {
                System.out.print(sortedListNode.val + " ");
                sortedListNode = sortedListNode.next;
            }
        } else {
            System.out.println("Unrecognized option: " + args[0]);
            usage();
        }        
    }
}