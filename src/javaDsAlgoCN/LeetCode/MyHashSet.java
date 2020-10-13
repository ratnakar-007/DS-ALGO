package javaDsAlgoCN.LeetCode;

import java.util.ArrayList;

class MapNode{
    int key;
    boolean value;

    MapNode next;

    public MapNode(int key, boolean value) {
        this.key = key;
        this.value = value;
    }
}

public class MyHashSet {
    ArrayList<MapNode> buckets;
    int size;
    int numBuckets;

    public MyHashSet() {
        numBuckets = 5;
        buckets = new ArrayList<>();
        for (int i = 0 ; i<20 ; i++) {
            buckets.add(null);
        }
    }

    //Hash function
    private int getBucketIndex(int key) {
        return key%numBuckets;
    }

    public void add(int key, boolean value) {
        int bucketIndex = getBucketIndex(key);
        MapNode head = buckets.get(bucketIndex);
        while (head != null) {
            if (head.key==key) {
                //head.value = value;
                return;
            }
            head = head.next;
        }
        head = buckets.get(bucketIndex);
        MapNode newElement = new MapNode(key, value);
        size++;
        newElement.next = head;
        buckets.set(bucketIndex, newElement);
        double loadFactor = (1.0*size)/numBuckets;
        if (loadFactor > 0.7) {
            rehash();
        }
    }

    private void rehash() {
        ArrayList<MapNode> temp = buckets;
        buckets = new ArrayList<>();
        for (int i = 0; i < 2*numBuckets ; i++) {
            buckets.add(null);
        }
        size = 0;
        numBuckets *= 2;

        for (int i = 0; i < temp.size() ; i++) {
            MapNode head = temp.get(i);
            while (head != null) {
                int key = head.key;
                boolean value = head.value;
                add(key, value);
                head= head.next;
            }
        }
    }

    public void add(int key) {
        add(key, true);
    }

    public void remove(int key) {
        int bucketIndex = getBucketIndex(key);
        MapNode head = buckets.get(bucketIndex);
        MapNode prev = null;
        while (head != null) {
            if (head.key == key) {
                size--;
                if (prev == null) {
                    buckets.set(bucketIndex, head.next);
                } else {
                    prev.next = head.next;
                }
            }
            prev = head;
            head = head.next;
        }
    }

    public boolean contains(int key) {
        int bucketIndex = getBucketIndex(key);
        MapNode head = buckets.get(bucketIndex);
        while (head != null) {
            if (head.key== key) {
                return head.value;
            }
            head = head.next;
        }
        return false;
    }
}
