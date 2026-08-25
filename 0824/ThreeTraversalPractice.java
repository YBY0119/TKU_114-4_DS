public class ThreeTraversalPractice {

    static class TreeNode {
        char val;
        TreeNode left;
        TreeNode right;
        TreeNode(char val) { this.val = val; }
    }

    public static void preorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public static void postorder(TreeNode root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val + " ");
    }

    public static void main(String[] args) {
        // 構建題目指定結構: M(F(B, null), T(R, Z))
        TreeNode root = new TreeNode('M');
        root.left = new TreeNode('F');
        root.left.left = new TreeNode('B');
        root.left.right = null;
        root.right = new TreeNode('T');
        root.right.left = new TreeNode('R');
        root.right.right = new TreeNode('Z');

        System.out.println("--- 測試指定樹 M(F(B,null),T(R,Z)) ---");
        System.out.print("Preorder:  ");
        preorder(root);
        System.out.println();

        System.out.print("Inorder:   ");
        inorder(root);
        System.out.println();

        System.out.print("Postorder: ");
        postorder(root);
        System.out.println("\n");

        // 測試 null
        System.out.println("--- 測試 Empty Tree (null) ---");
        System.out.print("Preorder: ");
        preorder(null);
        System.out.println("(無輸出)");
    }
}