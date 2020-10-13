package javaDsAlgoCN.AdvDS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueuePrac {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int arr[] = {-137892, -353550, 521753, -48409, -298886, -871462, 231711, -46383, 192623, -43410};
        ArrayList<Integer> ans = kLargest(arr, 5);
        System.out.println(ans);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> {
            if (a < b) return 1;
            else if (a > b) return -1;
            else return 0;
        });

        PriorityQueue<Integer> maxHeap2 = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                if (a < b) return 1;
                else if (a > b) return -1;
                else return 0;
            }
        });

        PriorityQueue<String> maxHeapString = new PriorityQueue<String>(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                if (a.length() > b.length()) return -1;
                else if (a.length() < b.length()) return 1;
                else return 0;
            }
        });

        maxHeapString.add("a");
        maxHeapString.add("aas");
        maxHeapString.add("aadaAaSASdxasd");
        maxHeapString.add("aadafffssaxa");
        maxHeapString.add("aaff");
        maxHeapString.add("vssav");

        System.out.println(maxHeapString);
        while (!maxHeapString.isEmpty())
            System.out.println(maxHeapString.remove());
    }

    public static ArrayList<Integer> kLargest(int input[], int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0 ; i < k ; i++){
            pq.add(input[i]);
        }
        for (int i = k ; i < input.length ; i++) {
            if (pq.peek() < input[i]) {
                pq.remove();
                pq.add(input[i]);
            }
        }

        while (!pq.isEmpty()) {
            ans.add(pq.remove());
        }

        return ans;
    }
}
