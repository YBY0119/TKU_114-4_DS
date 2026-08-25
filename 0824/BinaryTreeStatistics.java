public class BinaryTreeStatistics {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    // 節點總數
    public static int size(TreeNode root) {
        if (root == null) return 0;
        return 1 + size(root.left) + size(root.right);
    }

    // 節點值總和
    public static int sum(TreeNode root) {
        if (root == null) return 0;
        return root.val + sum(root.left) + sum(root.right);
    }

    // 最大值：明確處理 empty tree（拋出異常或回傳 null 物件，不可預設為 0）
    public static Integer maximum(TreeNode root) {
        if (root == null) {
            return null; // 明確區分空樹無最大值
        }
        int max = root.val;
        if (root.left != null) {
            Integer leftMax = maximum(root.left);
            if (leftMax != null) max = Math.max(max, leftMax);
        }
        if (root.right != null) {
            Integer rightMax = maximum(root.right);
            if (rightMax != null) max = Math.max(max, rightMax);
        }
        return max;
    }

    // 葉節點個數
    public static int leafCount(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return leafCount(root.left) + leafCount(root.right);
    }

    // 樹的高度
    public static int height(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    // 是否包含特定值
    public static boolean contains(TreeNode root, int target) {
        if (root == null) return false;
        if (root.val == target) return true;
        return contains(root.left, target) || contains(root.right, target);
    }

    public static void printStats(String title, TreeNode root) {
        System.out.println("=== " + title + " ===");
        System.out.println("Size: " + size(root));
        System.out.println("Sum: " + sum(root));
        System.out.println("Maximum: " + maximum(root));
        System.out.println("Leaf Count: " + leafCount(root));
        System.out.println("Height: " + height(root));
        System.out.println("Contains 20: " + contains(root, 20));
        System.out.println();
    }

    public static void main(String[] args) {
        // 1. 空樹測試
        printStats("Empty Tree", null);

        // 2. 包含負數的二元樹測試
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(-20);
        root.right = new TreeNode(-5);
        root.left.left = new TreeNode(-30);
        printStats("Negative Value Tree", root);

        // 3. 一般樹測試
        TreeNode normalRoot = new TreeNode(10);
        normalRoot.left = new TreeNode(20);
        normalRoot.right = new TreeNode(30);
        normalRoot.left.right = new TreeNode(40);
        printStats("Normal Tree", normalRoot);
    }
}