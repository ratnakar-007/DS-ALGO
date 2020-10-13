package javaDsAlgoCN.AdvDS;

import java.lang.reflect.Array;
import java.util.*;

class Pair<T, V> {
    T first;
    V second;
}

class Node<T> {
    T data;
    Node<T> next;
    Node(T data){
        this.data = data;
    }
}


public class BinaryTreeUse {

    public static BinaryTreeNode<Integer> input (Scanner scn) {
        BinaryTreeNode<Integer> root;
        //System.out.println("Enter node data: ");
        int data = scn.nextInt();
        if (data == -1) {
            return null;
        }
        root = new BinaryTreeNode<>(data);
        root.left = input(scn);
        root.right = input(scn);

        return root;
    }
    //Time complexity = O(n)
    public static int height(BinaryTreeNode<Integer> root) {
        if (root == null)
            return 0;
        int lh = height(root.left);
        int rh = height(root.right);

        return 1 + Math.max(lh, rh);
    }

    //Time complexity => WORST CASE: O(n^2), AVERAGE CASE: O(nlogn)
    //*Recurrence relation for AVERAGE case: T(n) = kn + 2T(n/2)
    //*Recurrence relation for WORST case: T(n) = kn + T(n-1)

    //Time complexity = O(n*h), where 'h' is the height of the tree.
    public static int diameter(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        int option1 = height(root.right) + height(root.left);
        int option2 = diameter(root.left);
        int option3 = diameter(root.right);

        return Math.max(option1, Math.max(option2,option3));
    }

    //Improved function to find Diameter
    public static Pair<Integer,Integer> diameterModified(BinaryTreeNode<Integer> root){
        if (root == null) {
            Pair<Integer, Integer> output = new Pair<>();
            output.first = 0;
            output.second = 0;
            return output;
        }
        Pair<Integer, Integer> loutput = diameterModified(root.left);
        Pair<Integer, Integer> routput = diameterModified(root.right);

        int height = 1 + Math.max(loutput.first, routput.first);
        int option1 = loutput.first + routput.first;
        int option2 = loutput.second;
        int option3 = routput.second;
        int diameter= Math.max(option1, Math.max(option2, option3));
        Pair<Integer, Integer> output = new Pair<>();
        output.first = height;
        output.second= diameter;

        return output;
    }

