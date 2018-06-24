import java.util.Scanner;

class ListNode {
    public int value;
    public ListNode next;
    public ListNode(int value) {
        this.value = value;
        this.next = null;
    }
}

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
    
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;    
    }

    public static ListNode selectionSortLinkedList(ListNode head) {
        ListNode node = head;
        while (node != null) {
            ListNode min = node;
            ListNode curr = node.next;
            while (curr != null) {
                if (curr.value < min.value) {
                    min = curr;
                }
                curr = curr.next;
            }
            int temp = node.value;
            node.value = min.value;
            min.value = temp;
            node = node.next;
        }
        return head;
    }

    public static void usage() {
        System.out.println("Usage: java SelectionSort <options>");
        System.out.println("Options:");
        System.out.println("    --help          Check usage");
        System.out.println("    --array         Sort array");
        System.out.println("    --linkedlist    Sort Linked List");
    }
    
    public static void main(String[] args) {
        if (args.length != 1 || args[0] == "--help") {
            usage();
            return;
        }

        if (args[0].equals("--arrays")) {
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

            ListNode sortedListNode = selectionSortLinkedList(head);
            System.out.println("Sorted linkedlist: ");
            while (sortedListNode != null) {
                System.out.print(sortedListNode.value + " ");
                sortedListNode = sortedListNode.next;
            }
        } else {
            System.out.println("Unrecognized option: " + args[0]);
        }        
    }
}
