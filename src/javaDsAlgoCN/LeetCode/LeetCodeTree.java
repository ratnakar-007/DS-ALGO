package javaDsAlgoCN.LeetCode;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

class LeetCodeTreeNode {
    int val;
    LeetCodeTreeNode left;
    LeetCodeTreeNode right;
    LeetCodeTreeNode() {}
    LeetCodeTreeNode(int val) { this.val = val; }
    LeetCodeTreeNode(int val, LeetCodeTreeNode left, LeetCodeTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class LeetCodeTree {
    public List<Integer> inOrderTraversal(LeetCodeTreeNode root) {
        List<Integer> sorted =  new ArrayList<>();
        Stack<LeetCodeTreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || root != null) {
            if (root!=null) {
                stack.push(root);
                root = root.left;
            } else {
                root =  stack.pop();
                sorted.add(root.val);
                root = root.right;
            }
        }
        return sorted;
    }
    public List<Integer> getAllElements(LeetCodeTreeNode root1, LeetCodeTreeNode root2) {
        List<Integer> sorted1 =  inOrderTraversal(root1);
        List<Integer> sorted2 =  inOrderTraversal(root2);
        for(int ele : sorted2) {
            sorted1.add(ele);
        }
        Collections.sort(sorted1);
        return sorted1;
    }
}
