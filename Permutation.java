import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Permutation {

	public static List<String> permutation(String str) {
		List<String> result = new ArrayList<>();
		if (str == null || str.length() == 0)
			return result;
		
        char[] letters = str.toCharArray();
        Arrays.sort(letters);

        boolean visited[] = new boolean[letters.length];
        StringBuilder sb = new StringBuilder();

        dfs(letters, sb, 0, result, visited);
        return result;
    }
	
    public static void dfs(char[] letters, 
		    				StringBuilder sb,
		    				int startIndex,
					        List<String> result,
					        boolean[] visited) {
		
		if (sb.length() == letters.length) {
			result.add(sb.toString());
			return;
		}		
				
		for (int i = 0; i < letters.length; i++) {
			if (visited[i])
				continue;	
			if (i != 0 && letters[i] == letters[i - 1] && !visited[i - 1])
				continue;
			visited[i] = true;
            sb.append(letters[i]);    
            dfs(letters, sb, i + 1, result, visited);
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
	}

	public static List<List<Integer>> permutationInt(List<Integer> nums) {
		List<List<Integer>> result = new ArrayList<>();
		if (nums == null || nums.size() == 0)
			return null;
		
        Collections.sort(nums);

        boolean visited[] = new boolean[nums.size()];
        List<Integer> temp = new ArrayList<>();

        dfsInt(nums, temp, 0, result, visited);
        return result;
    }
	
    public static void dfsInt(List<Integer> nums, 
		    				List<Integer> temp,
		    				int startIndex,
					        List<List<Integer>> result,
					        boolean[] visited) {
		
		if (temp.size() == nums.size()) {
			result.add(new ArrayList<Integer>(temp));
			return;
		}		
				
		for (int i = 0; i < nums.size(); i++) {
			if (visited[i])
				continue;	
			if (i != 0 && nums.get(i) == nums.get(i-1) && !visited[i-1])
				continue;
			visited[i] = true;
            temp.add(nums.get(i));    
            dfsInt(nums, temp, i + 1, result, visited);
            temp.remove(temp.size() - 1);
            visited[i] = false;
        }
	}

	public static void usage() {
    	System.out.println("Usage: java Permutation <options>");
    	System.out.println("Options:");
    	System.out.println("--help 			Check usage");
    	System.out.println("--int 			Permutation of integers");
    	System.out.println("--string 		Permutation of string");
    }
	
	public static void main(String[] args) {

		if (args.length != 1 || args[0].equals("--help")) {
			usage();
			return;
		}

		if (args[0].equals("--string")) {
			Scanner sc = new Scanner(System.in);  
			System.out.println("Please input a string");
			String str = sc.next();  
			
			List<String> result = permutation(str);
			System.out.println("Permutation is: ");
			for (String s : result) {
				System.out.println(s);
			}
		} else if (args[0].equals("--int")) {
			Scanner sc = new Scanner(System.in);  

			System.out.println("Please input size of number list");
			int n = sc.nextInt();

			System.out.println("Please insert the number list");
			List<Integer> nums = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				nums.add(sc.nextInt());
			}

			List<List<Integer>> ans = permutationInt(nums);
			System.out.println("Permutation are: ");
			for (List<Integer> list : ans) {
				System.out.println(list.toString());
			}

		} else {
			System.out.println("Unrecognized option: " + args[0]);
		}

	}
}
