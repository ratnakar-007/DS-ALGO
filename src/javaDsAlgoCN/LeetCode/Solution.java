package javaDsAlgoCN.LeetCode;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
       /* System.out.println(hIndex2(new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}));
        System.out.println(hIndex(new int[]{5,6,7,8,9}));
        //System.out.println(longestPalindrome("bb"));
        int[] ans = distributeCandies(10, 3);
        System.out.print("[");
        for (int i = 0; i < ans.length; i++) {
            if (i==ans.length-1)
                System.out.print(ans[i]);
            else
                System.out.print(ans[i] + ", ");
        }
        System.out.println("]");
        Integer[] input = new Integer[4];
        input[0] = 9;
        input[1] = 34;
        input[2] = 38;
        input[3] = 97;
        System.out.println(largestNumber(input));

        /*String[] Grid = {"DANDO", "NNINJ", "AXGJC", "INJAA", "CODDI"};
        System.out.println(wordSearch(Grid, "CODINGNINJA"));

        String S = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels(S));*/
        int[] nums = new int[]{0,1,0,3,12};
        Solution.moveZeroes(nums);
        for (int i:
             nums) {
            System.out.print(i +", ");
        }
    }

    public static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int i = citations.length-1;
        for ( ; i>=0; i--) {
            if (citations[i] < (citations.length-i-1))
                break;
        }
        
        return citations.length-i-1;
    }

    public static int hIndex2(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length, i;
        for(i = 1; i <= n; ++i)
            if(citations[n-i] < i) break;
        return i-1;
    }

    public static int titleToNumber2(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++){
            result = result * 26 + (s.charAt(i) - 'A' + 1);
        }
        return result;

    }

    public static int titleToNumber(String s) {
        if (s.length()==1) {
            return s.charAt(0)-'A' + 1;
        }
        int ans = 0;
        for (int i = 1 ; i <= s.length()-1 ; i++) {
            ans += (int)Math.pow(26, i);
        }
        ans += Math.pow(26, s.length()-1)*(s.charAt(0) - 'A');
        return ans+titleToNumberUtil(s.substring(1));
    }

    private static int titleToNumberUtil(String s) {
        if(s.length() == 0) {
            return 1;
        }
        int ans = (int) Math.pow(26, s.length()-1)*(s.charAt(0) - 'A');
        return ans + titleToNumberUtil(s.substring(1));
    }

    public static List<Integer> pascalTriangle(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= rowIndex; i++) {
            ans.add(1);
        }
        for (int i = 1 ; i < rowIndex-1 ; i++) {
            for (int j = i; j >0 ; j--) {
                ans.set(j, ans.get(j) + ans.get(j-1));
            }
        }
        return ans;
     }

    public static int longestPalindrome(String s) {
        int ans = 0;
        boolean isCountOdd = false;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0 ; i<s.length() ; i++) {
            if (map.containsKey(s.charAt(i))) {
                int count = map.get(s.charAt(i));
                map.put(s.charAt(i), count+1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }

        for (Map.Entry<Character, Integer> me : map.entrySet()) {
            if (me.getValue()%2 == 0) {
                ans+=me.getValue();
            } else {
                isCountOdd = true;
                ans+=(me.getValue()-1);
            }
        }
        if(isCountOdd) return ans+1;

        return ans;
    }

    public static int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        if(candies == 0) {
            Arrays.fill(ans, 0);
            return ans;
        }
        int itr = 0;
        while (candies > 0){
            for (int i = 1 ; i <= num_people ; i++) {
                int candiesGiven = i+(itr)*num_people;
                if (candiesGiven > candies) {
                    ans[i-1] += candies;
                    candies -= candiesGiven;
                    break;
                } else {
                    candies -= candiesGiven;
                    ans[i-1] += candiesGiven;
                }
            }
            itr++;
        }
        return ans;
    }

    public static String largestNumber(Integer[] input) {
        StringBuilder ans = new StringBuilder("");
        Arrays.sort(input, (i2, i1)->{
            String i2i1 = ""+i2+i1;
            String i1i2 = ""+i1+i2;

            return i2i1.compareTo(i1i2) > 0 ? -1 : 1;
        });
        for (int i : input) {
            ans = ans.append(i);
        }
        return ans.toString();
    }

    public static int wordSearch(String[] Grid, String pattern) {
        int ans = 0;
        int[][] directions = {{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
        int[][] used = new int[Grid.length][Grid[0].length()];
        for (int i = 0 ; i< Grid.length ; i++){
            for(int j = 0 ; j< Grid[i].length() ;j++) {
                char ch = Grid[i].charAt(j);
                if (ch == pattern.charAt(0)) {
                    ans = wordSearchUtil(Grid, used, pattern,0, i, j, directions);
                    if (ans == 1) {
                        break;
                    }
                }
                if (ans == 1) {
                    break;
                }
            }
        }
        return ans;
    }

    private static int wordSearchUtil(String[] grid, int[][] used, String pattern,
                                      int index, int x, int y, int[][] directions) {
        if (index == pattern.length()) return 1;
        used[x][y] = 1;
        int i, newx, newy;
        int found = 0;
        for (i = 0 ; i < directions.length ; i++) {
            newx = x + directions[i][0];
            newy = y + directions[i][1];
            if (validPoints(newx, newy, grid) &&
                    grid[newx].charAt(newy) == pattern.charAt(index) &&
                    used[newx][newy] != 1) {
                found = found | wordSearchUtil(grid, used, pattern, index+1, newx, newy, directions);
            }
        }
        return found;
    }

    private static boolean validPoints(int x, int y, String[] grid) {
        if (x>=0 && x<grid.length && y>=0 && y< grid[0].length()) {
            return true;
        } return false;
    }

    public static String largestTimeFromDigit(int[] A) {
        String result = "";
        for (int i = 0; i <A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                for (int k = 0; k < A.length; k++) {
                    if (i == j || i == k || j == k) {
                        continue;
                    }
                    String hh = ""+ A[i] + A[j];
                    String mm = ""+ A[k] + A[6-i-j-k];
                    String currTime = hh + ":"+ mm;
                    if (hh.compareTo("24")<0 && mm.compareTo("60")<0 && currTime.compareTo(result) > 0) {
                        result = currTime;
                    }
                }
            }
        }
        return result;
    }

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k==0) return false;
        int end = nums.length-1;
        int rstart = end-k;
        if (rstart<0) {
            rstart = 0;
        }
        int tempRStart;
        while(end>0) {
            tempRStart = rstart;
            while(tempRStart<end) {
                if (Math.abs(nums[tempRStart]-nums[end])<=t) {
                    return true;
                }
                tempRStart++;
            }
            rstart--;
            if (rstart<0) {
                rstart = 0;
            }
            end--;
        }
        return false;
    }

    public static int arrayToll(int[] A) {
        //LinkedList<Integer> ll = new LinkedList<>();
        int len = 1;
        if (A[0] == -1) {
            return len;
        }
        int cntr = A[0];
        while (A[cntr] != -1) {
            len++;
            cntr = A[cntr];
        }
        return len+1;
    }

    public static List<Integer> partitionLabels(String S) {
        int[] lastIndex = new int[26];
        for (int i = 0 ; i < S.length() ; i++) {
            lastIndex[S.charAt(i) - 'a'] = i;
        }
        List<Integer> ans = new ArrayList<>();
        int start = 0 ; int end = 0;
        for (int i = 0; i < S.length(); i++) {
            end = Math.max(end, lastIndex[S.charAt(i) - 'a']);
            if (i == end) {
                ans.add(end-start+1);
                start = i+1;
            }
        }
        return ans;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static boolean carPooling(int[][] trips, int capacity) {
        int maxEl = Integer.MIN_VALUE;
        for (int[] ar : trips)
            if (maxEl < ar[2])
                maxEl = ar[2];
        int[] transportLog = new int[maxEl+1];

        for (int[] ar : trips) {
            transportLog[ar[1]] += ar[0];
            transportLog[ar[2]] -= ar[0];
        }
        for (int j = 1 ; j < maxEl ; j++) {
            transportLog[j] += transportLog[j-1];
            if (transportLog[j] > capacity)
                return false;
        }
        return true;
    }

    public static List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        if(n==0) return ans;
        if(n==1){
            ans.add(nums[0]);
            return ans;
        }
        if(n==2){
            ans.add(nums[0]);
            if (nums[0]!=nums[1])
                ans.add(nums[1]);
            return ans;
        }
        Integer majF = null;
        Integer majS = null;
        int third = n/3;
        int countF = 0;
        int countS = 0;
        for (int i = 0 ; i<nums.length ; i++) {
            if (majF==null ||nums[i] == majF) {
                majF = nums[i];
                countF++;
            } else if (majS==null || nums[i] == majS) {
                majS = nums[i];
                countS++;
            } else if (countF == 0) {
                countF = 1;
                majF = nums[i];
            } else if (countS == 0) {
                countS = 1;
                majS = nums[i];
            } else {
                countF--;
                countS--;
            }
        }
        countF = 0; countS = 0;
        for(int i = 0 ; i<nums.length ; i++) {
            if(majF != null && nums[i]==majF) countF++;
            if(majS != null && nums[i]==majS) countS++;
        }
        if (countF > third) ans.add(majF);

        if (majS != majF && countS > third) ans.add(majS);

        return ans;
    }

    public static char findTheDifference(String s, String t) {
        int[] chrs = new int[26];
        char ans = ' ';
        for (int i = 0 ; i<s.length() ; i++) {
            chrs[s.charAt(i)-'a']++;
        }
        for (int i = 0 ; i<t.length() ; i++) {
            chrs[t.charAt(i)-'a']--;
            if (chrs[t.charAt(i)-'a'] < 0) {
                return t.charAt(i);
            }
        }
        return ans;
    }

    public static void moveZeroes(int[] nums) {
        int start = 0;
        int i = 1;
        for (;i<nums.length;i++){
            if (nums[i] != 0 && nums[start] == 0) {
                nums[start] = nums[i];
                start++;
                nums[i] = 0;
            } else if(nums[start]==0) {

            } else {
                start++;
            }
        }
    }
}
