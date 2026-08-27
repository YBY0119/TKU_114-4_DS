public class SkewedBstReport {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { this.val = v; }
    }

    public static Node insert(Node root, int val) {
        if (root == null) return new Node(val);
        if (val < root.val) root.left = insert(root.left, val);
        else if (val > root.val) root.right = insert(root.right, val);
        return root;
    }

    public static int getHeight(Node root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    public static int getSize(Node root) {
        if (root == null) return 0;
        return 1 + getSize(root.left) + getSize(root.right);
    }

    public static int getSearchComparisonCount(Node root, int target) {
        int count = 0;
        Node cur = root;
        while (cur != null) {
            count++;
            if (target == cur.val) return count;
            else if (target < cur.val) cur = cur.left;
            else cur = cur.right;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] sortedData = {1, 2, 3, 4, 5, 6, 7};
        int[] balancedData = {4, 2, 6, 1, 3, 5, 7};

        // 1. 傾斜樹 (Skewed)
        Node skewedTree = null;
        for (int x : sortedData) skewedTree = insert(skewedTree, x);

        // 2. 平衡樹 (Balanced)
        Node balancedTree = null;
        for (int x : balancedData) balancedTree = insert(balancedTree, x);

        int totalSkewedCmp = 0;
        for (int x : sortedData) totalSkewedCmp += getSearchComparisonCount(skewedTree, x);

        int totalBalancedCmp = 0;
        for (int x : sortedData) totalBalancedCmp += getSearchComparisonCount(balancedTree, x);

        System.out.println("=== Skewed Tree (排序資料建立) ===");
        System.out.println("Size: " + getSize(skewedTree));
        System.out.println("Height: " + getHeight(skewedTree));
        System.out.println("查找所有節點總比較次數: " + totalSkewedCmp);

        System.out.println("\n=== Balanced Tree (平衡順序建立) ===");
        System.out.println("Size: " + getSize(balancedTree));
        System.out.println("Height: " + getHeight(balancedTree));
        System.out.println("查找所有節點總比較次數: " + totalBalancedCmp);
    }
}