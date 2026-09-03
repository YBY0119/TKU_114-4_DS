import java.util.*;

public class DataStructureDecisionReport {
    public record Decision(int id, String scenario, String recommendedDS, String reason, String primaryBigO) {}

    public static List<Decision> generateDecisionReport() {
        List<Decision> decisions = new ArrayList<>();

        decisions.add(new Decision(1, "高頻隨機存取指定索引元素", "ArrayList", "連續記憶體位址支援常數時間尋址", "O(1) Access"));
        decisions.add(new Decision(2, "頻繁在前端/中間進行大量插入與刪除", "LinkedList", "僅需修改指針連結，不需搬移連續記憶體", "O(1) Insert/Delete (已獲節點)"));
        decisions.add(new Decision(3, "任務排隊與先進先出 (FIFO) 緩衝區", "Queue (ArrayDeque / LinkedList)", "嚴格維持先進先出結構，尾端入隊前端出隊", "O(1) Enqueue/Dequeue"));
        decisions.add(new Decision(4, "函式呼叫堆疊 / 括號匹配 / 撤銷歷史 (LIFO)", "Stack (ArrayDeque)", "後進先出特性，頂部常數時間操作", "O(1) Push/Pop"));
        decisions.add(new Decision(5, "隨時動態取得最高/最低優先級元素", "Heap (PriorityQueue)", "利用完全二元樹維持堆積屬性，動態調整效率高", "O(1) Peek, O(log N) Push/Poll"));
        decisions.add(new Decision(6, "大量 Key-Value 唯一鍵快速查表與更新", "HashMap", "雜湊演算法計算 bucket 索引，均攤常數級搜尋", "O(1) Put/Get (Average)"));
        decisions.add(new Decision(7, "防止資料重複並快速檢查是否存在", "HashSet", "內部以雜湊表實作，提供均攤常數時間的唯一性驗證", "O(1) Contains/Add (Average)"));
        decisions.add(new Decision(8, "需同時支援排序巡訪與範圍查詢 (Range Query)", "Binary Search Tree (TreeMap / TreeSet)", "二元搜尋樹有序存儲，支援中序遍歷由小到大檢索", "O(log N) Search/Insert (Balanced)"));
        decisions.add(new Decision(9, "無權重網路的最短路徑與層級廣度擴展", "Graph + BFS (Queue)", "利用佇列由近到遠擴展，首次到達即為最少邊路徑", "O(V + E)"));
        decisions.add(new Decision(10, "網路連通性分析、拓撲排序與迷宮路徑回溯", "Graph + DFS (Recursion / Stack)", "優先深入探尋路徑，遇死路回溯以確認可達性", "O(V + E)"));
        decisions.add(new Decision(11, "密集圖 (Dense Graph) 快速查詢兩頂點是否有連邊", "Adjacency Matrix", "使用二維陣列直接透過座標索引檢視邊存在性", "O(1) Edge Query, O(V^2) Space"));
        decisions.add(new Decision(12, "稀疏圖 (Sparse Graph) 節省記憶體並快速列舉鄰居", "Adjacency List", "僅儲存實際存在的邊，節省不必要的矩陣空間", "O(V + E) Space, O(deg(V)) Neighbors"));

        return decisions;
    }

    public static void main(String[] args) {
        List<Decision> report = generateDecisionReport();
        System.out.printf("%-4s | %-28s | %-28s | %-24s | %s%n", "ID", "場景", "推薦結構", "主要 Big-O", "選用理由");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
        for (Decision d : report) {
            System.out.printf("%-4d | %-30s | %-30s | %-26s | %s%n", d.id(), d.scenario(), d.recommendedDS(), d.primaryBigO(), d.reason());
        }
    }
}