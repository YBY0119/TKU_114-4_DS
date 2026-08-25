import java.util.ArrayList;
import java.util.List;

public class BinaryTreeStructureReport {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public static int getSize(TreeNode root) {
        if (root == null) return 0;
        return 1 + getSize(root.left) + getSize(root.right);
    }

    public static int getHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    public static int countLeaves(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return countLeaves(root.left) + countLeaves(root.right);
    }

    public static List<Integer> getLeaves(TreeNode root) {
        List<Integer> leaves = new ArrayList<>();
        findLeaves(root, leaves);
        return leaves;
    }

    private static void findLeaves(TreeNode root, List<Integer> leaves) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
            return;
        }
        findLeaves(root.left, leaves);
        findLeaves(root.right, leaves);
    }

    public static void printReport(String treeName, TreeNode root) {
        System.out.println("=== " + treeName + " 結構報表 ===");
        System.out.println("Root: " + (root != null ? root.val : "null"));
        System.out.println("Size: " + getSize(root));
        System.out.println("Height: " + getHeight(root));
        System.out.println("Leaf Count: " + countLeaves(root));
        System.out.println("Leaves: " + getLeaves(root));
        System.out.println();
    }

    public static void main(String[] args) {
        // 1. 空樹
        printReport("Empty Tree", null);

        // 2. 單節點樹
        TreeNode singleNode = new TreeNode(42);
        printReport("Single-node Tree", singleNode);

        // 3. 至少 7 個節點的樹
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        printReport("7-node Tree", root);
    }
}