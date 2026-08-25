import java.util.*;

public class TraversalTestReport {

    static class TreeNode {
        String val;
        TreeNode left;
        TreeNode right;
        TreeNode(String val) { this.val = val; }
    }

    // 4種遍歷實作
    public static List<String> preorder(TreeNode root) {
        List<String> res = new ArrayList<>();
        preorderHelper(root, res);
        return res;
    }
    private static void preorderHelper(TreeNode root, List<String> res) {
        if (root == null) return;
        res.add(root.val);
        preorderHelper(root.left, res);
        preorderHelper(root.right, res);
    }

    public static List<String> inorder(TreeNode root) {
        List<String> res = new ArrayList<>();
        inorderHelper(root, res);
        return res;
    }
    private static void inorderHelper(TreeNode root, List<String> res) {
        if (root == null) return;
        inorderHelper(root.left, res);
        res.add(root.val);
        inorderHelper(root.right, res);
    }

    public static List<String> postorder(TreeNode root) {
        List<String> res = new ArrayList<>();
        postorderHelper(root, res);
        return res;
    }
    private static void postorderHelper(TreeNode root, List<String> res) {
        if (root == null) return;
        postorderHelper(root.left, res);
        postorderHelper(root.right, res);
        res.add(root.val);
    }

    public static List<String> levelOrder(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            res.add(cur.val);
            if (cur.left != null) q.offer(cur.left);
            if (cur.right != null) q.offer(cur.right);
        }
        return res;
    }

    // 驗證並印出測試結果
    public static void verifyAndReport(String treeType, TreeNode root,
                                       List<String> expPre, List<String> expIn,
                                       List<String> expPost, List<String> expLevel) {
        List<String> actPre = preorder(root);
        List<String> actIn = inorder(root);
        List<String> actPost = postorder(root);
        List<String> actLevel = levelOrder(root);

        System.out.println("==================================================");
        System.out.println("測試案例: " + treeType);
        System.out.println("--------------------------------------------------");
        printRow("Preorder  ", expPre, actPre);
        printRow("Inorder   ", expIn, actIn);
        printRow("Postorder ", expPost, actPost);
        printRow("LevelOrder", expLevel, actLevel);
        System.out.println();
    }

    private static void printRow(String name, List<String> expected, List<String> actual) {
        boolean match = expected.equals(actual);
        System.out.printf("%s | 預期: %-16s | 實際: %-16s | 結果: %s\n",
                name, expected.toString(), actual.toString(), match ? "PASS" : "FAIL");
    }

    public static void main(String[] args) {
        // 1. Empty Tree
        verifyAndReport("Empty Tree", null,
                List.of(), List.of(), List.of(), List.of());

        // 2. Single-node Tree
        TreeNode single = new TreeNode("A");
        verifyAndReport("Single-node Tree", single,
                List.of("A"), List.of("A"), List.of("A"), List.of("A"));

        // 3. Only-left Tree (A -> B -> C)
        TreeNode onlyLeft = new TreeNode("A");
        onlyLeft.left = new TreeNode("B");
        onlyLeft.left.left = new TreeNode("C");
        verifyAndReport("Only-left Tree", onlyLeft,
                List.of("A", "B", "C"), List.of("C", "B", "A"), List.of("C", "B", "A"), List.of("A", "B", "C"));

        // 4. Only-right Tree (A -> B -> C)
        TreeNode onlyRight = new TreeNode("A");
        onlyRight.right = new TreeNode("B");
        onlyRight.right.right = new TreeNode("C");
        verifyAndReport("Only-right Tree", onlyRight,
                List.of("A", "B", "C"), List.of("A", "B", "C"), List.of("C", "B", "A"), List.of("A", "B", "C"));

        // 5. Complete Tree (A(B(D,E), C(F,null)))
        TreeNode complete = new TreeNode("A");
        complete.left = new TreeNode("B");
        complete.right = new TreeNode("C");
        complete.left.left = new TreeNode("D");
        complete.left.right = new TreeNode("E");
        complete.right.left = new TreeNode("F");
        verifyAndReport("Complete Tree", complete,
                List.of("A", "B", "D", "E", "C", "F"),
                List.of("D", "B", "E", "A", "F", "C"),
                List.of("D", "E", "B", "F", "C", "A"),
                List.of("A", "B", "C", "D", "E", "F"));

        // 6. Irregular Tree
        TreeNode irregular = new TreeNode("A");
        irregular.left = new TreeNode("B");
        irregular.left.right = new TreeNode("C");
        irregular.left.right.left = new TreeNode("D");
        verifyAndReport("Irregular Tree", irregular,
                List.of("A", "B", "C", "D"),
                List.of("B", "D", "C", "A"),
                List.of("D", "C", "B", "A"),
                List.of("A", "B", "C", "D"));
    }
}