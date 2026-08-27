public class BstDeleteCases {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { this.val = v; }
    }

    private Node root;

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private Node insertRec(Node node, int val) {
        if (node == null) return new Node(val);
        if (val < node.val) node.left = insertRec(node.left, val);
        else if (val > node.val) node.right = insertRec(node.right, val);
        return node;
    }

    public void delete(int val) {
        root = deleteRec(root, val);
    }

    private Node deleteRec(Node node, int val) {
        if (node == null) return null;
        if (val < node.val) node.left = deleteRec(node.left, val);
        else if (val > node.val) node.right = deleteRec(node.right, val);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            
            Node successor = getMin(node.right);
            node.val = successor.val;
            node.right = deleteRec(node.right, successor.val);
        }
        return node;
    }

    private Node getMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public int size() {
        return sizeRec(root);
    }

    private int sizeRec(Node node) {
        if (node == null) return 0;
        return 1 + sizeRec(node.left) + sizeRec(node.right);
    }

    public void printInorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.val + " ");
            inorderRec(node.right);
        }
    }

    public boolean isValidBST() {
        return isValidRec(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidRec(Node node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isValidRec(node.left, min, node.val) && isValidRec(node.right, node.val, max);
    }

    private void printState(String label) {
        System.out.print(label + " -> Inorder: ");
        printInorder();
        System.out.println("  Size: " + size() + ", Valid BST: " + isValidBST());
    }

    public static void main(String[] args) {
        BstDeleteCases bst = new BstDeleteCases();
        // 建樹: 50, 30(單子節點40), 70(雙子節點60, 80), 40(leaf), 60(leaf), 80(leaf)
        int[] vals = {50, 30, 70, 40, 60, 80};
        for (int v : vals) bst.insert(v);

        bst.printState("初始樹");

        // 1. 刪除 Leaf: 60
        bst.delete(60);
        bst.printState("刪除葉子節點 (60)");

        // 2. 刪除 Single-child node: 30
        bst.delete(30);
        bst.printState("刪除單子節點 (30)");

        // 3. 刪除 Two-child node: 50 (Root)
        bst.delete(50);
        bst.printState("刪除雙子節點 (50)");
    }
}