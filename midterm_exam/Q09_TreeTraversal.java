import java.util.*;

public class Q09_TreeTraversal {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        preorderRec(root, result);
        return result;
    }

    private static void preorderRec(Node node, List<Integer> result) {
        if (node == null) return;
        result.add(node.value);
        preorderRec(node.left, result);
        preorderRec(node.right, result);
    }

    public static List<Integer> inorder(Node root) {
        List<Integer> result = new ArrayList<>();
        inorderRec(root, result);
        return result;
    }

    private static void inorderRec(Node node, List<Integer> result) {
        if (node == null) return;
        inorderRec(node.left, result);
        result.add(node.value);
        inorderRec(node.right, result);
    }

    public static List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        postorderRec(root, result);
        return result;
    }

    private static void postorderRec(Node node, List<Integer> result) {
        if (node == null) return;
        postorderRec(node.left, result);
        postorderRec(node.right, result);
        result.add(node.value);
    }

    public static List<Integer> levelOrder(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            result.add(cur.value);
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Node root = new Node(8);
        root.left = new Node(4);
        root.right = new Node(12);
        root.left.left = new Node(2);
        root.left.right = new Node(6);
        root.right.right = new Node(14);

        System.out.println("preorder  = " + Q09_TreeTraversal.preorder(root));   // [8, 4, 2, 6, 12, 14]
        System.out.println("inorder   = " + Q09_TreeTraversal.inorder(root));    // [2, 4, 6, 8, 12, 14]
        System.out.println("postorder = " + Q09_TreeTraversal.postorder(root));  // [2, 6, 4, 14, 12, 8]
        System.out.println("level     = " + Q09_TreeTraversal.levelOrder(root)); // [8, 4, 12, 2, 6, 14]
    }
}