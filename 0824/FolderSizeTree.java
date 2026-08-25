import java.util.ArrayList;
import java.util.List;

public class FolderSizeTree {

    static class FolderNode {
        String name;
        int ownSize;
        FolderNode left;
        FolderNode right;

        FolderNode(String name, int ownSize) {
            this.name = name;
            this.ownSize = ownSize;
        }
    }

    static class SubtreeInfo {
        int totalSize;
        FolderNode maxNode;
        int maxSize;

        SubtreeInfo(int totalSize, FolderNode maxNode, int maxSize) {
            this.totalSize = totalSize;
            this.maxNode = maxNode;
            this.maxSize = maxSize;
        }
    }

    // 使用 Postorder 累加計算 subtree size
    public static SubtreeInfo computeSubtreeSizes(FolderNode root) {
        if (root == null) {
            return new SubtreeInfo(0, null, 0);
        }

        // 後序遍歷：先算左右子樹
        SubtreeInfo leftInfo = computeSubtreeSizes(root.left);
        SubtreeInfo rightInfo = computeSubtreeSizes(root.right);

        int currentSubtreeSize = root.ownSize + leftInfo.totalSize + rightInfo.totalSize;

        // 比較找出最大的 subtree
        FolderNode maxNode = root;
        int maxSize = currentSubtreeSize;

        if (leftInfo.maxNode != null && leftInfo.maxSize > maxSize) {
            maxSize = leftInfo.maxSize;
            maxNode = leftInfo.maxNode;
        }
        if (rightInfo.maxNode != null && rightInfo.maxSize > maxSize) {
            maxSize = rightInfo.maxSize;
            maxNode = rightInfo.maxNode;
        }

        return new SubtreeInfo(currentSubtreeSize, maxNode, maxSize);
    }

    // 收集所有 Leaf Folder
    public static List<String> getLeafFolders(FolderNode root) {
        List<String> leaves = new ArrayList<>();
        collectLeaves(root, leaves);
        return leaves;
    }

    private static void collectLeaves(FolderNode root, List<String> leaves) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            leaves.add(root.name + " (" + root.ownSize + "MB)");
            return;
        }
        collectLeaves(root.left, leaves);
        collectLeaves(root.right, leaves);
    }

    public static void main(String[] args) {
        // 建構目錄樹範例
        FolderNode root = new FolderNode("Root", 10);
        root.left = new FolderNode("Documents", 20);
        root.right = new FolderNode("Media", 50);

        root.left.left = new FolderNode("Projects", 30);
        root.left.right = new FolderNode("Resume", 5);

        root.right.left = new FolderNode("Photos", 100);
        root.right.right = new FolderNode("Videos", 250);

        SubtreeInfo info = computeSubtreeSizes(root);

        System.out.println("=== 目錄大小統計報告 ===");
        System.out.println("總大小 (Total Root Size): " + info.totalSize + " MB");
        System.out.println("最大 Subtree 節點: " + (info.maxNode != null ? info.maxNode.name : "None") + " (" + info.maxSize + " MB)");
        System.out.println("Leaf Folders: " + getLeafFolders(root));
    }
}