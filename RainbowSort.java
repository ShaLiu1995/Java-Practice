import java.util.Scanner;


public class RainbowSort {

    public static void rainbowSort(int[] array) {
        if (array == null || array.length <= 1)
            return;

        // [0, zero) is 0
        // [zero, one) is 1
        // [one, two) is 2
        // [two, three] is to be discovered
        // (three, end] is 3

        int zero = 0;
        int one = 0;
        int two = 0;
        int three = array.length - 1;

        while (two <= three) {
            // printArray(array);
            if (array[two] == 0) {
                // System.out.println("case 0: ");
                // System.out.println("zero: " + zero);
                // System.out.println("one: " + one);
                // System.out.println("two: " + two);
                // System.out.println("three: " + three);
                // System.out.println();
                exch(array, zero, two);
                if (zero == one) {                 
                    zero++;
                    one++;
                    two++;
                } else {
                    zero++;
                }
                
            } else if (array[two] == 1) {
                // System.out.println("case 1: ");
                // System.out.println("zero: " + zero);
                // System.out.println("one: " + one);
                // System.out.println("two: " + two);
                // System.out.println("three: " + three);
                // System.out.println();
                exch(array, one++, two++);
            } else if (array[two] == 2) {
                // System.out.println("case 2: ");
                // System.out.println("zero: " + zero);
                // System.out.println("one: " + one);
                // System.out.println("two: " + two);
                // System.out.println("three: " + three);
                // System.out.println();
                two++;
            } else {
                // System.out.println("case 3: ");
                // System.out.println("zero: " + zero);
                // System.out.println("one: " + one);
                // System.out.println("two: " + two);
                // System.out.println("three: " + three);
                // System.out.println();
                exch(array, two, three--);
            }
        }
    }

    private static void printArray(int[] nums) {
        System.out.println("Array is: ");
        for (int i = 0; i < nums.length; i++)
            System.out.print(nums[i] + " ");
        System.out.println();
    }

    private static void exch(int[] nums, int i, int j) {
        int swap = nums[i];
        nums[i] = nums[j];
        nums[j] = swap;
    }
    
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Input length:");
        int n = sc.nextInt();
        int[] p = new int[n];

        System.out.println("Input array:");
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt();
        }       

        rainbowSort(p);
        System.out.println("Sorted array: ");
        for (int num : p) {
            System.out.print(num + " ");
        }
        System.out.println();

        
    }
}