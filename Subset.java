import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Subset {

    public static List<String> subset1(String str) {
        List<String> result = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return result;
        }
        
        char[] letters = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        dfs1(letters, sb, 0, result);
        return result;
    }

    public static void dfs1(char[] letters, 
                            StringBuilder sb,
                            int index,
                            List<String> result) {
                
        if (index == letters.length) {
            result.add(sb.toString());
            return;
        }

        dfs1(letters, sb, index + 1, result);
        sb.append(letters[index]);
        dfs1(letters, sb, index + 1, result);
        sb.deleteCharAt(sb.length() - 1);
    }

    public static List<String> subset2(String str) {
    
        List<String> result = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return result;
        }
        
        char[] letters = str.toCharArray();
          Arrays.sort(letters);
        StringBuilder sb = new StringBuilder();
        dfs2(letters, sb, 0, result);
        return result;
    }
    
    public static void dfs2(char[] letters, 
                            StringBuilder sb,
                            int startIndex,
                            List<String> result) {
                
        result.add(sb.toString());
                
        for (int i = startIndex; i < letters.length; i++) {
            if (i != startIndex && letters[i] == letters[i - 1])
                continue;
            sb.append(letters[i]);    
            dfs2(letters, sb, i + 1, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // number
    public static List<List<Integer>> subset(List<Integer> nums) {
    
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.size() == 0) {
            return new ArrayList<>();
        }
        
        Collections.sort(nums);
        List<Integer> subset = new ArrayList<>();

        dfs(nums, subset, 0, result);
        return result;
    }
    
    public static void dfs(List<Integer> nums, 
                            List<Integer> subset,
                            int startIndex,
                            List<List<Integer>> result) {
                
        result.add(new ArrayList<Integer>(subset));
                
        for (int i = startIndex; i < nums.size(); i++) {
            if (i != startIndex && nums.get(i) == nums.get(i-1))
                continue;
            subset.add(nums.get(i));    
            dfs(nums, subset, i + 1, result);
            subset.remove(subset.size() - 1);
        }
    }

    public static void usage() {
        System.out.println("Usage: java Subset <options>");
        System.out.println("Options:");
        System.out.println("--help             Check usage");
        System.out.println("--int             Subset of integers");
        System.out.println("--string         Subset of string");
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
            
            List<String> ans1 = subset1(str);
            System.out.println("Combinations are (subset1): ");
            for (String s : ans1) {
                System.out.println(s);
            }

            List<String> ans2 = subset2(str);
            System.out.println("Combinations are (subset2): ");
            for (String s : ans2) {
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

            List<List<Integer>> ans = subset(nums);
            System.out.println("Combinations are: ");
            for (List<Integer> list : ans) {
                System.out.println(list.toString());
            }

        } else {
            System.out.println("Unrecognized option: " + args[0]);
        }
    }
}
