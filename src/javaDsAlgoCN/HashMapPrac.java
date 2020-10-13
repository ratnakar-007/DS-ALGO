package javaDsAlgoCN;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HashMapPrac {
    public static void main(String[] args) {
        int[] arr = {4, 4, 4, 4};
        /*ArrayList<Integer> ans = new ArrayList<>();
        ans = HMSolution.longestConsecutiveIncreasingSequence(arr);
        for (int ele : ans){
            System.out.println(ele);
        }*/

        HMSolution.findPairsDifferenceK(arr, 0);


    }
}

class HMSolution {

    public static int maxFrequencyNumber(int[] arr){
        HashMap<Integer, Integer> map = new LinkedHashMap<>();

        for(int i = 0; i < arr.length ; i++) {
            int key = arr[i];
            if(map.containsKey(arr[i])) {
                int val = map.get(arr[i]);
                val++;
                map.put(key, val);
            } else {
                map.put(key, 1);
            }
        }
        int max_freq = 0;
        int ans = -1;
        Set<Integer> keySet = map.keySet();
        for (int i : keySet) {
            if (max_freq < map.get(i)) {
                ans = i;
                max_freq = map.get(i);
            }
        }
        return ans;
    }
    public static ArrayList<Integer> longestConsecutiveIncreasingSequence(int[] arr) {
        int start_sq = arr[0];
        int max_count = 0;

        HashMap<Integer, Integer> orderMap = new HashMap<>() ;
        HashMap<Integer, Boolean> arrMap   = new        HashMap<>() ;
        int order = 1;
        for (int i : arr) {
            arrMap.put(i, true);
            orderMap.put(i, order++);
        }
        for (Map.Entry me : arrMap.entrySet()) {
            if((boolean)me.getValue()) {
                int key = (int) me.getKey();
                arrMap.put(key, false);
                int tempKey= key;
                int tempMax_count = 1;
                while (arrMap.containsKey(++key) && (tempMax_count <= arr.length-1) && arrMap.get(key)){
                    tempMax_count++;
                    arrMap.put(key, false);
                }
                key = tempKey;
                while (arrMap.containsKey(--key) && (tempMax_count <= arr.length-1) && arrMap.get(key)){
                    tempMax_count++;
                    arrMap.put(key, false);
                }
                if (tempMax_count == max_count) {
                    if (orderMap.get(key+1) < orderMap.get(start_sq)){
                        max_count = tempMax_count;
                        start_sq  = key+1;
                    }
                } else if (tempMax_count > max_count) {
                    max_count = tempMax_count;
                    start_sq  = key+1;
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        int upperLim = start_sq+max_count-1;
        for (int i = start_sq ; i <= upperLim ; i++)
            ans.add(i);
        return ans;
    }

    public static void findPairsDifferenceK(int[] input, int k){
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0 ; i < input.length ; i++) {
            if (freqMap.containsKey(input[i])) {
                int freq = freqMap.get(input[i]);
                freq++;
                freqMap.put(input[i], freq);
            } else {
                freqMap.put(input[i], 1);
            }
        }

        freqMap.forEach((key, value)->{
            int addK = key + k;
            int subK = key - k;
            if (addK == subK) {
                int freqOfSubK = freqMap.get(subK);
                int numOfPairs = freqOfSubK*(freqOfSubK-1)/2;
                while(numOfPairs > 0) {
                    System.out.println(addK + " " + key);
                    numOfPairs--;
                }
            } else {
                if (freqMap.containsKey(addK)) {
                    int freqOfAddK = freqMap.get(addK);
                    int numOfPairs = value*freqOfAddK;

                    while(numOfPairs > 0) {
                        if(addK < key) {
                            System.out.println(addK + " " + key);
                        } else {
                            System.out.println(key + " " + addK);
                        }
                        numOfPairs--;
                    }
                }
                if (freqMap.containsKey(subK)) {
                    int freqOfSubK = freqMap.get(subK);
                    int numOfPairs = 0;
                    if (subK == key) {
                        numOfPairs = freqOfSubK*(freqOfSubK-1)/2;
                    } else {
                        numOfPairs = value*freqOfSubK;
                    }

                    while(numOfPairs > 0) {
                        if(subK < key) {
                            System.out.println(subK + " " + key);
                        } else {
                            System.out.println(key + " " + subK);
                        }
                        numOfPairs--;
                    }
                }
            }

            freqMap.put(key, 0);
        });
    }

    public static void unique (char[] test) {
        //When test is string
        /*System.out.println(test.chars().distinct().
                mapToObj(c -> String.valueOf((char)c)).
                collect(Collectors.joining()));*/

    }
}
