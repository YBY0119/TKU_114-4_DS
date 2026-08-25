import java.util.*;

public class TraversalResultCollector {

    static class TreeNode {
        String val;
        TreeNode left;
        TreeNode right;
        TreeNode(String val) { this.val = val; }
    }

    // Preorder Traversal
    public static List<String> preorder(TreeNode root) {
        List<String> res = new ArrayList<>();
        preorderHelper(root, res);
        return res;
    }

    private static void preorderHelper(TreeNode root, List<String> res) {
        if (root == null) return;
        res.add(root.val);
        preorderHelper(root.left, res);
        preorderHelper(root.right, res);
    }

    // Inorder Traversal
    public static List<String> inorder(TreeNode root) {
        List<String> res = new ArrayList<>();
        inorderHelper(root, res);
        return res;
    }

    private static void inorderHelper(TreeNode root, List<String> res) {
        if (root == null) return;
        inorderHelper(root.left, res);
        res.add(root.val);
        inorderHelper(root.right, res);
    }

    // Postorder Traversal
    public static List<String> postorder(TreeNode root) {
        List<String> res = new ArrayList<>();
        postorderHelper(root, res);
        return res;
    }

    private static void postorderHelper(TreeNode root, List<String> res) {
        if (root == null) return;
        postorderHelper(root.left, res);
        postorderHelper(root.right, res);
        res.add(root.val);
    }

    // Level-order Traversal
    public static List<String> levelOrder(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            res.add(curr.val);
            if (curr.left != null) queue.offer(curr.left);
            if (curr.right != null) queue.offer(curr.right);
        }
        return res;
    }

    public static void testTree(String label, TreeNode root) {
        System.out.println("=== " + label + " ===");
        System.out.println("Preorder:   " + preorder(root));
        System.out.println("Inorder:    " + inorder(root));
        System.out.println("Postorder:  " + postorder(root));
        System.out.println("Level-order:" + levelOrder(root));
        System.out.println();
    }

    public static void main(String[] args) {
        // 1. Empty Tree
        testTree("Empty Tree", null);

        // 2. Single-node Tree
        TreeNode single = new TreeNode("A");
        testTree("Single-node Tree", single);

        // 3. Left-skewed Tree
        TreeNode leftSkewed = new TreeNode("A");
        leftSkewed.left = new TreeNode("B");
        leftSkewed.left.left = new TreeNode("C");
        testTree("Left-skewed Tree", leftSkewed);

        // 4. Complete Tree
        TreeNode complete = new TreeNode("1");
        complete.left = new TreeNode("2");
        complete.right = new TreeNode("3");
        complete.left.left = new TreeNode("4");
        complete.left.right = new TreeNode("5");
        complete.right.left = new TreeNode("6");
        testTree("Complete Tree", complete);
    }
}