package javaDsAlgoCN.AdvDS;

import java.util.Scanner;

public class PriorityQueueUse {
    public static void main(String[] args) throws PriorityQueueException {
        /*Scanner scn = new Scanner(System.in);
        PriorityQueue pq = new PriorityQueue();
        int ele = scn.nextInt();
        while (ele != -1) {
            pq.insert(ele);
            ele = scn.nextInt();
        }*/
        int[] arr = {87240, 79662, 67310, 15939, 68624, 68934, 58457, 85196, 85264, 30838};
        System.out.println(PriorityQueue.buyTicket(arr, 5));
    }
}
