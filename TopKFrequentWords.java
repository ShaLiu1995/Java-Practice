import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class TopKFrequentWords {

	public static List<String> getTopKWords(List<String> wordList, int k) {
		
		if (wordList == null || wordList.size() == 0) 
			return new ArrayList<String>();

		HashMap<String, Integer> map = new HashMap<>();

		for (String word : wordList) {
			if (!map.containsKey(word))
				map.put(word, 1);
			else
				map.put(word, map.get(word) + 1);
		}

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}

		PriorityQueue<String> pq = new PriorityQueue<>(k, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return map.get(s1) - map.get(s2);
			}
		});

		for (String word : map.keySet()) {
			if (pq.size() < k) {
				pq.offer(word);
			} else {
				String tmp = pq.peek();
				if (map.get(tmp) < map.get(word)) {
					pq.poll();
					pq.offer(word);
				}
			}
		}

		return new ArrayList<String>(pq);
	}
	

	
	public static void main(String[] args) {
		// int[] p = new int[] {-1, 8, 0, 2, 10};
		Scanner sc = new Scanner(System.in);

		System.out.println("Inset K: ");
		int k = sc.nextInt();

		System.out.println("Inset number of words: ");
		int n = sc.nextInt();

		System.out.println("Insert words (Seperated by space, terminated by CR): ");
		List<String> wordList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			wordList.add(sc.next());
		}
	 	
	 	System.out.println("Top frequent words are: ");
		List<String> res = getTopKWords(wordList, k);
		for (String s : res) {
			System.out.print(s + " ");
		}	
	}
}
