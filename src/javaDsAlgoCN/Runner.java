package javaDsAlgoCN;

import java.util.*;
import java.util.stream.Collectors;

public class Runner {
    public static String[] cake=new String [55];

    int calcAverage(double d, int i) {
        int k = 2;
        return k;
    }

    int calcAverage(int j, double d1) {
        return 0;
    }

    public static void print(int n){
        if(n < 0){
            return;
        }
        if(n == 0){
            System.out.println(n);
            return;
        }
        print(n--);
        System.out.print(n+" ");
    }

    public static void justAFunc(int n){
        System.out.println(n);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        /*
        int n,m;
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        scan.nextLine();
        for(int i=0;i<n;i++)
        {
            cake[i]=scan.nextLine();
        }
        System.out.println(new Solution().solve(n,cake));*/

        /*int[] nums = {1,9,2,3,4,4,5,6,5,3,2,2,1,1,2,3,5,6,6,6,5,5,6,5,1,1,1,7,5,5,5,5,5,5,5};
        int[] res  = Solution.topKFrequent(nums, 4);
        for (int i : res) {
            System.out.println(i);
        }*/
/*

        int[] arr1 = {1,2,3};
        int[] arr2 = {2,5,5};

        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        a.add(1);
        a.add(5);
        a.add(7);
        a.add(7);

        b.add(0);
        b.add(1);
        b.add(2);
        b.add(3);

        List<Integer> output = Solution.merge(a, b);
        for (int ele : output)
            System.out.print(ele +" ");

        String[] ans = Solution.keypad(123);
        for (String s:
             ans) {
            System.out.println(s);
        }
*/
        System.out.println(Solution.removeDuplicateLetters("cbacdcbc"));
    }
}

class Solution {

    private static String addStars(String s, int si) {
        if (si > s.length() - 1) {
            return "";
        }
        if (s.charAt(si) == s.charAt(si - 1)) {
            return "*" + s.charAt(si) + addStars(s, si + 1);
        } else
            return s.charAt(si) + addStars(s, si + 1);
    }

