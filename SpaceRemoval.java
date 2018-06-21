import java.util.Scanner;
import java.lang.StringBuilder;


public class SpaceRemoval {

	public static String removeDuplicateSpace(String str) {
		if (str == null)
			return null;

		StringBuilder sb = new StringBuilder(str);

		int i = 0, j = 0;
		while (j < sb.length()) {
			if (sb.charAt(j) == ' ') {
				if (i == 0 || sb.charAt(i-1) == ' ') {
					j++;
				} else if (sb.charAt(i-1) != ' ') {
					sb.setCharAt(i, sb.charAt(j));
					i++;
					j++;
				}
			} else {
				sb.setCharAt(i, sb.charAt(j));
				i++;
				j++;
			}
		}

		// remove trailing space
		if (i > 0 && sb.charAt(i-1) == ' ') i--;
		sb.delete(i, sb.length());

		return sb.toString();
	}
	

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Insert String: ");
		String str = sc.nextLine();
		System.out.println("You inserted: ");
		System.out.println(str);

		String res = removeDuplicateSpace(str);
		
		System.out.println("String after duplicate space removal: ");
		System.out.println(res);
	}
}
