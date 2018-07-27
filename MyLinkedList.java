import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class MyLinkedList {
  
    private ListNode head;

    public MyLinkedList(ListNode head) {
        this.head = head;
    }

    public int size(ListNode head) {
        int size = 0;
        ListNode curr = head;
        while (curr != null) {
            size++;
            curr = curr.next;
        }
        return size;
    }

    public ListNode get(ListNode head, int index) {
        ListNode curr = head;
        while (index > 0 && curr != null) {
            curr = curr.next;
            index--;
        }
        return curr;
    }

    public ListNode offerFirst(ListNode head, int val) {
        ListNode newNode = ListNode(val);
        newNode.next = head;
        return newNode;
    }

    public ListNode offerLast(ListNode head, int val) {
        ListNode newNode = ListNode(val);
        if (head == null) {
            return newNode;
        }
        ListNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
        return head;
    }
}