    public static void merge(int[] input, int mid, int si, int ei) {
        int n1;
        int n2;
        int[] lft;
        int[] rght;

        n1 = mid - si + 1;
        n2 = ei - mid;
        lft = new int[n1];
        rght = new int[n2];

        for (int i = 0; i < n1; ++i)
            lft[i] = input[si + i];
        for (int j = 0; j < n2; ++j)
            rght[j] = input[mid + 1 + j];

        int i = 0, j = 0;
        int k = si;
        while (i < n1 && j < n2) {
            if (lft[i] <= rght[j]) {
                input[k] = lft[i];
                i++;
            } else {
                input[k] = rght[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            input[k] = lft[i];
            i++;
            k++;
        }
        while (j < n2) {
            input[k] = rght[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int[] input, int si, int ei) {
        if (si >= ei) return;
        else {
            int mid = (si + ei) / 2;
            mergeSort(input, si, mid);
            mergeSort(input, mid + 1, ei);
            merge(input, mid, si, ei);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int partition(int[] input, int si, int ei) {
        //Find PIVOT
        int pivot = input[ei];
        //Ensure elements less than or equal to pivot are at left and elements greater than are at right
        int lft = si - 1;

        for (int i = si; i < ei; i++) {
            if (input[i] < pivot) {
                lft++;
                swap(input, lft, i);
            }
        }

        //Place pivot at it's actual position
        swap(input, lft + 1, ei);

        return (lft + 1);
    }

    public static void quickSort(int[] input, int si, int ei) {
        //Base Case
        if (si >= ei) return;
        int pivot_pos = partition(input, si, ei);
        quickSort(input, si, pivot_pos - 1);
        quickSort(input, pivot_pos + 1, ei);
    }

    public static String addStars(String s) {
        return s.charAt(0) + addStars(s, 1);
    }

    private static String[] keypad(String[] digit_map, int n) {
        if (n <= 0) {
            String[] ans = {""};
            return ans;
        }
        String[] small_ans = keypad(digit_map, n / 10);
        int dig = n % 10;
        String chars = digit_map[dig];
        String[] ans = new String[small_ans.length * chars.length()];
        int k = 0;
        for (int j = 0; j < small_ans.length; j++) {
            for (int i = 0; i < chars.length(); i++) {
                ans[k++] = small_ans[j] + chars.charAt(i);
            }
        }
        return ans;
    }

    public static String[] keypad(int n) {
        String[] digit_map = {"", "", "abc", "def",
                "ghi", "jkl", "mno",
                "pqrs", "tuv", "wxyz"};
        return keypad(digit_map, n);
    }

    private static void printKeypad(String[] digit_map, int n, String outputSoFar) {
        if (n <= 0) {
            System.out.println(outputSoFar);
            return;
        }
        String chars = digit_map[n % 10];
        for (int i = 0; i < chars.length(); i++) {
            printKeypad(digit_map, n / 10, chars.charAt(i) + outputSoFar);
        }
    }

    public static void printKeypad(int n) {
        String[] digit_map = {"", "", "abc", "def",
                "ghi", "jkl", "mno",
                "pqrs", "tuv", "wxyz"};
        printKeypad(digit_map, n, "");
    }

    /*public static boolean checkAB(String input) {
        if (input == "") {
            return true;
        }
        if (input.charAt(0) == 'a') {
            if (input.charAt(1) == 'a')
                return checkAB(input.substring(2));
            else if (input.charAt(1) == 'b')
                return checkAB(input.substring(2));
            if (input.substring(1) == "")
                return true;
            else
                return false;
        } else if (input.charAt(0) == 'b') {
            if (input.length() == 1)
                return true;
            else if (input.charAt(1) == 'b')
                return checkAB(input.substring(2));
            else
                return false;
        }
        return false;
    }*/
    public static boolean checkAB(String input) {
        if (input.equals("")) {
            return true;
        }
        String temp = input;
        if (input.length() > 2) {
            if (input.charAt(0) == 'a') {
                if (temp.charAt(1) == 'a' && temp.charAt(2) == 'b' && temp.charAt(3) == 'b') {
                    return checkAB(input.substring(4));
                } else if (temp.charAt(1) == 'b' && temp.charAt(2) == 'b') {
                    return checkAB(input.substring(3));
                }
                if (temp.substring(1) == "") {
                    return true;
                } else {
                    return checkAB(input.substring(1));
                }
            } else if (input.charAt(0) == 'b') {
                if (temp.charAt(1) == 'b' && temp.charAt(2) == 'a') {
                    return checkAB(input.substring(2));
                } else if (temp.charAt(1) == 'b' && temp.substring(2) == "") {
                    return true;
                } else {
                    return false;
                }
            } else
                return false;
        } else {
            if (input.length() == 1) {
                if (input.charAt(0) == 'a')
                    return true;
            }
            else if (input.charAt(0) == 'a' && input.charAt(1) == 'a')
                return true;
            else
                return false;
        }
        return true;
    }

    private static int binarySearch(int[] input, int element, int si, int ei) {
        if (si > ei) return -1;
        int mid = (si + ei)/2;
        if (input[mid] == element)
            return mid;
        else if (input[mid] > element)
            return binarySearch(input, element, si, mid - 1);
        else
            return binarySearch(input, element, mid + 1, ei);
    }

    public static int binarySearch(int input[], int element) {
        return binarySearch(input, element, 0, input.length - 1);
    }

    public static int firstAppearance(int input[], int element, int si, int ei){
        if (si > ei) return -1;
        int mid = (si + ei)/2;
        if (mid == 0 || input[mid] == element && input[mid-1] < element)
            return mid;
        else if (input[mid] >= element)
            return firstAppearance(input, element, si, mid - 1);
        else
            return firstAppearance(input, element, mid + 1, ei);
    }

    public static int lastAppearance(int input[], int element, int si, int ei){
        if (si > ei) return -1;
        int mid = (si + ei)/2;
        if (mid == input.length-1 || input[mid] == element && input[mid+1] > element)
            return mid;
        else if (input[mid] <= element)
            return lastAppearance(input, element, mid + 1, ei);
        else
            return lastAppearance(input, element, si, mid -1);
    }

    public static int countAppearance(int[] input, int n) {
        int firstApp = firstAppearance(input, n, 0, input.length-1);
        int lastApp = lastAppearance(input, n, 0, input.length-1);

        int count = (lastApp - firstApp) + 1;

        return count;
    }

    public static void pairSum(int[] arr, int num){
        mergeSort(arr,0, arr.length - 1);

        int i = 0;
        int j = arr.length - 1;

        while (i < j) {
            if ((arr[i] + arr[j]) == num){
                int countOfi = countAppearance(arr, arr[i]);
                int countOfj = countAppearance(arr, arr[j]);

                int numOfPairs = countOfi * countOfj;
                if (arr[i] == arr[j])
                    numOfPairs = (countOfi-1)*countOfi/2;
                for (int k = 1 ; k<=numOfPairs ; k++) {
                    System.out.println(arr[i] + " " + arr[j]);
                }
                i += countOfi;
                j -= countOfj;
            } else if ((arr[i] + arr[j]) > num) {
                j--;
            } else{
                i++;;
            }
        }
    }

    public static void FindTriplet(int[] arr, int sum){
        int n = arr.length;
        mergeSort(arr, 0, n - 1);

        for (int i = 0 ; i<n-1 ; i ++) {
            int lft = i+1;
            int rght = n-1;
            int x = arr[i];
            while(lft<rght){
                if (x + arr[lft] + arr[rght] == sum ){
                    System.out.println(x + " " + arr[lft] + " " + arr[rght]);
                    lft++;
                    rght--;
                } else if (x + arr[lft] + arr[rght] < sum)
                    lft++;
                else
                    rght--;
            }
        }
    }

    private static boolean checkSequence(String a, String b, int m, int n){
        if (n==0)
            return true;
        if (m==0)
            return false;
        if (a.charAt(m) == b.charAt(n))
            return checkSequence(a, b, m-1, n-1);
        return checkSequence(a, b, m-1, n);
    }

    public static boolean checkSequence(String a, String b) {
        return checkSequence(a, b, a.length()-1, b.length()-1);
    }

    public static void intersection(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int[] intersection;
        int i = 0;
        int j = 0;
        int k = 0;

        mergeSort(arr1, 0, n1-1);
        mergeSort(arr2, 0, n2-1);
        if (n1 < n2){
            intersection = new int[n1];
        } else {
            intersection = new int[n2];
        }

        while(i < n1 && j < n2) {
            if (arr1[i] == arr2[j]){
                intersection[k++] = arr1[i];
                i++;
                j++;
            } else if (arr1[i] < arr2[j]){
                i++;
            } else {
                j++;
            }
        }

        for (int num : intersection) {
            if (num == 0) break;
            System.out.println(num);
        }
    }

    public static void rotate(int[] input, int si, int ei) {
        int temp = input[si];
        for(int i = si+1 ; i <= ei ; i++ ) {
            input[i-1] = input[i];
        }
        input[ei] = temp;
    }

    public static void reverse(int[] input, int si, int ei) {
        int i = si;
        int j = ei;
        int temp;
        while (i<j) {
            temp = input[i];
            input[i] = input[j];
            input[j] = temp;
            i++;
            j--;
        }
    }

    public static void rotate(int[] arr, int d) {
        d = d % arr.length;
        reverse(arr, 0, d-1);
        reverse(arr, d, arr.length -1);
        reverse(arr,0, arr.length-1);
    }

    private static void printSubsets(int[] input, int si, String out) {
        if (si == input.length){
            System.out.println(out.trim());
            return;
        }
        printSubsets(input, si+1, out + " " + input[si]);
        printSubsets(input, si+1, out);
    }

    public static void printSubsets(int[] input) {
        printSubsets(input, 0, "");
    }

    public static int[][] subsets(int[] input, int si, int ei) {
        //int[][] ans = new int[(int)Math.pow(2, input.length)][];
        if (si > ei) {
            int[][] smallAns = {{}};
            return smallAns;
        }

        int[][] smallAns = subsets(input, si +1 , ei);
        int[][] ans = new int[2*smallAns.length][];
        int k = 0;
        int l = 0;
        for (int i = 0 ; i<smallAns.length ; i++ )
            ans[k++] = smallAns[i];
        for (; k < ans.length ; k++){
            ans[k] = new int[smallAns[l].length + 1];
            int m = 0;
            for (int j = 0 ; j < ans[k].length ; j++) {
                if (j == 0) ans[k][j] = input[si];
                else
                    ans[k][j] = smallAns[l][m++];
            }
            l++;
        }
        return ans;
    }

    private static int posOfMinElement(int[] arr, int si, int ei) {
        int mid = (si + ei)/2;
        if (mid < ei && arr[mid] > arr[mid+1]) {
            return mid+1;
        }
        if (mid > si && arr[mid]<arr[mid-1]) {
            return mid;
        }
        else if (arr[mid] > arr[ei]){
            return posOfMinElement(arr, mid+1, ei);
        }
        else {
            return posOfMinElement(arr, si, mid-1);
        }
    }

    public static int arrayRotateCheck(int[] arr) {
        int posFromLst = arr.length - posOfMinElement(arr, 0, arr.length-1);
        return posFromLst;
    }

    public static int[][] subsetsSumK(int[] input, int si, int k){
        if (si == input.length) {
            if (k == 0)
                return new int[1][0];
            else
                return new int[0][0];
        }
        int[][] temp1 = subsetsSumK(input, si + 1, k - input[si]);
        int[][] temp2 = subsetsSumK(input, si + 1, k);
        int include = input[si];
        int[][] output = new int[temp1.length+temp2.length][];
        int l = 0;
        for (int i = 0 ; i < temp1.length ; i++ ) {
            output[i] = new int[temp1[i].length + 1];
            int m = 0;
            for (int j = 0 ; j < temp1[l].length+1 ; i++ ){
                if (j == 0) output[i][j] = include;
                else
                    output[i][j] = temp1[l][m++];
            }
            l++;
        }
        return output;
    }

    public static char getChar(int n) {
        return (char)(96 + n);
    }

    public static String[] getCode(String input) {
        if (input.length()==0) {
            String[] output = {""};
            return output;
        }
        int firstDig = input.charAt(0) - '0';
        String[] output1 = getCode(input.substring(1));
        String[] output2 = new String[0];
        int first2Dig = 0;
        if (input.length() >= 2) {
            first2Dig = (input.charAt(0)-'0')*10 + (input.charAt(1)-'0');
            if (first2Dig > 10 && first2Dig <= 26) {
                output2 = getCode(input.substring(2));
            }
        }
        String[] output = new String[output1.length + output2.length];

        int k = 0;
        for (String s:output1) {
            char firstDigChar = getChar(firstDig);
            output[k] = firstDigChar + s;
            k++;
        }
        for (String s:output2) {
            char first2DigChar = getChar(first2Dig);
            output[k] = first2DigChar + s;
            k++;
        }
        return output;
    }

    public static void printAllPossibleCodes(String input) {
        printCodes(input, "");
    }

    private static void printCodes(String input, String ans) {
        if(input.length() == 0){
            System.out.println(ans);
            return;
        }

        int firstDig = input.charAt(0) -'0';
        char ch1 = getChar(firstDig);
        printCodes(input.substring(1), ans + ch1);
        if (input.length() >= 2){
            int first2Dig = (input.charAt(0) - '0')*10 + (input.charAt(1) - '0');
            if (first2Dig > 0 && first2Dig<=26){
                char ch2 = getChar(first2Dig);
                printCodes(input.substring(2), ans + ch2);
            }
        }
    }

    public static int maximumProfit(int budget[]) {
        int currProfit;
        // Sort Array first
        Arrays.sort(budget);
        int len = budget.length;
        int mid = (len)/2;
        int maxProfit1 = budget[mid]*(len-mid);
        int maxProfit2 = budget[mid+1]*(len-(mid+1));
        for(int i = mid-1 ; i >= 0 ; i--){
            currProfit = budget[i]*(len-i);
            if(currProfit >= maxProfit1)
                maxProfit1 = currProfit;
        }
        for (int j = mid+1; j<len ; j++){
            currProfit = budget[j]*(len-j);
            if(currProfit >= maxProfit2)
                maxProfit2 = currProfit;
        }
        return maxProfit1>maxProfit2?maxProfit1:maxProfit2;
    }

    public boolean searchInGrid(String[] Graph, int row, int col, String sentence) {
        int[] x = {0,-1,-1,-1,0,1,1,1};
        int[] y = {-1,-1,0,1,1,1,0,-1};

        if (Graph[row].charAt(col) != sentence.charAt(0))
            return false;

        for(int dir = 0 ; dir < 8 ; dir++) {
            int k;
            int rd = row + x[dir];
            int cd = col + y[dir];

            for (k = 1 ; k < sentence.length() ; k++) {
                if (rd >= Graph.length || rd < 0 || cd >= Graph[row].length() || cd < 0)
                    break;
                if (Graph[rd].charAt(cd) != sentence.charAt(k))
                    break;
                row = rd;
                col = cd;
                rd += x[dir];
                cd += y[dir];
            }

            if (k==sentence.length())
                return true;
        }
        return false;
    }

    public int solve(String[] Graph , int N, int M) {
        for (int i = 0 ; i < Graph.length ; i++) {
            for (int j = 0 ; j < Graph[i].length() ; j++) {
                if (searchInGrid(Graph, i, j, "CODINGNINJA")) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public static int getLargestPiece(String[] cake, int n, int r, int c, boolean[][] visited) {
        int largestPiece = 0;
        if (r < 0 || r >= n || c < 0 || c >= cake[0].length()) {
            return 0;
        }

        if (cake[r].charAt(c) == '0') {
            return 0;
        }

        if (visited[r][c]) {
            return 0;
        }

        if (cake[r].charAt(c) == '1' && !visited[r][c]) {
            visited[r][c] = true;
            int opt_dir1 = getLargestPiece(cake, n, r-1, c, visited);
            int opt_dir2 = getLargestPiece(cake, n, r, c+1, visited);
            int opt_dir3 = getLargestPiece(cake, n,r+1, c, visited);
            int opt_dir4 = getLargestPiece(cake, n, r, c-1, visited);
            //largestPiece = Math.max(opt_dir1, Math.max(opt_dir2, Math.max(opt_dir3, opt_dir4)));
            largestPiece = opt_dir1 + opt_dir2 + opt_dir3 + opt_dir4;
        }

        return largestPiece+1;
    }

    public int solve(int n,String cake[]) {
        int maxPiece = Integer.MIN_VALUE;
        boolean[][] visited = new boolean[n][cake[0].length()];

        for (int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < cake[i].length() ; j++) {
                if (cake[i].charAt(j) == '1' && !visited[i][j]){
                    //visited[i][j] = true;
                    int tempMaxPiece = getLargestPiece(cake, n, i, j, visited);
                    if (tempMaxPiece > maxPiece) {
                        maxPiece = tempMaxPiece;
                    }
                }
            }
        }
        return maxPiece;
    }

    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int ele : nums) {
            if (freqMap.containsKey(ele)) {
                int currVal = freqMap.get(ele);
                currVal++;
                freqMap.put(ele, currVal);
            } else {
                freqMap.put(ele, 1);
            }
        }
        int[] result = new int[k];
        List<Map.Entry<Integer, Integer>> mapList = new ArrayList<>(freqMap.entrySet());
        Collections.sort(mapList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o2, Map.Entry<Integer, Integer> o1) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        System.out.println(mapList);
        int itr = 0;
        for (Map.Entry<Integer, Integer> me : mapList){
            if (itr == k){
                break;
            }
            result[itr] = me.getKey();
            itr++;
        }

/*      HashMap<Integer, Integer> sortedMapVal = freqMap.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        int itr = 0;
        for (Map.Entry<Integer, Integer> me : sortedMapVal.entrySet()) {
            if (itr == k){
                break;
            }
            result[itr] = me.getKey();
            itr++;
        }*/

        return result;
    }

    public static List<Integer> merge(List<Integer> arr1, List<Integer> arr2) {
        int n1 = arr1.size();
        int n2 = arr2.size();

        List<Integer> output = new ArrayList<>();

        int i = 0, j = 0;
        int k = 0;
        while (i < n1 && j < n2) {
            if (arr1.get(i) <= arr2.get(j)) {
                output.add(arr1.get(i));
                i++;
            } else {
                output.add(arr2.get(j));
                j++;
            }
            k++;
        }
        while (i < n1) {
            output.add(arr1.get(i));
            i++;
            k++;
        }
        while (j < n2) {
            output.add(arr2.get(j));
            j++;
            k++;
        }
        return output;
    }

    public static void commonSubstring(List<String> a, List<String> b) {
        for (int i = 0 ; i < a.size() ; i++) {
            String s1 = a.get(i);
            String s2 = b.get(i);

            if (twoStrings(s1, s2)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static boolean twoStrings(String s1, String s2)  {
        boolean v[]=new boolean[26];
        Arrays.fill(v,false);

        for (int i = 0; i < s1.length(); i++)
            v[s1.charAt(i) - 'a'] = true;

        for (int i = 0; i < s2.length(); i++)
            if (v[s2.charAt(i) - 'a'])
                return true;

        return false;
    }

    public static boolean detectCapitalUse(String word) {
        if (word == null || word.isEmpty() ||word.length() == 1) {
            return true;
        }
        int flag =0;
        for (int i=0;i<word.length();i++) {
            if (65<=word.charAt(i) && word.charAt(i)<=90)
                flag++;
        }
        if (flag==0 || flag==word.length())
            return true;
        if (flag==1 && word.charAt(0)>=65 && word.charAt(0)<=90)
            return true;
        return false;
    }

    public static boolean isPrime(int num) {
        if (num<=3)
            return true;
        if (num%2==0)
            return false;
        for (int i=3;i*i<=num;i+=2) {
            if (num%i==0){
                return false;
            }
        }
        return true;
    }

    public static int[][] subsetsSumToKHelper(int[] input, int bi, int k) {
        if (bi == input.length) {
            if (k == 0) {
                return new int[1][0];
            } else {
                return new int[0][0];
            }
        }

        int[][] smallOutput1 = subsetsSumToKHelper(input, bi+1, k);
        int[][] smallOutput2 = subsetsSumToKHelper(input, bi+1, k-input[bi]);
        int[][] output = new int[smallOutput1.length+smallOutput2.length][];

        int index = 0;
        for(int i = 0 ; i < smallOutput1.length;++i) {
            output[index++] = smallOutput1[i];
        }

        for(int i = 0 ; i<smallOutput2.length ; ++i) {
            output[index] = new int[smallOutput2[i].length+1];
            output[index][0] = input[bi];
            for (int j = 0 ; j < smallOutput2[i].length;j++) {
                output[index][j+1] = smallOutput2[i][j];
            }
            index++;
        }
        return output;
    }

    public static int[][] subsetsSumToK(int[] input, int k) {
        return subsetsSumToKHelper(input, 0, k);
    }

    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] orderMaintained = new int[nums.length];
        int j =0;
        for (int i : nums)
            orderMaintained[j++] = i;
        Arrays.sort(nums);
        int[] ans = new int[nums.length];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 0);
        int count = 1 ;
        for(int i= 1 ; i<nums.length ; i++) {
            if (!map.containsKey(nums[i]))
                map.put(nums[i], count);
            count++;
        }
        int index = 0;
        for (int i : orderMaintained) {
            ans[index++] = map.get(i);
        }

        return ans;
    }

    public static int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (ar2, ar1)->{
            if (ar2[1] == ar1[1])
                if (ar2[0] > ar1[0])
                    return -1;
            return ar2[1]-ar1[1];
        });

        int ans = intervals.length;
        int[] prev = intervals[ans-1];
        for (int i = intervals.length-2 ; i >= 0 ; --i) {
            int[] curr = intervals[i];
            if (curr[0] >= prev[0] && curr[1] <= prev[1]) {
                ans--;
            } else {
                prev = curr;
            }
        }

        return ans;
    }

    public static String removeDuplicateLetters(String s) {
        boolean[] isDone = new boolean[26];
        java.util.Stack<Character> unique = new java.util.Stack<>();
        int[] lastIndex = new int[26];
        for (int i = 0 ; i < s.length() ; i++)
            lastIndex[s.charAt(i)-'a'] = i;

        for (int i = 0 ; i < s.length() ; i++) {
             char currCh = s.charAt(i);
             if (isDone[currCh-'a'])
                 continue;
             while(!unique.isEmpty() && unique.peek() > currCh && i<lastIndex[unique.peek()-'a']) {
                 isDone[unique.pop()-'a'] = false;
             }
             unique.push(currCh);
             isDone[currCh-'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        while(!unique.isEmpty())
            sb.append(unique.pop());

        return sb.reverse().toString();
    }

    public static boolean buddyStrings(String A, String B) {
        if (A.length() != B.length() || A.length()<2 || B.length()<2)
            return false;
        if (A.length() == 0 || B.length() == 0)
            return false;

        if (A.equals(B)) {
            HashSet<Character> diff = new HashSet<>();
            for (char ch : A.toCharArray()) {
                diff.add(ch);
            }
            if (diff.size() < A.length())
                return true;
            else
                return false;
        }

        int diff = 0;

        char aChar=' ';
        char bChar=' ';

        for (int i = 0 ; i < A.length() ; i++) {
            if (A.charAt(i) != B.charAt(i)) {
                if (diff > 0) {
                    if (A.charAt(i) != bChar || B.charAt(i) != aChar) {
                        return false;
                    }
                } else {
                    aChar = A.charAt(i);
                    bChar = B.charAt(i);
                }
                diff++;
            }
        }

        return (diff==2);
    }
}

//class Solution {
//
//    // Return the changed string
//    public static String replace(String input){
//        if (input.length() <= 1) {
//            return input;
//        }
//        if (input.charAt(0) == 'p' && input.length() >= 2 && input.charAt(1) == 'i')
//            return "3.14" + replace(input.substring(2, input.length()));
//
//        return input.charAt(0) + replace(input.substring(1, input.length()));
//    }
//}


//class Solution {
//
//    public static boolean isStringPalindrome(String input) {
//
//        if (input.length() == 0 || input.length() == 1) return true;
//        if (input.charAt(0) != input.charAt(input.length() - 1)) return false;
//
//        String newInput = input.substring(1, input.length() - 1);
//        return isStringPalindrome(newInput);
//    }
//}


//class Solution {
//
//    private static int[] allIndexes(int input[], int[] allIndices, int x, int aisi, int si) {
//        if (si == input.length) {
//            return allIndices;
//        }
//        if (input[si] == x) {
//            allIndices[aisi] = si;
//            aisi++;
//            return allIndexes(input, allIndices, x, aisi, si + 1);
//        }
//        else return allIndexes(input, allIndices, x, aisi, si + 1);
//    }
//
//    public static int[] allIndexes(int input[], int x) {
//        /* Your class should be named Solution
//         * Don't write main().
//         * Don't read input, it is passed as function argument.
//         * Return output and don't print it.
//         * Taking input and printing output is handled automatically.
//         */
//        int i;
//        int j;
//        int[] allIndices = new int[input.length];
//        allIndices = allIndexes(input, allIndices, x, 0, 0);
//                if (allIndices[0] != 0){
//                    for (i = 0 ; i < allIndices.length ; i++ )
//                        if (allIndices[i] == 0)
//                            break;
//                } else {
//                    for (i = 1 ; i < allIndices.length ; i++ )
//                        if (allIndices[i] == 0)
//                            break;
//                }
//
//        int[] actAns = new int[i];
//        for (j = 0 ; j < i ; j++ )
//            actAns[j] = allIndices[j];
//        return actAns;
//    }
//
//}
//class Solution {
//
//    public static boolean checkNumber(int input[], int x) {
//        /* Your class should be named Solution
//         * Don't write main().
//         * Don't read input, it is passed as function argument.
//         * Return output and don't print it.
//         * Taking input and printing output is handled automatically.
//         */
//            if(input[0] == x)
//                return true;
//            if (input.length == 1)
//                return false;
//        int[] smallInput = new int[input.length - 1];
//        for (int i = 1 ; i<input.length ; i++)
//            smallInput[i - 1] = input[i];
//
//        return checkNumber(smallInput, x);
//    }
//}

