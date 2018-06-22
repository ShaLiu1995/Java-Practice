import java.lang.String;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

// LT 839 Merge two sorted intervals
// LT 577 Merge K sorted intervals

class Interval {
	int start, end;
	Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
	public String toString() {
		String str = "[" + start + "," + end + "]";
		return str;
	}
}

class Pair {
    int row, col;
    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class MergeSortedIntervalList {

	public static List<Interval> mergeTwoIntervalList(List<Interval> list1, List<Interval> list2) {       
        int p1 = 0, p2 = 0;
        List<Interval> res = new ArrayList<Interval>();
        
        while (p1 < list1.size() || p2 < list2.size()) {
            Interval curr;
            if (p1 == list1.size()) {
                curr = list2.get(p2++);
            } else if (p2 == list2.size()) {
                curr = list1.get(p1++);
            } else {
                if (list1.get(p1).start < list2.get(p2).start) {
                    curr = list1.get(p1++);
                } else {
                    curr = list2.get(p2++);
                }
            }
            
            if (res.isEmpty()) {
                res.add(curr);
                continue;
            }
            
            Interval last = res.get(res.size() - 1);
            if (curr.start <= last.end) {
                last.end = Math.max(curr.end, last.end);
            } else {
                res.add(curr);
            }
        }
        
        return res;
    }

    public static List<Interval> mergeKIntervalList(List<List<Interval>> intervals) {
    	int k = intervals.size();
    	PriorityQueue<Pair> pq = new PriorityQueue<>(k, new Comparator<Pair>() {
    		@Override
    		public int compare(Pair p1, Pair p2) {
    			return intervals.get(p1.row).get(p1.col).start - 
    				   intervals.get(p2.row).get(p2.col).start;
    		}
    	});

    	for (int i = 0; i < k; i++) {
    		if (intervals.get(i).size() > 0) {
    			pq.add(new Pair(i, 0));
    		}
    	}

    	List<Interval> res = new ArrayList<>();
    	while (!pq.isEmpty()) {
    		Pair p = pq.poll();
    		res.add(intervals.get(p.row).get(p.col));
    		p.col++;
    		if (p.col < intervals.get(p.row).size()) {
    			pq.offer(p);
    		}
    	}

    	return merge(res);
    }
	

	public static List<Interval> merge(List<Interval> intervals) {
		if (intervals.size() <= 1)
			return intervals;

		int start = intervals.get(0).start;
		int end = intervals.get(0).end;
		List<Interval> res = new ArrayList<>();
		for (Interval iv : intervals) {
			if (iv.start <= end) {
				end = Math.max(iv.end, end);
			} else {
				res.add(new Interval(start, end));
				start = iv.start;
				end = iv.end;
			}
		}

		res.add(new Interval(start, end));
		return res;
	}

    public static void usage() {
    	System.out.println("Usage: java MergeSortedIntervalList <options>");
    	System.out.println("Options:");
    	System.out.println("--help 			Check usage");
    	System.out.println("--two 			Merger two sorted interval lists");
    	System.out.println("--more 			Merger three or more sorted interval lists");
    }
	
	public static void main(String[] args) {

		if (args.length != 1 || args[0].equals("--help")) {
			usage();
			return;
		}

		if (args[0].equals("--two")) {
			Scanner sc = new Scanner(System.in);

			System.out.println("Insert number of intervals in List1: ");
			int n1 = sc.nextInt();
			System.out.println("Insert intervals in List1: ");
			List<Interval> list1 = new ArrayList<>();
			for (int i = 0; i < n1; i++) {
				int start = sc.nextInt();
				int end = sc.nextInt();
				list1.add(new Interval(start, end));
			}

			System.out.println("Insert number of intervals in List2: ");
			int n2 = sc.nextInt();
			System.out.println("Insert intervals in List2: ");
			List<Interval> list2 = new ArrayList<>();
			for (int i = 0; i < n2; i++) {
				int start = sc.nextInt();
				int end = sc.nextInt();
				list2.add(new Interval(start, end));
			}

			List<Interval> res = mergeTwoIntervalList(list1, list2);
			System.out.println("Results are: ");
			for (Interval iv : res) {
				System.out.println(iv.toString());
			}

		} else if (args[0].equals("--more")) {
			Scanner sc = new Scanner(System.in);

			System.out.println("Insert total number of lists: ");
			int N = sc.nextInt();

			List<List<Interval>> ivListList = new ArrayList<>();
			for (int k = 0; k < N; k++) {
				System.out.println("Insert number of intervals in next list: ");
				int n = sc.nextInt();
				System.out.println("Insert next interval list: ");
				List<Interval> ivList = new ArrayList<>();
				for (int i = 0; i < n; i++) {
					int start = sc.nextInt();
					int end = sc.nextInt();
					ivList.add(new Interval(start, end));
				}
				ivListList.add(ivList);
			}

			List<Interval> res = mergeKIntervalList(ivListList);
			System.out.println("Results are: ");
			for (Interval iv : res) {
				System.out.println(iv.toString());
			}

		} else {
			System.out.println("Unrecognized option: " + args[0]);
		}

	}
}
