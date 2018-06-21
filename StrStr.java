import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/**
 * return the postion where pattern s2 matching s1 for the first time
 */

public class StrStr {

	public static int strMatch(String s1, String s2) {
		
		if (s1 == null || s2 == null)
			return -1;

		if (s2.isEmpty())
			return 0;

		if (s1.isEmpty())
			return -1;

		for (int i = 0; i <= s1.length() - s2.length(); i++) {
			int j = 0;
			while (j < s2.length()) {
				if (s1.charAt(i + j) != s2.charAt(j)) {
					break;
				}
				j++;
			}
			if (j == s2.length()) return i;
		}

		return -1;
	}

	public static int rabinKarpSearch(String txt, String pat) {
		if (txt == null || pat == null) {
			return -1;
		}

		int radix = 256;
		int patLen = pat.length();
		int txtLen = txt.length();

		if (patLen > txtLen) 
			return -1;

		long q = longRandomPrime();
		long RM = 1;
		for (int i = 1; i <= patLen - 1; i++) {
			RM = (radix * RM) % q;
		}

		long patHash = hash(pat, patLen, radix, q);
		long txtHash = hash(txt, patLen, radix, q);

		if (patHash == txtHash && check(txt, pat, 0))
			return 0;

		for (int i = patLen; i < txtLen; i++) {
			txtHash = (txtHash + q - RM * txt.charAt(i-patLen) % q) % q;
			txtHash = (txtHash * radix + txt.charAt(i)) % q;

			int offset = i - patLen + 1;
			if ((patHash == txtHash) && check(txt, pat, offset))
				return offset;
		}

		return -1;

	}

	private static long hash(String key, int m, int radix, long q) {
		long h = 0;
		for (int j = 0; j < m; j++)
			h = (radix * h + key.charAt(j)) % q;
		return h;
	}

	private static boolean check(String txt, String pat, int i) {
		for (int j = 0; j < pat.length(); j++) 
			if (pat.charAt(j) != txt.charAt(i + j))
				return false;
		return true;
	}

	private static long longRandomPrime() {
		BigInteger prime = BigInteger.probablePrime(31, new Random());
		return prime.longValue();
	}
	

	
	public static void main(String[] args) {
		// int[] p = new int[] {-1, 8, 0, 2, 10};
		Scanner sc = new Scanner(System.in);

		System.out.println("Input String 1: ");
	 	String s1 = sc.nextLine();
	 	System.out.println("Input String 2: ");
	 	String s2 = sc.nextLine();

		System.out.println("Match Position: " + strMatch(s1, s2));
		System.out.println("Match Position (Rabin-Karp): " + rabinKarpSearch(s1, s2));
		
	}
}
