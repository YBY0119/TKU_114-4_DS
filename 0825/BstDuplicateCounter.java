public class BstDuplicateCounter {
    static class Node {
        int key;
        int count;
        Node left, right;
        Node(int k) {
            this.key = k;
            this.count = 1;
        }
    }

    private Node root;

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        if (node == null) return new Node(key);
        if (key == node.key) {
            node.count++;
        } else if (key < node.key) {
            node.left = insertRec(node.left, key);
        } else {
            node.right = insertRec(node.right, key);
        }
        return node;
    }

    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.key + "(" + node.count + ") ");
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        BstDuplicateCounter bst = new BstDuplicateCounter();
        int[] data = {20, 10, 20, 30, 10, 20, 5, 20, 30};
        for (int x : data) bst.insert(x);

        System.out.print("Inorder traversal: ");
        bst.inorder(); // 輸出格式範例: 5(1) 10(2) 20(4) 30(2)
    }
}