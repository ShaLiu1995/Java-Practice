import java.util.Scanner;
import java.lang.StringBuilder;

// LO String Decompress

public class StringDecompress {

    public static String stringDecompress(String input) {
        if (input == null)
            return null;

        StringBuilder sb = new StringBuilder();

        int i = 0, j = 0;
        while (j < input.length()) {
            char ch = input.charAt(j);
            if (!Character.isDigit(ch)) {
                if (i < j) {
                  int repeat = Integer.parseInt(input.substring(i, j));
                  if (repeat == 0) {
                     sb.deleteCharAt(sb.length() -1);
                  }
                  int count = 1;
                  while (count < repeat) {
                      sb.append(sb.charAt(sb.length() - 1));
                      count++;
                  }  
                }      
                sb.append(ch);
                j++;
                i = j;
            } else {
                j++;
            }
        }
    
        if (i < j) {
            int repeat = Integer.parseInt(input.substring(i, j));
            if (repeat == 0) {
               sb.deleteCharAt(sb.length() -1);
            }
            int count = 1;
            while (count < repeat) {
                sb.append(sb.charAt(sb.length() - 1));
                count++;
            }
        }
        return sb.toString();
    }
    

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insert String: ");
        String str = sc.nextLine();
        System.out.println("You inserted: ");
        System.out.println(str);

        String res = stringDecompress(str);
        
        System.out.println("String after decompression: ");
        System.out.println(res);
    }
}
