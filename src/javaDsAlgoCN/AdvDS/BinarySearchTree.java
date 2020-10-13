package javaDsAlgoCN.AdvDS;

import java.util.Scanner;

public class BinarySearchTree {
    BinaryTreeNode<Integer> root;

    private boolean searchUtil(BinaryTreeNode<Integer> root, int key) {
        if (root == null)
            return false;
        if (key == root.data) {
            return true;
        } else if (key < root.data) {
            return searchUtil(root.left, key);
        } else {
            return searchUtil(root.right, key);
        }
    }

    public boolean search(int key) {
        return searchUtil(root, key);
    }

    private BinaryTreeNode<Integer> insertUtil(BinaryTreeNode<Integer> root, int key) {
        if (root == null) {
            root = new BinaryTreeNode<>(key);
            return root;
        }
        if (key < root.data) {
            root.left = insertUtil(root.left, key);
        } else if (key > root.data) {
            root.right = insertUtil(root.right, key);
        }

        return root;
    }

    public void insertData(int key) {
        root = insertUtil(root, key);
    }

    public int maxValueInBST(BinaryTreeNode<Integer> root) {
        int maxValue = root.data;
        while (root.right != null) {
            maxValue = root.right.data;
            root = root.right;
        }
        return maxValue;
    }

    public int minValueInBST(BinaryTreeNode<Integer> root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    private BinaryTreeNode<Integer> deleteUtil(BinaryTreeNode<Integer> root, int key) {
        if (root == null)
            return root;
        if (key < root.data) {
            root.left = deleteUtil(root.left, key);
        } else if (key > root.data) {
            root.right = deleteUtil(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.data = minValueInBST(root.right);
            root.right = deleteUtil(root.right, root.data);
        }
        return root;
    }

    public void deleteData(int key) {
        root = deleteUtil(root, key);
    }

    private void printTreeRecursive(BinaryTreeNode<Integer> root) {
        if (root == null)
            return;
        String print = "" + root.data + ":";
        if (root.left != null) {
            print = print + "L:" + root.left.data + ",";
        }
        if (root.right != null) {
            print = print + "R:" + root.right.data;
        }
        System.out.println(print);

        printTreeRecursive(root.left);
        printTreeRecursive(root.right);
    }

    public void printTree() {
        printTreeRecursive(root);
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner s = new Scanner(System.in);
        int choice, input;
        while (true) {
            choice = s.nextInt();
            switch (choice) {
                case 1:
                    input = s.nextInt();
                    bst.insertData(input);
                    break;
                case 2:
                    input = s.nextInt();
                    bst.deleteData(input);
                    break;
                case 3:
                    input = s.nextInt();
                    System.out.println(bst.search(input));
                    break;
                default:
                    bst.printTree();
                    return;
            }
        }
    }
}