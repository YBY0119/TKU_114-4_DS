public class BstRangeReport {
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

    public int min() {
        if (root == null) throw new RuntimeException("Tree is empty");
        Node cur = root;
        while (cur.left != null) cur = cur.left;
        return cur.val;
    }

    public int max() {
        if (root == null) throw new RuntimeException("Tree is empty");
        Node cur = root;
        while (cur.right != null) cur = cur.right;
        return cur.val;
    }

    public void printRange(int low, int high) {
        if (low > high) {
            int tmp = low;
            low = high;
            high = tmp;
        }
        System.out.printf("Range [%d, %d]: ", low, high);
        printRangeRec(root, low, high);
        System.out.println();
    }

    private void printRangeRec(Node node, int low, int high) {
        if (node == null) return;
        if (node.val > low) {
            printRangeRec(node.left, low, high);
        }
        if (node.val >= low && node.val <= high) {
            System.out.print(node.val + " ");
        }
        if (node.val < high) {
            printRangeRec(node.right, low, high);
        }
    }

    public static void main(String[] args) {
        BstRangeReport bst = new BstRangeReport();
        int[] vals = {50, 30, 70, 20, 40, 60, 80, 10, 25};
        for (int v : vals) bst.insert(v);

        System.out.println("Min: " + bst.min());
        System.out.println("Max: " + bst.max());

        bst.printRange(25, 65);
        bst.printRange(65, 25); // 測試 low > high
    }
}