import java.util.Scanner;

// https://algs4.cs.princeton.edu/
// LC 88 Merge Sorted Array
// LC 148 Sort List

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class MergeSort {

    public static void mergeSort(int[] nums) {
        int[] aux = new int[nums.length];
        mergeSort(nums, aux, 0, nums.length - 1);   
    }
    
    private static void mergeSort(int[] nums, int[] aux, int lo, int hi) {
        if (hi <= lo)   return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(nums, aux, lo, mid);
        mergeSort(nums, aux, mid + 1, hi);
        merge(nums, aux, lo, mid, hi);
    }
    
    private static void merge(int[] nums, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = nums[k];
        }
        
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)                nums[k] = aux[j++];
            else if (j > hi)            nums[k] = aux[i++];
            else if (aux[j] < aux[i])   nums[k] = aux[j++];
            else                        nums[k] = aux[i++];
        }
    }

    public static ListNode mergeSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = findMiddle(head);

        ListNode right = mergeSortList(mid.next);
        mid.next = null;
        ListNode left = mergeSortList(head);

        return mergeList(left, right);
    }

    private static ListNode findMiddle(ListNode head) {
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private static ListNode mergeList(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }
        if (head1 != null) {
            tail.next = head1;
        } else {
            tail.next = head2;
        }

        return dummy.next;
    }

    public static void usage() {
        System.out.println("Usage: java MergeSort <options>");
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

            mergeSort(p);
            System.out.println("Sorted array: ");
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

            ListNode sortedListNode = mergeSortList(head);
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