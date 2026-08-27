

public class BstShapeExperiment {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { this.val = v; }
    }

    public static Node insert(Node node, int val) {
        if (node == null) return new Node(val);
        if (val < node.val) node.left = insert(node.left, val);
        else if (val > node.val) node.right = insert(node.right, val);
        return node;
    }

    public static int getHeight(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
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

    public static void runExperiment(String name, int[] data, int[] allValues) {
        Node root = null;
        for (int x : data) root = insert(root, x);

        int totalCmp = 0;
        for (int x : allValues) totalCmp += getSearchComparisonCount(root, x);

        System.out.printf("%-18s | 高度: %2d | 總比較次數 (15值): %3d | 平均比較次數: %.2f\n",
                name, getHeight(root), totalCmp, (double) totalCmp / allValues.length);
    }

    public static void main(String[] args) {
        int[] sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int[] balanced = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
        int[] random = {7, 2, 15, 4, 11, 1, 9, 14, 3, 6, 13, 8, 12, 5, 10};

        System.out.println("=== 15 個節點的三種不同插入順序實驗 ===");
        runExperiment("1. 排序順序 (Sorted)", sorted, sorted);
        runExperiment("2. 平衡順序 (Balanced)", balanced, sorted);
        runExperiment("3. 隨機順序 (Random)", random, sorted);
    }
}