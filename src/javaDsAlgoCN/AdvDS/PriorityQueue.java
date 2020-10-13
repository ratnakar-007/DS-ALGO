package javaDsAlgoCN.AdvDS;

import java.util.ArrayList;

public class PriorityQueue {
    private ArrayList<Integer> heap;

    public PriorityQueue() {
        this.heap = new ArrayList<>();
    }

    boolean isEmpty() {
        return (heap.size() == 0);
    }

    int size() {
        return heap.size();
    }

    int getMin() throws PriorityQueueException {
        if (isEmpty()) {
            throw new PriorityQueueException();
        }
        return heap.get(0);
    }

    void insert(int ele) {
        heap.add(ele);
        int childIndex = heap.size()-1;
        int parentIndex= (childIndex-1)/2;
        while (childIndex > 0) {
            if (heap.get(childIndex) < heap.get(parentIndex)) {
                int temp = heap.get(childIndex);
                heap.set(childIndex, heap.get(parentIndex));
                heap.set(parentIndex, temp);
                childIndex = parentIndex;
                parentIndex= (childIndex-1)/2;
            } else {
                return;
            }
        }
    }

    void printHeap() throws PriorityQueueException {
        if (isEmpty()) {
            throw new PriorityQueueException();
        }
        int lastIndex = heap.size()-1;
        int upperLimit= (lastIndex-1)/2;
        for (int i = 0 ; i <= upperLimit ; i++) {
            System.out.println("Parent:" + heap.get(i));
            int lchi = i*2 + 1;
            int rchi = i*2 + 2;
            if (lchi > lastIndex && rchi > lastIndex){
                return;
            } else if (lchi < lastIndex && rchi <= lastIndex){
                System.out.print("Child 1: " + heap.get(lchi) + " Child 2: " + heap.get(rchi));
                System.out.println("");
            } else if (rchi > lastIndex) {
                System.out.print("Child 1: " + heap.get(lchi));
            }
        }
        System.out.println("");
    }

    int removeMin() {
        int lastEle = heap.get(heap.size()-1);
        int removedVal = heap.get(0);
        heap.set(0, lastEle);
        heap.remove(heap.size()-1);
        int rootIndex = 0;
        int minIndex = rootIndex;
        int lchi = rootIndex*2 + 1;
        int rchi = rootIndex*2 + 2;
        while(lchi < heap.size()) {
            if (heap.get(lchi) < heap.get(minIndex))
                minIndex = lchi;
            if (rchi < heap.size() && heap.get(rchi) < heap.get(minIndex))
                minIndex = rchi;
            if (minIndex == rootIndex){
                break;
            } else {
                int temp = heap.get(rootIndex);
                heap.set(rootIndex, heap.get(minIndex));
                heap.set(minIndex, temp);
                rootIndex = minIndex;
            }
            lchi = rootIndex*2 + 1;
            rchi = rootIndex*2 + 2;
        }
        return removedVal;
    }

    public static void heapify(int[] input, int n, int i) {
        int lchi = 2*i + 1;
        int rchi = 2*i + 2;
        int maxIndex = i;
        if (lchi < n && input[lchi] > input[i])
            maxIndex = lchi;
        if (rchi < n && input[rchi] > input[maxIndex])
            maxIndex = rchi;

        if (maxIndex != i) {
            int temp = input[i];
            input[i] = input[maxIndex];
            input[maxIndex] = temp;

            heapify(input, n, maxIndex);
        }
    }

    public static void inplaceHeapSort(int input[]) {
        int n = input.length;
        for (int i = n/2-1 ; i>=0 ; i--) {
            heapify(input, n, i);
        }

        for (int i = n-1 ; i >= 0 ; i--) {
            int temp = input[0];
            input[0] = input[i];
            input[i] = temp;

            heapify(input, i, 0);
        }
    }

    public static int buyTicket(int input[], int k) {
        int myPriority = input[k];

        // To find order of myPriority in input array.If there are other persons having priority same as me.
        int order = 0;
        for (int i = 0 ; i <= k ; i++) {
            if (input[i] == myPriority) {
                order++;
            }
        }

        // Heapify the input array
        for (int i = input.length/2-1 ; i>=0 ; i--) {
            heapify(input, input.length, i);
        }

        int n 		= input.length;
        int timer   = 0;
        while (input[0] != myPriority) {
            input[0] = input[n-1];
            n--;
            heapify(input, n, 0);
            timer++;
        }

        return timer + (order-1);
    }
}
