import java.util.Scanner;


public class RainbowSort {

    /** Three colors */
    public static int[] rainbowSort3(int[] array) {
        // Write your solution here
        if (array == null || array.length <= 1)
          return array;

        // a[0 .. neg-1]       is -1
        // a[neg .. zero-1]    is 0
        // a[zero .. pos]      to be discovered
        // a[pos+1 .. end]     is 1

        int neg = 0;
        int zero = 0;
        int pos = array.length - 1;
        
        while (zero <= pos) {
          if (array[zero] == -1) {
            exch(array, neg++, zero++);
          } else if (array[zero] == 1) {
            exch(array, zero, pos--);
          } else {
            zero++;
          }
        }
        return array; 
    }


    /** Four colors */
    public static void rainbowSort4(int[] array) {
        if (array == null || array.length <= 1)
            return;

        // a[0 ... zero-1]     is 0
        // a[zero ... one-1]   is 1
        // a[one ... two-1]    is 2
        // a[two ... three]    to be discovered
        // a[three+1 ... end]  is 3

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

    /** K colors */
    public static void rainbowSortK(int[] array, int k) {
        if (array == null || array.length == 0 || k <= 1) {
            return;
        }

        rainbowSortK(array, 0, array.length - 1, 1, k);   
    }
    
    private static void rainbowSortK(int[] colors, int lo, int hi, int colorFrom, int colorTo) {
        if (lo >= hi) {
            return;
        }
        
        if (colorFrom >= colorTo) {
            return;
        } 
        
        int colorMid = (colorFrom + colorTo) / 2;
        int i = lo - 1;
        int j = hi + 1;

        // a[lo ... i-1]    <= colorMid
        // a[i ... j]       to be discovered
        // a[j ... hi]      > colorMid

        while (true) {

            while (colors[++i] <= colorMid) {
                if (i == hi) break;
            }
            
            while (colors[--j] > colorMid) {
                if (j == lo) break;
            }
            
            if (i >= j) break;
            exch(colors, i, j);
        }
        
        // a[lo ... j] <= colorMid < a[i ... hi]
        rainbowSortK(colors, lo, j,  colorFrom,    colorMid);
        rainbowSortK(colors, i,  hi, colorMid + 1, colorTo);
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
    
    public static void usage() {
        System.out.println("Usage: java RainbowSort <options>");
        System.out.println("Options:");
        System.out.println("    --help        Check usage");
        System.out.println("    --three       Three colors: -1, 0, 1");
        System.out.println("    --four        Four colors:   0, 1, 2 ,3");
        System.out.println("    --k           K colors");
    }

    public static void main(String[] args) {

        if (args.length != 1 || args[0].equals("--help")) {
            usage();
            return;
        }

        if (args[0].equals("--three")) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Input length:");
            int n = sc.nextInt();
            int[] p = new int[n];

            System.out.println("Input array (-1, 0, 1):");
            for (int i = 0; i < n; i++) {
                p[i] = sc.nextInt();
            }       

            rainbowSort3(p);
            System.out.println("Sorted array (3 colors): ");
            for (int num : p) {
                System.out.print(num + " ");
            }
            System.out.println();
        } 
        else if (args[0].equals("--four")) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Input length:");
            int n = sc.nextInt();
            int[] p = new int[n];

            System.out.println("Input array (0, 1, 2, 3):");
            for (int i = 0; i < n; i++) {
                p[i] = sc.nextInt();
            }       

            rainbowSort4(p);
            System.out.println("Sorted array (4 colors): ");
            for (int num : p) {
                System.out.print(num + " ");
            }
            System.out.println();
        } 
        else if (args[0].equals("--k")) {
            Scanner sc = new Scanner(System.in);

            System.out.println("Input k:");
            int k = sc.nextInt();

            System.out.println("Input length:");
            int n = sc.nextInt();
            int[] p = new int[n];

            System.out.println("Input array (1, 2, ... ,k):");
            for (int i = 0; i < n; i++) {
                p[i] = sc.nextInt();
            }       

            rainbowSortK(p, k);
            System.out.println("Sorted array (k colors): ");
            for (int num : p) {
                System.out.print(num + " ");
            }
            System.out.println();
        } 
        else {
            System.out.println("Unrecognized option: " + args[0]);
            usage();
        }

        
    }
}