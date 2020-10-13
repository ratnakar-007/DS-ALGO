package javaDsAlgoCN;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SolutionRansom {
    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] ransom) {
        if (ransom.length > magazine.length) {
            System.out.println("No");
            return;
        }
        Map<String, Integer> helper = new HashMap<>();
        for(String s : magazine){
            if(!helper.containsKey(s)) {
                helper.put(s, 1);
            } else {
                helper.put(s, helper.get(s)+1);
            }
        }
        for(String s : ransom){
            if(helper.containsKey(s)){
                helper.put(s, helper.get(s)-1);
                if (helper.get(s) < 0){
                    System.out.println("No");
                    return;
                }
            }else{
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");
        int m = Integer.parseInt(mn[0]);
        int n = Integer.parseInt(mn[1]);
        String[] magazine = new String[m];
        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);
        scanner.close();
    }
}


