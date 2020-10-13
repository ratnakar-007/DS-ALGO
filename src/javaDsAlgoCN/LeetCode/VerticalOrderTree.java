package javaDsAlgoCN.LeetCode;

import java.util.*;

class Pair{
    int row;
    int val;

    public Pair(int row, int val) {
        this.row = row;
        this.val = val;
    }
}

class Limits{
    int min;
    int max;

    public Limits(int min, int max) {
        this.min = min;
        this.max = max;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
}

class Tree{
    public static TreeNode takeInput(Scanner s) {
        int data = s.nextInt();
        if (data == -1000) {
            return null;
        }
        TreeNode root = new TreeNode(data);
        root.left = takeInput(s);
        root.right = takeInput(s);
        return root;
    }

    public static void printTree(TreeNode root) {
        if (root == null) {
            return;
        }
        String toBePrinted = root.val + ":->";
        if (root.left != null) {
            toBePrinted += "L:" + root.left.val + ",";
        }

        if (root.right != null) {
            toBePrinted += "R:" + root.right.val;
        }
        System.out.println(toBePrinted);
        printTree(root.left);
        printTree(root.right);
    }

    private static void verticalTraversal(TreeNode root, Map<Integer, List<Pair>> verticalTraversalMap, Limits limits, int row, int horizontalD) {
        if (root == null) {
            return;
        }

        //left part
        verticalTraversal(root.left, verticalTraversalMap, limits, row+1, horizontalD-1);
        if (horizontalD < limits.min) {
            limits.min = horizontalD;
        }
        List<Pair> hdList = verticalTraversalMap.get(horizontalD);
        if (hdList == null) {
            hdList = new ArrayList<>();
            hdList.add(new Pair(row, root.val));
        } else {
            hdList.add(new Pair(row, root.val));
        }
        verticalTraversalMap.put(horizontalD, hdList);

        //right part
        verticalTraversal(root.right, verticalTraversalMap, limits, row+1, horizontalD+1);
        if (horizontalD > limits.max) {
            limits.max = horizontalD;
        }
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, List<Pair>> verticalTraversalMap = new HashMap<>();
        List<List<Integer>> answer = new ArrayList<>();
        Limits limits = new Limits(Integer.MAX_VALUE, Integer.MIN_VALUE);
        if (root != null) {
            verticalTraversal(root, verticalTraversalMap, limits, 0, 0);
            int minL = limits.min;
            int maxL = limits.max;
            for (int i=minL;i<=maxL;i++){
                List<Integer> subList = new ArrayList<>();
                List<Pair> pairList = verticalTraversalMap.get(i);
                pairList.sort(new Comparator<Pair>() {
                    @Override
                    public int compare(Pair p2, Pair p1) {
                        if (p2.row == p1.row)
                            return p2.val- p1.val;
                        else
                            return p2.row-p1.row;
                    }
                });
                for (Pair p : pairList) {
                    subList.add(p.val);
                }
                answer.add(subList);
            }
        }
        return answer;
    }
    public static int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        return pathSumUtil(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    private static int pathSumUtil(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        if (targetSum == root.val) {
            ans++;
        }
        ans += pathSumUtil(root.left, targetSum-root.val);
        ans += pathSumUtil(root.right, targetSum-root.val);

        return ans;
    }
}

public class VerticalOrderTree {
    public static void main(String[] args) {
        TreeNode root = Tree.takeInput(new Scanner(System.in));
        //Tree.printTree(root);
        //List<List<Integer>> answer = Tree.verticalTraversal(root);
        System.out.println(Tree.pathSum(root, -1));
    }
}