    private static int getRootIndex(int[] in, int ele) {
        int index = -1;
        for (int i = 0 ; i < in.length ; i++ ) {
            if (in[i] == ele) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static BinaryTreeNode<Integer> getTreeFromPostOrderAndInorder(int[] pos, int [] in, int posi, int poli, int isi, int ili){

        if (posi>poli) return null;
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(pos[poli]);
        int ri = getRootIndex(in, pos[poli]);
        root.left = getTreeFromPostOrderAndInorder(pos, in, posi, posi+(ri-isi)-1, isi, ri-1);
        root.right= getTreeFromPostOrderAndInorder(pos, in, posi+(ri-isi), poli-1, ri+1, ili);

        return root;
    }

    private static BinaryTreeNode<Integer> getTreeFromPreorderAndInorder(int[] pre, int[] in, int psi,
                                                                         int pli, int isi, int ili) {
        if (psi > pli) return null;
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(pre[psi]);
        int ri = getRootIndex(in, pre[psi]);
        root.left = getTreeFromPreorderAndInorder(pre, in, psi+1, psi+(ri-isi), isi, ri-1);
        root.right= getTreeFromPreorderAndInorder(pre, in, psi + (ri-isi+1), pli, ri+1, ili);

        return root;
    }
    public static int depth(BinaryTreeNode<Integer> root) {
        if (root == null) return 0;
        int ld = depth(root.left);
        int rd = depth(root.right);

        return 1 + Math.max(ld, rd);
    }

    public static boolean checkBalanced(BinaryTreeNode<Integer> root){
        int ld = 0;
        int rd = 0;
        if (root == null) return true;
        ld = depth(root.left);
        rd = depth(root.right);

        if (Math.abs(ld-rd) <= 1 && checkBalanced(root.left) && checkBalanced(root.right))
            return true;

        return false;
    }

    public  static BinaryTreeNode<Integer> getTreeFromPostOrderAndInorder(int[] pos, int[] in) {
        return getTreeFromPostOrderAndInorder(pos, in, 0, pos.length-1, 0, in.length-1);
    }

    public static BinaryTreeNode<Integer> getTreeFromPreorderAndInorder(int[] pre, int[] in){
        return getTreeFromPreorderAndInorder(pre, in, 0, pre.length-1,
                0, in.length-1);
    }

    public static ArrayList<Node<BinaryTreeNode<Integer>>> LLForEachLevel(BinaryTreeNode<Integer> root) {
        ArrayList<Node<BinaryTreeNode<Integer>>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int qSize = q.size();
            Node<BinaryTreeNode<Integer>> head = null;
            Node<BinaryTreeNode<Integer>> tail = null;
            for(int i = 0 ; i < qSize ; i++) {
                BinaryTreeNode<Integer> currNode = q.poll();
                if (head == null) {
                    head = new Node<>(currNode);
                    tail = head;
                } else {
                    tail.next = new Node<>(currNode);
                    tail = tail.next;
                }
                if (currNode.left != null) q.add(currNode.left);
                if (currNode.right!= null) q.add(currNode.right);
            }
            ans.add(0, head);
        }
        return ans;
    }

    public static ArrayList<Integer> getNodeToRootPath(BinaryTreeNode<Integer> root, int k) {
        if (root == null)
            return null;
        if (root.data == k) {
            ArrayList<Integer> output = new ArrayList<>();
            output.add(root.data);
            return output;
        }
        ArrayList<Integer> leftOutput = getNodeToRootPath(root.left, k);
        if (leftOutput != null) {
            leftOutput.add(root.data);
            return leftOutput;
        }
        ArrayList<Integer> rightOutput = getNodeToRootPath(root.right, k);
        if (rightOutput != null) {
            rightOutput.add(root.data);
            return rightOutput;
        } else
            return null;
    }

    public static List<List<Integer>> printZigZag(BinaryTreeNode<Integer> root) {
        Stack<BinaryTreeNode<Integer>> cl = new Stack<>();
        Stack<BinaryTreeNode<Integer>> nl = new Stack<>();
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        boolean LtoR = true;

        if (root == null) return ans;

        cl.push(root);
        while(!cl.isEmpty()) {
            BinaryTreeNode<Integer> currNode = cl.pop();
            subList.add(currNode.data);
            if (LtoR) {
                if (currNode.left != null)
                    nl.push(currNode.left);
                if (currNode.right!= null)
                    nl.push(currNode.right);
            } else {
                if (currNode.right != null)
                    nl.push(currNode.right);
                if (currNode.left!= null)
                    nl.push(currNode.left);
            }
            if (cl.isEmpty()) {
                LtoR = !LtoR;
                Stack<BinaryTreeNode<Integer>> temp = new Stack<>();
                ans.add(subList);
                subList = new ArrayList<>();
                cl = nl;
                nl = temp;
            }
        }
        return ans;
    }

    public static int sumRootToLeaf(BinaryTreeNode<Integer> root) {
        List<String> rootToLeafPath = new ArrayList<>();
        String outputSoFar = "";
        sumRootToLeafUtil(root, rootToLeafPath, outputSoFar);
        int ans = 0;
        for (String path: rootToLeafPath) {
            int subAns = 0;
            for (int i = 0 ; i<path.length() ; i++) {
                subAns += (path.charAt(i) - '0')*Math.pow(2, path.length()-1-i);
            }
            ans+=subAns;
        }
        return ans;
    }

    private static void sumRootToLeafUtil(BinaryTreeNode<Integer> root, List<String> rootToLeafPath,
                                          String outputSoFar) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            rootToLeafPath.add(outputSoFar + root.data);
            return;
        }

        outputSoFar = outputSoFar + root.data;

        sumRootToLeafUtil(root.left, rootToLeafPath, outputSoFar);
        sumRootToLeafUtil(root.right, rootToLeafPath, outputSoFar);

        return;
    }
    /*
    public int sumRootToLeafUtil(TreeNode root, int val) {
        if(root == null)
            return 0;

        val = val * 2 + root.val;

        //if leaf node
        if(root.left == null && root.right == null) {
            return val;
        }

        return sumRootToLeafUtil(root.left, val)
                + sumRootToLeafUtil(root.right, val);
    }

    public int sumRootToLeaf(TreeNode root) {
        return sumRootToLeafUtil(root, 0);
    }
    */
    public static void main(String[] args) {
        //int[] pre = {1, 2, 3, 4, 15, 5, 6, 7, 8, 10, 9, 12};
        //int[] in  = {4, 15, 3, 2, 5, 1, 6, 10, 8, 7, 9, 12};
        /*int[] pos = {8, 4, 2, 9, 7, 3, 1};
        int[] in  = {8, 4, 2, 1, 3, 7, 9};

        BinaryTreeNode<Integer> root = getTreeFromPostOrderAndInorder(pos,in);
        List<List<Integer>> ans = printZigZag(root);
        System.out.println(ans);*/

        Scanner scn = new Scanner(System.in);
        BinaryTreeNode<Integer> root = input(scn);
        System.out.println(sumRootToLeaf(root));
    }
}
