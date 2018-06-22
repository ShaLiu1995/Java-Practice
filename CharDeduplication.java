import java.util.Scanner;
import java.lang.StringBuilder;
import java.util.Stack;
import java.util.ArrayList;


public class CharDeduplication {

    public static String deduplication(String str) {
        if (str == null)
            return null;

        if (str.length() < 2)
            return str;

        StringBuilder sb = new StringBuilder(str);

        int f = 1, s = 0;
        while(f < sb.length()) {
            if (sb.charAt(s) != sb.charAt(f)) {
                sb.setCharAt(++s, sb.charAt(f));
            } else {
                f++;
            }
        }

        sb.delete(s + 1, sb.length());

        return sb.toString();
    }

    public static String deduplicationRepeatly(String str) {
        if (str == null)
            return null;
    
        Stack<Character> stack = new Stack<>();
        int f = 0;
        while(f < str.length()) {
            char ch = str.charAt(f);
            if (!stack.isEmpty() && ch == stack.peek()) {
                while (f < str.length() && ch == str.charAt(f))
                    f++;
                // System.out.println("pop: " + stack.peek());
                stack.pop();
            } else {
                // System.out.println("push: " + ch);
                stack.push(ch);
                f++;
            }
        }
 
        StringBuilder sb = new StringBuilder();
        ArrayList<Character> temp = new ArrayList<>(stack);
        for (Character ch : temp) {
            sb.append(ch);
        }

        return sb.toString();
    }
    

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insert String: ");
        String str = sc.nextLine();

        String res = deduplication(str);
        System.out.println("String after de-duplication: ");
        System.out.println(res);

        String res2 = deduplicationRepeatly(str);
        System.out.println("String after de-duplication (Repeatly): ");
        System.out.println(res2);
    }
}
