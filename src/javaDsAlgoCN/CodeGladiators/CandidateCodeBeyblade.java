package javaDsAlgoCN.CodeGladiators;

import java.util.*;

public class CandidateCodeBeyblade {
    public static void main(String args[] ) throws Exception {
        Scanner scn = new Scanner(System.in);
        int T = scn.nextInt();

        String a = "abcd";

        System.out.println(a.substring(0));
        System.out.println(a.substring(3));

        while(T != 0) {
            int members= scn.nextInt();
            long[] g_revolution = new long[members];
            long[] opponent   = new long[members];

            for (int i = 0 ; i < members ; i++) {
                long g_rev = scn.nextLong();
                g_revolution[i] = g_rev;
            }
            for (int i = 0 ; i < members ; i++) {
                long opp = scn.nextLong();
                opponent[i] = opp;
            }

            System.out.println(maxFightsGRevWin(g_revolution, opponent));
            T--;
        }
    }

    public static int maxFightsGRevWin(long[] g_revolution, long[] opponent) {
        int win = 0;
        int i = 0;
        int j = 0;

        Arrays.sort(g_revolution);
        Arrays.sort(opponent);

        while (i < g_revolution.length && j < opponent.length) {
            if (g_revolution[i] > opponent[j]) {
                win++;
                i++;
                j++;
            } else if (g_revolution[i] < opponent[j]) {
                i++;
            } else {
                i++;
            }
        }

        return win;
    }
}