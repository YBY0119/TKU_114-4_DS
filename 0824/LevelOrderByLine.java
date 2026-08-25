import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderByLine {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("Level " + level + " (count=" + levelSize + "): ");

            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = queue.poll();
                System.out.print(curr.val + " ");

                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {
        System.out.println("--- 測試 Empty Tree ---");
        printLevelOrder(null);
        System.out.println();

        System.out.println("--- 測試完整樹 ---");
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(40);
        root.right.left = new TreeNode(50);
        root.right.right = new TreeNode(60);

        printLevelOrder(root);
    }
}