package javaDsAlgoCN.AdvDS.DynamicProgramming;

import java.util.PriorityQueue;
import java.util.Scanner;

public class DPMain {

    public static int fibRec(int n) {
        if (n==0||n==1) {
            return n;
        }

        return fibRec(n-1) + fibRec(n-2);
    }
    public static int fib(int n) {
        int[] storage = new int[n+1];
        for (int i = 0; i <= n ; i++) {
            storage[i] = -1;
        }

        return fib(n ,storage);
     }
    private static int fib(int n, int[] storage) {
        if (n==0||n==1) {
            storage[n] = n;
            return storage[n];
        }
        if (storage[n] != -1) {
            return storage[n];
        }

        storage[n] = fib(n-1, storage) + fib(n-2, storage);

        return storage[n];
    }
    public static int fibDp(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }
    public static int countStepsTo1(int n){
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;

        for (int i = 4 ; i <= n ; i++) {
            int minWays1 = Integer.MAX_VALUE;
            int minWays2 = Integer.MAX_VALUE;
            int minWays3 = Integer.MAX_VALUE;
            if (i%3 == 0) {
                minWays3 = 1 + dp[i/3];
            }
            if (i%2 == 0) {
                minWays2 = 1 + dp[i/2];
            }
            minWays1 = 1 + dp[i-1];
            dp[i] = Math.min(minWays1, Math.min(minWays2, minWays3));
        }

        return dp[n];
    }
    public static int countStepsTo1Rec(int n) {
        if (n<=3)
            return 1;

        int minWays3 = Integer.MAX_VALUE;
        int minWays2 = Integer.MAX_VALUE;
        if (n%3 == 0) {
            minWays3 = 1 + countStepsTo1Rec(n/3);
        }
        if (n%2 == 0) {
            minWays2 = 1 + countStepsTo1Rec(n/2);
        }

        int minWays1 = 1 + countStepsTo1Rec(n-1);

        return Math.min(minWays1, Math.min(minWays2, minWays3));
    }
    private static long staircase(int n, long[] dyn) {
        if (n <= 2) {
            return n;
        }
        dyn[0] = 1;
        dyn[1] = 1;
        dyn[2] = 2;

        for (int i = 3 ; i <= n ; i++) {
            dyn[i] = dyn[i-1] + dyn[i-2] + dyn[i-3];
        }

        return dyn[n];
    }
    public static long staircase(int n){
        long[] dp = new long[n+1];
        return staircase(n, dp);
    }
    public static int countBalancedBTs(int h) {
        int mod = (int) Math.pow(10, 9) + 7;
        return countBalancedBTs(h, mod);
    }

    private static int countBalancedBTs(int h, int mod) {
        if (h==0|h==1) {
            return 1;
        }

        int x = countBalancedBTs(h-1, mod);
        int y = countBalancedBTs(h-2, mod);

        long res1 = (long)x * x;
        long res2 = (long)x * y * 2;
        int value1= (int)(res1 % mod);
        int value2= (int)(res2 % mod);

        return (value1 + value2) % mod;
    }

    private static int countBalancedBTsDP(int h, int mod) {
        if (h==0|h==1) {
            return 1;
        }
        int[] dp = new int[h+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= h; i++) {
            int x = dp[i-1];
            int y = dp[i-2];
            long res1 = (long)x * x;
            long res2 = (long)x * y * 2;
            int value1= (int)(res1 % mod);
            int value2= (int)(res2 % mod);

            dp[i] = (value1 + value2) % mod;
        }

        return dp[h];
    }
    public static void main(String[] args) {

        System.out.println(countBalancedBTsDP(7, 1000000007));
    }
}
