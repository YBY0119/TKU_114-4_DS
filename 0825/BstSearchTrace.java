public class BstSearchTrace {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { this.val = v; }
    }

    private Node root;

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private Node insertRec(Node root, int val) {
        if (root == null) return new Node(val);
        if (val < root.val) root.left = insertRec(root.left, val);
        else if (val > root.val) root.right = insertRec(root.right, val);
        return root;
    }

    public void searchWithTrace(int target) {
        System.out.println("=== 搜尋目標: " + target + " ===");
        Node cur = root;
        int step = 0;
        while (cur != null) {
            step++;
            System.out.printf("步驟 %d: 當前節點 = %d", step, cur.val);
            if (target == cur.val) {
                System.out.println(" -> 找到目標！(總比較次數: " + step + ")");
                return;
            } else if (target < cur.val) {
                System.out.println(" -> 目標較小，往 [左] 子樹移動");
                cur = cur.left;
            } else {
                System.out.println(" -> 目標較大，往 [右] 子樹移動");
                cur = cur.right;
            }
        }
        System.out.println("節點為空 -> 查無此值 (總比較次數: " + step + ")");
    }

    public static void main(String[] args) {
        BstSearchTrace bst = new BstSearchTrace();
        // 建立 BST 結構: 50 為 root, 30/70 為 internal, 20/40/60/80 為 leaf
        int[] keys = {50, 30, 70, 20, 40, 60, 80};
        for (int k : keys) bst.insert(k);

        // 1. 測試 Root
        bst.searchWithTrace(50);
        // 2. 測試 Internal Node
        bst.searchWithTrace(30);
        // 3. 測試 Leaf Node
        bst.searchWithTrace(80);
        // 4. 測試 Missing Value
        bst.searchWithTrace(35);
    }
}