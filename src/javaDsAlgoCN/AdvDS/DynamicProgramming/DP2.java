package javaDsAlgoCN.AdvDS.DynamicProgramming;

import java.util.Scanner;

public class DP2 {
    private static int minCostPathRec(int[][] input, int row, int col) {
        if (row > (input.length - 1) || col > (input[0].length - 1)) {
            return Integer.MAX_VALUE;
        } else if (row == (input.length - 1) && col == (input[0].length - 1)) {
            return input[row][col];
        } else
            return input[row][col] + Math.min(minCostPathRec(input, row+1, col+1),
                    Math.min(minCostPathRec(input, row, col+1),
                            minCostPathRec(input, row+1,col)));
    }

    public static int minCostPathRec(int input[][]) {
//        int rUpperBound = input.length - 1;
//        int cUpperBound = input[0].length - 1;

        return minCostPathRec(input, 0, 0);
    }

    private static int minCostPathRecM(int[][] input, int row, int col, int[][] storage) {
        int m = input.length - 1;
        int n = input[0].length - 1;
        if (row > m  || col > n) {
            return Integer.MAX_VALUE;
        } else if (row == m && col == n) {
            storage[m][n] = input[row][col];
            return input[row][col];
        }

        if (storage[row][col] != -1) {
            return storage[row][col];
        }

        int op1 = minCostPathRecM(input, row, col+1, storage);
        int op2 = minCostPathRecM(input, row+1, col+1, storage);
        int op3 = minCostPathRecM(input, row+1, col, storage);

        storage[row][col] = input[row][col] + Math.min(op1, Math.min(op2, op3));

        return storage[row][col];
    }

    public static int minCostPathRecM(int input[][]) {
        int m = input.length;
        int n = input[0].length;
        int[][] storage = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                storage[i][j] = -1;
            }
        }
        return minCostPathRecM(input, 0, 0, storage);
    }

    public static int minCostPathDP(int[][] input) {
        int m = input.length;
        int n = input[0].length;

        int[][] storage = new int[m][n];
        storage[m-1][n-1] = input[m-1][n-1];

        //Last Row
        for (int j = n-2 ; j >= 0 ; j--) {
            storage[m-1][j] = input[m-1][j] + storage[m-1][j+1];
        }

        //Last Column
        for (int i = m-2 ; i >= 0 ; i--) {
            storage[i][n-1] = input[i][n-1] + storage[i+1][n-1];
        }

        //Complete storage array
        for (int i = m-2 ; i >= 0 ; i--) {
            for (int j = n-2 ; j >= 0 ; j--) {
                storage[i][j] = input[i][j] + Math.min(storage[i+1][j+1],
                                    Math.min(storage[i][j+1], storage[i+1][j]));
            }
        }
        return storage[0][0];
    }

    public static int longestCommonSubsequence(String X, String Y) {
        int m = X.length();
        int n = Y.length();

        return lcsUtil(X, m, 0, Y, n, 0);
    }

    private static int lcsUtil(String x, int m, int i, String y, int n, int j) {
        if (i == m || j == n) {
            return 0;
        }else if (x.charAt(i) == y.charAt(j)) {
            return 1 +  lcsUtil(x, m, i + 1, y, n, j + 1);
        } else
            return Math.max(lcsUtil(x, m, i+1, y, n, j), lcsUtil(x, m, i, y, n, j+1));
    }

    public static int lcsM(String s, String t) {
        int[][] storage = new int[s.length()+1][t.length()+1];
        for (int i = 0 ; i < s.length()+1 ; i++) {
            for (int j = 0; j < t.length()+1 ; j++) {
                storage[i][j] = -1;
            }
        }
        return lcsMUtil(s, t, storage);
    }

    private static int lcsMUtil(String s, String t, int[][] storage) {
        int m = s.length();
        int n = t.length();

        if (storage[m][n] != -1) {
            return storage[m][n];
        }
        if (m == 0 || n==0) {
            storage[m][n] = 0;
            return storage[m][n];
        }
        if (s.charAt(0) == t.charAt(0)) {
            storage[m][n] = 1+ lcsMUtil(s.substring(1), t.substring(1), storage);
        } else {
            int op1 = lcsMUtil(s.substring(1), t, storage);
            int op2 = lcsMUtil(s, t.substring(1), storage);
            storage[m][n] = Math.max(op1, op2);
        }

        return storage[m][n];
    }

    public static int lcsDP(String s, String t) {
        int m = s.length()+1;
        int n = t.length()+1;
        int[][] dp = new int[m][n];

        for (int i = 0 ; i < m ; i++) {
            dp[i][n-1] = 0;
        }
        for (int i = 0 ; i < n ; i++) {
            dp[m-1][i] = 0;
        }

        for (int i = m-2 ; i >= 0 ; i--) {
            for (int j = n - 2; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][0];
    }

    public static int getMaxMoney(int arr[], int n){
        if (n == 1) {
            return arr[0];
        }
        if (n == 2) {
            return Math.max(arr[0], arr[1]);
        }

        int[] loot = new int[n];
        loot[0] = arr[0];
        loot[1] = arr[1];

        for(int i = 2 ; i < n ; i++) {
            loot[i] = Math.max(loot[i-1], arr[i] + loot[i-2]);
        }

        return loot[n-1];
    }

    public static int findPossibleWays(int coins[], int sum, int size){
        int[][] arr = new int[size + 1][];
        for(int i = 0; i < size + 1; i++)
            arr[i] = new int[sum + 1];

        for(int i = 0; i < size + 1; i++)
            arr[i][0] = 1;

        for(int j = 0; j < sum + 1; j++)
            arr[0][j] = 0;

        for(int i = 1; i < size + 1; i++)
            for(int j = 1; j < sum + 1; j++)
                if(coins[i - 1] > j)
                    arr[i][j] = arr[i - 1][j];
                else
                    arr[i][j] = arr[i - 1][j] + arr[i][j - coins[i - 1]];

        int answer = arr[size][sum];
        return answer;
    }

    public static int countWaysToMakeChange(int denominations[], int value){
        int n = denominations.length;

        int[][] ways = new int[n+1][value+1];

        for (int i = 0 ; i <= n ; i++) {
            ways[0][i] = 0;
        }
        for (int i = 1 ; i <= n ; i++) {
            ways[i][0] = 1;
        }

        for (int i = 1 ; i <= n ; i++)
            for (int j = 1 ; j <= value ; j++) {
                if (denominations[i-1] > j )
                    ways[i][j] = ways[i-1][j];
                else
                    ways[i][j] = ways[i-1][j] + ways[i][j-denominations[i-1]];
            }

        return ways[n][value];
    }
}

class Runner {

    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        /*int m = s.nextInt();
        int n = s.nextInt();
        int input[][] = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                input[i][j] = s.nextInt();
            }
        }
        System.out.println(DP2.minCostPathRec(input));
        System.out.println(DP2.minCostPathRecM(input));
        System.out.println(DP2.minCostPathDP(input));


        System.out.println(DP2.lcsDP("AAAAA", "ZBCDA"));
        System.out.println(DP2.lcsM("ACBDA", "ABCDA"));
        */

        int[] arr = {1, 2, 3};
        System.out.println(DP2.countWaysToMakeChange(arr, 4));
    }
}

class A {
    protected int i;
    A(int j) {
        this.i = j;
    }
}

class B extends A {
    B(int j) {
        super(j);
    }
}
