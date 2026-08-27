import java.util.*;

public class Q11_BstDeletion {

    private static class Node {
        int value;
        Node left, right;
        Node(int value) {
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public boolean add(int value) {
        if (contains(value)) {
            return false;
        }
        root = insertRec(root, value);
        size++;
        return true;
    }

    private Node insertRec(Node node, int value) {
        if (node == null) return new Node(value);
        if (value < node.value) node.left = insertRec(node.left, value);
        else if (value > node.value) node.right = insertRec(node.right, value);
        return node;
    }

    public boolean remove(int value) {
        if (!contains(value)) {
            return false;
        }
        root = removeRec(root, value);
        size--;
        return true;
    }

    private Node removeRec(Node node, int value) {
        if (node == null) return null;
        if (value < node.value) {
            node.left = removeRec(node.left, value);
        } else if (value > node.value) {
            node.right = removeRec(node.right, value);
        } else {
            // Case 1 & 2: Leaf 或只有一個子節點
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Case 3: 雙子節點，使用右子樹最小值作為 inorder successor
            Node minNode = getMin(node.right);
            node.value = minNode.value;
            node.right = removeRec(node.right, minNode.value);
        }
        return node;
    }

    private Node getMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public boolean contains(int value) {
        Node cur = root;
        while (cur != null) {
            if (value == cur.value) return true;
            else if (value < cur.value) cur = cur.left;
            else cur = cur.right;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public List<Integer> inorder() {
        List<Integer> result = new ArrayList<>();
        inorderRec(root, result);
        return result;
    }

    private void inorderRec(Node node, List<Integer> result) {
        if (node != null) {
            inorderRec(node.left, result);
            result.add(node.value);
            inorderRec(node.right, result);
        }
    }

    public boolean isValid() {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(Node node, long low, long high) {
        if (node == null) return true;
        if (node.value <= low || node.value >= high) return false;
        return validate(node.left, low, node.value) && validate(node.right, node.value, high);
    }

    public static void main(String[] args) {
        Q11_BstDeletion tree = new Q11_BstDeletion();
        for (int value : new int[]{50, 30, 70, 20, 40, 60, 80}) {
            tree.add(value);
        }
        System.out.println(tree.remove(20));    // true (leaf)
        System.out.println(tree.remove(30));    // true (one child)
        System.out.println(tree.remove(50));    // true (two children - root)
        System.out.println(tree.remove(999));   // false (not found)
        System.out.println(tree.inorder());     // [40, 60, 70, 80]
        System.out.println(tree.size());        // 4
        System.out.println(tree.isValid());     // true
    }
}