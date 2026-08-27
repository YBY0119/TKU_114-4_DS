public class BstDeleteTestSuite {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { this.val = v; }
    }

    private Node root;

    public void insert(int val) { root = insertRec(root, val); }

    private Node insertRec(Node node, int val) {
        if (node == null) return new Node(val);
        if (val < node.val) node.left = insertRec(node.left, val);
        else if (val > node.val) node.right = insertRec(node.right, val);
        return node;
    }

    public void delete(int val) { root = deleteRec(root, val); }

    private Node deleteRec(Node node, int val) {
        if (node == null) return null;
        if (val < node.val) node.left = deleteRec(node.left, val);
        else if (val > node.val) node.right = deleteRec(node.right, val);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node minNode = node.right;
            while (minNode.left != null) minNode = minNode.left;
            node.val = minNode.val;
            node.right = deleteRec(node.right, minNode.val);
        }
        return node;
    }

    public void printInorder() {
        printInorderRec(root);
        System.out.println();
    }

    private void printInorderRec(Node node) {
        if (node != null) {
            printInorderRec(node.left);
            System.out.print(node.val + " ");
            printInorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        BstDeleteTestSuite bst = new BstDeleteTestSuite();

        System.out.println("1. 測試空樹刪除 (Empty Tree):");
        bst.delete(100);
        bst.printInorder();

        System.out.println("2. 測試刪除不存在的節點 (Missing Node):");
        bst.insert(50);
        bst.delete(999);
        bst.printInorder();

        System.out.println("3. 測試只有 Root 節點刪除 (Single Root):");
        bst.delete(50);
        bst.printInorder();

        System.out.println("4. 測試 Root 只有一個 Child 時刪除:");
        bst.insert(50);
        bst.insert(70);
        bst.delete(50);
        bst.printInorder();
        bst.delete(70); // 清空

        System.out.println("5. 測試 Root 具有雙子節點 (Two Child):");
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.delete(50);
        bst.printInorder();

        System.out.println("6. 測試連續刪除至空樹 (Continuous Delete to Empty):");
        bst.delete(30);
        bst.delete(70);
        bst.printInorder();
    }
}