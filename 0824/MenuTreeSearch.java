import java.util.ArrayList;
import java.util.List;

public class MenuTreeSearch {

    static class MenuItem {
        String name;
        List<MenuItem> children;

        MenuItem(String name) {
            this.name = name;
            this.children = new ArrayList<>();
        }

        void addChild(MenuItem child) {
            this.children.add(child);
        }
    }

    // 檢查是否包含特定名稱
    public static boolean contains(MenuItem root, String target) {
        if (root == null) return false;
        if (root.name.equals(target)) return true;
        for (MenuItem child : root.children) {
            if (contains(child, target)) return true;
        }
        return false;
    }

    // 尋找目標節點深度（根節點 depth = 0，找不到回傳 -1）
    public static int findDepth(MenuItem root, String target) {
        return findDepthHelper(root, target, 0);
    }

    private static int findDepthHelper(MenuItem root, String target, int currentDepth) {
        if (root == null) return -1;
        if (root.name.equals(target)) return currentDepth;

        for (MenuItem child : root.children) {
            int depth = findDepthHelper(child, target, currentDepth + 1);
            if (depth != -1) return depth;
        }
        return -1;
    }

    // 計算葉節點個數
    public static int countLeaves(MenuItem root) {
        if (root == null) return 0;
        if (root.children.isEmpty()) return 1;

        int leaves = 0;
        for (MenuItem child : root.children) {
            leaves += countLeaves(child);
        }
        return leaves;
    }

    // 階層式顯示選單 (display)
    public static void display(MenuItem root, int indent) {
        if (root == null) return;
        for (int i = 0; i < indent; i++) {
            System.out.print("  ");
        }
        System.out.println("- " + root.name);
        for (MenuItem child : root.children) {
            display(child, indent + 1);
        }
    }

    public static void main(String[] args) {
        // 建構範例選單樹
        MenuItem root = new MenuItem("主選單");
        MenuItem m1 = new MenuItem("檔案");
        m1.addChild(new MenuItem("開新檔案"));
        m1.addChild(new MenuItem("開啟舊檔"));

        MenuItem m2 = new MenuItem("編輯");
        m2.addChild(new MenuItem("復原"));
        m2.addChild(new MenuItem("重做"));

        MenuItem m3 = new MenuItem("預購"); // 包含測試關鍵字

        root.addChild(m1);
        root.addChild(m2);
        root.addChild(m3);

        System.out.println("=== 選單結構 ===");
        display(root, 0);
        System.out.println();

        System.out.println("總葉節點數: " + countLeaves(root));
        System.out.println("contains '預購': " + contains(root, "預購"));
        System.out.println("findDepth '預購': " + findDepth(root, "預購"));
        System.out.println("contains '設定': " + contains(root, "設定"));
        System.out.println("findDepth '設定': " + findDepth(root, "設定"));
    }
}