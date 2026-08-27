public class BstInvariantChecker {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { this.val = v; }
    }

    public static boolean isValidBST(Node root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(Node node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    public static void main(String[] args) {
        // Valid Tree: 20 -> (10, 30)
        Node validTree = new Node(20);
        validTree.left = new Node(10);
        validTree.right = new Node(30);

        // 違規 1: 左子樹深層節點大於 Root (20 -> 左: 10 -> 右: 25)
        Node invalid1 = new Node(20);
        invalid1.left = new Node(10);
        invalid1.left.right = new Node(25);

        // 違規 2: 右子樹深層節點小於 Root (20 -> 右: 30 -> 左: 15)
        Node invalid2 = new Node(20);
        invalid2.right = new Node(30);
        invalid2.right.left = new Node(15);

        // 違規 3: 深層節點違反局部上界 (50 -> 左: 30 -> 右: 40 -> 右: 55 > 50)
        Node invalid3 = new Node(50);
        invalid3.left = new Node(30);
        invalid3.left.right = new Node(40);
        invalid3.left.right.right = new Node(55);

        System.out.println("Valid Tree 驗證結果: " + isValidBST(validTree));
        System.out.println("違規樹 1 驗證結果: " + isValidBST(invalid1));
        System.out.println("違規樹 2 驗證結果: " + isValidBST(invalid2));
        System.out.println("違規樹 3 驗證結果: " + isValidBST(invalid3));
    }
}