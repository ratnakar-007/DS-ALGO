package javaDsAlgoCN.AdvDS;

import java.util.*;

class myPair {
    TreeNode<Integer> node;
    int sum;
    public myPair () {
        node= null;
        sum = Integer.MIN_VALUE;
    }
}

public class TreeUse {

    public static TreeNode<Integer> takeInput() {
        int n;
        Scanner s = new Scanner(System.in);
        System.out.println("Enter next node Data..");
        n = s.nextInt();
        TreeNode<Integer> root = new TreeNode<>(n);
        System.out.println("Enter Number of children for " + n);
        int childCount = s.nextInt();
        for (int i = 0; i < childCount; i++) {
            TreeNode<Integer> child = takeInput();
            root.children.add(child);
        }
        return root;
    }
    public static TreeNode<Integer> takeInputLvlWse(){
        int n;
        Scanner s = new Scanner(System.in);
        //System.out.println("Enter next node Data..");
        n = s.nextInt();
        TreeNode<Integer> root = new TreeNode<>(n);
        Queue<TreeNode<Integer>> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            TreeNode<Integer> currNode = q.poll();
            //System.out.println("Enter # of children for- " + currNode.data);
            int numOfCh = s.nextInt();
            for (int i = 0; i < numOfCh; i++) {
            //    System.out.println("Enter #"+ i+" child data.");
                int childData = s.nextInt();
                TreeNode<Integer> child = new TreeNode<>(childData);
                currNode.children.add(child);
                q.add(child);
            }
        }
        return root;
    }
    public static void printLvlWse(TreeNode<Integer> root) {
        Queue<TreeNode<Integer>> q = new LinkedList<>();
        TreeNode<Integer> currNode;
        String s = "";
        q.add(root);
        while(!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                currNode = q.poll();
                s = s + currNode.data + " ";
                for (int j = 0; j < currNode.children.size(); j++) {
                    q.add(currNode.children.get(j));
                }
            }
            System.out.println(s.trim());
            s = "";
        }
    }
    public static void print(TreeNode<Integer> root) {
        System.out.print(root.data + ": ");
        for (int i = 0; i < root.children.size(); i++) {
            System.out.print(root.children.get(i).data + "   ");
        }
        System.out.println("");
        for (int i = 0; i < root.children.size(); i++) {
            print(root.children.get(i));
        }
    }
    public static int largestNode(TreeNode<Integer> root) {
        if (root == null) return Integer.MIN_VALUE;
        int ans =root.data;
        for (int i = 0; i < root.children.size(); i++) {
            int childLargest = largestNode(root.children.get(i));
            if (childLargest>ans){
                ans = childLargest;
            }
        }
        return ans;
    }
    public static int numNodeGreater(TreeNode<Integer> root,int x){
        if (root == null) return 0;
        int count;
        int data = root.data;
        count = data > x ? 1 : 0;
        for (int i = 0; i < root.children.size(); i++) {
            count += numNodeGreater(root.children.get(i), x);
        }
        return count;
    }
    public static int height(TreeNode<Integer> root) {
        if (root == null) return 0;
        int maxHeight = 0;
        for (int i = 0; i < root.children.size(); i++) {
            maxHeight = Math.max(maxHeight, height(root.children.get(i)));
        }
        return maxHeight+1;
    }
    public static int countLeafNodes(TreeNode<Integer> root){
        int count = 0;
        if (root.children.size() == 0) {
            return 1;
        } else {
            for (int i = 0 ; i < root.children.size() ; i++) {
                count += countLeafNodes(root.children.get(i));
            }
            return  count;
        }
    }
    public static boolean checkIfContainsX(TreeNode<Integer> root, int x){
        if (root == null) return false;
        Queue<TreeNode<Integer>> q = new LinkedList<>();
        TreeNode<Integer> currNode;
        q.add(root);
        while(!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                currNode = q.poll();
                if (currNode.data == x)
                    return true;
                for (int j = 0; j < currNode.children.size(); j++) {
                    q.add(currNode.children.get(j));
                }
            }
        }
        return false;
    }
    public static myPair maxSumNodeUtil(TreeNode<Integer> root) {
        if (root == null)
            return null;
        myPair p = new myPair();
        p.node = root;
        int sum = root.data;
        for (int i = 0 ; i < root.children.size() ; i++){
            sum += root.children.get(i).data;
        }
        p.sum = sum;
        myPair c = new myPair();
        for (int j = 0 ; j < root.children.size() ; j++){
            c = maxSumNodeUtil(root.children.get(j));
            if (p.sum < c.sum)
                p = c;
        }
        return p;
    }
    public static TreeNode<Integer> findSecondLargest(TreeNode<Integer> root){
        TreeNode<Integer> first = null;
        TreeNode<Integer> second= null;

        Queue<TreeNode<Integer>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode<Integer> currNode = q.poll();
            if (first == null)
                first = currNode;
            else if (currNode.data > first.data) {
                second= first;
                first = currNode;
            } else if (second == null || currNode.data > second.data) {
                if (currNode.data != first.data)
                    second = currNode;
            }
            for (int i = 0; i < currNode.children.size(); i++) {
                q.add(currNode.children.get(i));
            }
         }
        return second;
    }
    public static TreeNode<Integer> maxSumNode(TreeNode<Integer> root){
        myPair p = maxSumNodeUtil(root);
        if (p == null) return null;
        return p.node;
    }
    public static void replaceWithDepthValue(TreeNode<Integer> root){
        /*int lvl = 0;
        Queue<TreeNode<Integer>> q = new LinkedList<>();
        Queue<TreeNode<Integer>> nq = new LinkedList<>();
        if (root == null) return;
        q.add(root);
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0 ; i < qSize ; i++) {
                TreeNode<Integer> currNode = q.poll();
                currNode.data = lvl;

                for (int j = 0 ; j < currNode.children.size() ; j++) {
                    nq.add(currNode.children.get(j));
                }
            }
            q = nq;
            nq = new LinkedList<>();
            lvl++;
        }*/
        replaceWithDepthValue(root, 0);
    }
    private static void replaceWithDepthValue(TreeNode<Integer> root, int i) {
        root.data = i;
        for (int j = 0 ; j < root.children.size() ; j++) {
            replaceWithDepthValue(root.children.get(j), i + 1);
        }
    }
    public static TreeNode<Integer> removeLeafNodes(TreeNode<Integer> root) {
        if (root == null)
            return null;
        if (root.children.size() == 0)
            return null;

        for (int i = 0 ; i < root.children.size() ; i++) {
            TreeNode<Integer> child = removeLeafNodes(root.children.get(i));
            if (child == null) {
                root.children.remove(i);
                i--;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        /*
        TreeNode<Integer> root = new TreeNode<>(4);
        TreeNode<Integer> node1 = new TreeNode<>(2);
        TreeNode<Integer> node2 = new TreeNode<>(3);
        TreeNode<Integer> node3 = new TreeNode<>(5);
        TreeNode<Integer> node4 = new TreeNode<>(6);

        root.children.add(node1);
        root.children.add(node2);
        root.children.add(node3);

        //node2.children.add(node4);
        System.out.println(root.children.get(0) == node1);*/
        //takeInput();
//        TreeNode<Integer> root = takeInputLvlWse();
//        printLvlWse(root);
//        System.out.println("Largest Node: "+largestNode(root));
//        System.out.println("Number of Node greater than 4: "+numNodeGreater(root, 4));
//        System.out.println("Leaf Nodes: " + countLeafNodes(root));
//        System.out.println("If 9999 is in tree: " + checkIfContainsX(root, 9999));
/*        TreeNode<Integer> msn = maxSumNode(root);
        System.out.println(msn.data);
        TreeNode<Integer> sl = findSecondLargest(root);
        if (sl != null)
            System.out.println(sl.data);
        else
            System.out.println("ALL NODES HAVE SAME DATA.");
        replaceWithDepthValue(root);
        printLvlWse(root);*/
//        printLvlWse(TreeUse.removeLeafNodes(root));

    }
}
