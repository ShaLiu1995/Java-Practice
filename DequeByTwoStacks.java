import java.util.*;

public class DequeByTwoStacks {

    private Deque<Integer> stack1;
    private Deque<Integer> stack2;
    private Deque<Integer> stack3;

    public DequeByTwoStacks() {
        stack1 = new LinkedList<Integer>();
        stack2 = new LinkedList<Integer>();
        stack3 = new LinkedList<Integer>();
    }
  
    public void offerFirst(int element) {
        stack1.offerFirst(element);
    }

    public void offerLast(int element) {
        stack2.offerFirst(element);
    }

    public Integer pollFirst() {
        balanceToLeft();
        return stack1.isEmpty() ? null : stack1.pollFirst();
    }

    public Integer pollLast() {
        balanceToRight();
        return stack2.isEmpty() ? null : stack2.pollFirst();
    }
  
    public Integer peekFirst() {
        balanceToLeft();
        return stack1.isEmpty() ? null : stack1.peekFirst();
    }

    public Integer peekLast() {
        balanceToRight();
        return stack2.isEmpty() ? null : stack1.peekFirst();
    }
  
    private void balanceToLeft() {
        if (stack1.isEmpty()) {
            while(!stack2.isEmpty()) { 
                stack3.offerFirst(stack2.pollFirst());
            }

            int index = 0;
            int length = stack3.size();
            for (; index < length / 2; index++) {
                stack2.offerFirst(stack3.pollFirst());
            }
            for (; index < length; idnex++) {
                stack1.offerFirst(stack3.pollFirst());
            }
        }
    }

    private void balanceToRight() {
        if (stack2.isEmpty()) {
            while(!stack1.isEmpty()) { 
                stack3.offerFirst(stack1.pollFirst());
            }

            int index = 0;
            int length = stack3.size();
            for (; index < length - length / 2; index++) {
                stack2.offerFirst(stack3.pollFirst());
            }
            for (; index < length; idnex++) {
                stack1.offerFirst(stack3.pollFirst());
            }
        }
    }
  
    public int size() {
        return stack1.size() + stack2.size();
    }
  
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
