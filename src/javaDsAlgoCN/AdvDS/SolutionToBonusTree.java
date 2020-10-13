package javaDsAlgoCN.AdvDS;

import java.util.*;

class NodeInfo {
    int min;
    int max;
    boolean isBst;
    int height;
    int ans;
}

class SolutionToBonus {

    /*	Binary Tree Node class
     *
     * class BinaryTreeNode<T> {
            T data;
            BinaryTreeNode<T> left;
            BinaryTreeNode<T> right;

            public BinaryTreeNode(T data) {
                this.data = data;
            }
        }
    */
    public static NodeInfo largestBSTSubTreeUtil(BinaryTreeNode<Integer> root) {
        if (root == null) {
            NodeInfo output = new NodeInfo();
            output.min 		= Integer.MAX_VALUE;
            output.max 		= Integer.MIN_VALUE;
            output.isBst 	= true;
            output.height	= 0;
            output.ans 		= 0;

            return output;
        }

        NodeInfo leftNodeInfo = largestBSTSubTreeUtil(root.left);
        NodeInfo rightNodeInfo= largestBSTSubTreeUtil(root.right);

        int min      = Math.min(root.data, Math.min(leftNodeInfo.min, rightNodeInfo.min));
        int max      = Math.max(root.data, Math.max(leftNodeInfo.max, rightNodeInfo.max));
        int height   = 1 + Math.max(leftNodeInfo.height, rightNodeInfo.height);

        NodeInfo output = new NodeInfo();

        if (root.data > leftNodeInfo.max &&
                root.data < rightNodeInfo.min &&
                leftNodeInfo.isBst && rightNodeInfo.isBst) {
            output.min      = min;
            output.max      = max;
            output.height   = height;
            output.isBst    = true;
            output.ans      = height;

            return output;
        } else {
            output.height   = Math.max(leftNodeInfo.height, rightNodeInfo.height);
            return output;
        }
    }

    public static int largestBSTSubtree(BinaryTreeNode<Integer> root) {
        // Write your code here
        NodeInfo rootLargestBST = largestBSTSubTreeUtil(root);
        return rootLargestBST.height;
    }
}
public class SolutionToBonusTree {
    static Scanner s = new Scanner(System.in);

    public static BinaryTreeNode<Integer> takeInput(){
        Queue<BinaryTreeNode<Integer>> pendingNodes = new LinkedList<BinaryTreeNode<Integer>>(); // we can skip writing again inside <> after java version 1.7
        Scanner s = new Scanner(System.in);
        int rootData = s.nextInt();
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(rootData);
        pendingNodes.add(root);

        while(!pendingNodes.isEmpty()){
            BinaryTreeNode<Integer> currentNode;
            currentNode = pendingNodes.poll();
            int leftChildData = s.nextInt();
            if(leftChildData != -1){
                BinaryTreeNode<Integer> leftChild = new BinaryTreeNode<Integer>(leftChildData);
                currentNode.left = leftChild;
                pendingNodes.add(leftChild);
            }
            int rightChildData = s.nextInt();
            if(rightChildData != -1){
                BinaryTreeNode<Integer> rightChild = new BinaryTreeNode<Integer>(rightChildData);
                currentNode.right = rightChild;
                pendingNodes.add(rightChild);
            }
        }
        return root;
    }

    public static int maxValueInBST(BinaryTreeNode<Integer> root) {
        int maxValue = root.data;
        while (root.right != null) {
            maxValue = root.right.data;
            root = root.right;
        }
        return maxValue;
    }

    public static int minValueInBST(BinaryTreeNode<Integer> root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    public static void printNodesSumToS(BinaryTreeNode<Integer> root, int s) {
        /*Stack<BinaryTreeNode<Integer>> leftSt = new Stack<>();
        Stack<BinaryTreeNode<Integer>> rightSt= new Stack<>();

        BinaryTreeNode<Integer> currLeft = root;
        BinaryTreeNode<Integer> currRight= root;

        while(currLeft!=null || currRight!=null || leftSt.size() > 0 && rightSt.size() > 0) {
            while (currLeft!=null){
                leftSt.add(currLeft);
                currLeft = currLeft.left;
            }
            while (currRight!=null) {
                rightSt.add(currRight);
                currRight = currRight.right;
            }

            BinaryTreeNode<Integer> leftNode = leftSt.pop();
            BinaryTreeNode<Integer> rightNode= rightSt.pop();

            int leftVal = leftNode.data;
            int rightVal= rightNode.data;

            if (leftVal+rightVal < s) {
                if (leftNode.right != null) {
                    int greaterThanLeft = minValueInBST(leftNode.right);
                    BinaryTreeNode<Integer> justGreaterThanLeftVal = new BinaryTreeNode<>(greaterThanLeft);
                }
            }
        }*/
        ArrayList<BinaryTreeNode<Integer>> LeftList = new ArrayList<>();
        ArrayList<BinaryTreeNode<Integer>> RightList = new ArrayList<>();

        BinaryTreeNode<Integer> curr_left = root;
        BinaryTreeNode<Integer> curr_right = root;

        while (curr_left != null || curr_right != null
                || LeftList.size() > 0 && RightList.size() > 0) {

            while (curr_left != null) {
                LeftList.add(curr_left);
                curr_left = curr_left.left;
            }
            while (curr_right != null) {
                RightList.add(curr_right);
                curr_right = curr_right.right;
            }
            BinaryTreeNode<Integer> LeftNode = LeftList.get(LeftList.size() - 1);
            BinaryTreeNode<Integer> RightNode = RightList.get(RightList.size() - 1);

            int leftVal = LeftNode.data;
            int rightVal = RightNode.data;

            if (leftVal >= rightVal)
                break;
            if (leftVal + rightVal < s) {
                LeftList.remove(LeftList.size() - 1);
                curr_left = LeftNode.right;
            }
            else if (leftVal + rightVal > s) {
                RightList.remove(RightList.size() - 1);
                curr_right = RightNode.left;
            }
            else {
                System.out.println(LeftNode.data + " " + RightNode.data);
                RightList.remove(RightList.size() - 1);
                LeftList.remove(LeftList.size() - 1);
                curr_left = LeftNode.right;
                curr_right = RightNode.left;
            }
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = takeInput();
        System.out.println(SolutionToBonus.largestBSTSubtree(root));
    }

}
