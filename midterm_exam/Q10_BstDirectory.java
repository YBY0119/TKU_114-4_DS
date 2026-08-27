import java.util.*;

public class Q10_BstDirectory {

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
        if (node == null) {
            return new Node(value);
        }
        if (value < node.value) {
            node.left = insertRec(node.left, value);
        } else if (value > node.value) {
            node.right = insertRec(node.right, value);
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

    public List<Integer> searchPath(int target) {
        List<Integer> path = new ArrayList<>();
        Node cur = root;
        while (cur != null) {
            path.add(cur.value);
            if (target == cur.value) {
                break;
            } else if (target < cur.value) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return path;
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
        Q10_BstDirectory tree = new Q10_BstDirectory();
        for (int value : new int[]{50, 30, 70, 20, 40, 60, 80}) {
            tree.add(value);
        }
        System.out.println(tree.add(40));             // false
        System.out.println(tree.searchPath(60));      // [50, 70, 60]
        System.out.println(tree.searchPath(65));      // [50, 70, 60]
        System.out.println(tree.inorder());           // [20, 30, 40, 50, 60, 70, 80]
        System.out.println(tree.isValid());           // true
    }
}