package javaDsAlgoCN.CodeGladiators;

import java.io.*;
import java.util.*;
public class CandidateCode {
    public static void main(String args[] ) throws Exception {
        Scanner scn = new Scanner(System.in);
        int numOfIng= scn.nextInt();
        long[] requirement = new long[numOfIng];
        long[] available   = new long[numOfIng];

        for (int i = 0 ; i < numOfIng ; i++) {
            long req = scn.nextLong();
            requirement[i] = req;
        }
        for (int i = 0 ; i < numOfIng ; i++) {
            long ava = scn.nextLong();
            available[i] = ava;
        }

        System.out.println(maxNumOfPPGs(numOfIng, requirement, available));
    }

    public static int maxNumOfPPGs(int numOfIng, long[] requirement, long[] available) {
        int maxGirls = Integer.MAX_VALUE;

        for(int i = 0 ; i < numOfIng ; i++) {
            int tempNumOfGirls = (int)(available[i]/requirement[i]);
            if (tempNumOfGirls < maxGirls) {
                maxGirls = tempNumOfGirls;
            }
        }

        return maxGirls;
    }
}
