import java.util.Scanner;
import java.lang.StringBuilder;


public class CharRemoval {

	public static String removeChar(String str, char ch) {
		if (str == null)
			return null;

		StringBuilder sb = new StringBuilder(str);

		int i = 0;
		for (int j = 0; j < sb.length(); j++) {	
			if (sb.charAt(j) != ch) {
				sb.setCharAt(i, sb.charAt(j));
				i++;
			}
		}
		sb.delete(i, sb.length());

		return sb.toString();
	}
	

	
	public static void main(String[] args) {
		// int[] p = new int[] {-1, 8, 0, 2, 10};
		Scanner sc = new Scanner(System.in);

		System.out.println("Insert String: ");
		String str = sc.nextLine();

		System.out.println("Insert number of characters to remove: ");
		int k = sc.nextInt();

		System.out.println("Insert characters to remove: ");
		char[] removeSet = new char[k];
		for (int i = 0; i < k; i++)
			removeSet[i] = sc.next().charAt(0);

		for (char ch : removeSet)
			str = removeChar(str, ch);
		
		System.out.println("String after removal: " + str);
	}
}
