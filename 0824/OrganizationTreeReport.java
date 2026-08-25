import java.util.*;

public class OrganizationTreeReport {

    static class OrgNode {
        String name;
        List<OrgNode> children;

        OrgNode(String name) {
            this.name = name;
            this.children = new ArrayList<>();
        }

        void addChild(OrgNode child) {
            this.children.add(child);
        }
    }

    // 尋找直屬主管/父節點 (找不到回傳 null，根節點回傳 null)
    public static String findParent(OrgNode root, String target) {
        if (root == null || target == null || root.name.equals(target)) return null;
        return findParentHelper(root, target);
    }

    private static String findParentHelper(OrgNode root, String target) {
        for (OrgNode child : root.children) {
            if (child.name.equals(target)) {
                return root.name;
            }
            String p = findParentHelper(child, target);
            if (p != null) return p;
        }
        return null;
    }

    // 尋找深度 (根節點為 0，找不到回傳 -1)
    public static int findDepth(OrgNode root, String target) {
        return findDepthHelper(root, target, 0);
    }

    private static int findDepthHelper(OrgNode root, String target, int currentDepth) {
        if (root == null || target == null) return -1;
        if (root.name.equals(target)) return currentDepth;

        for (OrgNode child : root.children) {
            int d = findDepthHelper(child, target, currentDepth + 1);
            if (d != -1) return d;
        }
        return -1;
    }

    // 從根節點到目標的路徑 (找不到回傳空 List，不拋例外)
    public static List<String> pathFromRoot(OrgNode root, String target) {
        List<String> path = new ArrayList<>();
        if (root == null || target == null) return path;
        findPathHelper(root, target, path);
        return path;
    }

    private static boolean findPathHelper(OrgNode current, String target, List<String> path) {
        path.add(current.name);
        if (current.name.equals(target)) return true;

        for (OrgNode child : current.children) {
            if (findPathHelper(child, target, path)) return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    // 依層級逐層印出
    public static void printByLevel(OrgNode root) {
        if (root == null) {
            System.out.println("(空組織架構)");
            return;
        }

        Queue<OrgNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.print("Level " + level + ": ");
            for (int i = 0; i < size; i++) {
                OrgNode curr = queue.poll();
                System.out.print(curr.name + " ");
                for (OrgNode child : curr.children) {
                    queue.offer(child);
                }
            }
            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {
        // 構建組織架構
        OrgNode ceo = new OrgNode("CEO");
        OrgNode cto = new OrgNode("CTO");
        OrgNode cfo = new OrgNode("CFO");
        OrgNode devLead = new OrgNode("開發主管");
        OrgNode qaLead = new OrgNode("測試主管");
        OrgNode engineer1 = new OrgNode("工程師A");

        ceo.addChild(cto);
        ceo.addChild(cfo);
        cto.addChild(devLead);
        cto.addChild(qaLead);
        devLead.addChild(engineer1);

        System.out.println("=== 逐層組織架構 ===");
        printByLevel(ceo);
        System.out.println();

        // 測試功能與防呆
        String[] targets = {"工程師A", "CTO", "CEO", "不存在的部門"};
        for (String target : targets) {
            System.out.println("--- 查詢單位: " + target + " ---");
            System.out.println("直屬父單位 (findParent): " + findParent(ceo, target));
            System.out.println("單位層級深度 (findDepth):  " + findDepth(ceo, target));
            System.out.println("至頂路徑 (pathFromRoot):   " + pathFromRoot(ceo, target));
            System.out.println();
        }
    }
}