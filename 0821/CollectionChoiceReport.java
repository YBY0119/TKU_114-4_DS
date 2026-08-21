import java.util.*;

public class CollectionChoiceReport {

    public static void main(String[] args) {
        System.out.println("=== 集合選擇報告與實作展示 ===\n");

        // 1. 保留搜尋紀錄且允許重複
        System.out.println("1. 需求：保留搜尋紀錄且允許重複");
        System.out.println("選擇 Interface: List / 實作: ArrayList (或 LinkedList)");
        List<String> searchHistory = new ArrayList<>();
        searchHistory.add("Java Queue");
        searchHistory.add("Spring Boot");
        searchHistory.add("Java Queue");
        System.out.println("操作結果: " + searchHistory);
        System.out.println("----------------------------------------");

        // 2. 保存不重複會員編號
        System.out.println("2. 需求：保存不重複會員編號");
        System.out.println("選擇 Interface: Set / 實作: HashSet");
        Set<String> memberIds = new HashSet<>();
        memberIds.add("M001");
        memberIds.add("M002");
        memberIds.add("M001"); // 重複不加入
        System.out.println("操作結果: " + memberIds);
        System.out.println("----------------------------------------");

        // 3. 以學號查詢成績
        System.out.println("3. 需求：以學號查詢成績");
        System.out.println("選擇 Interface: Map / 實作: HashMap");
        Map<String, Integer> studentGrades = new HashMap<>();
        studentGrades.put("S101", 88);
        studentGrades.put("S102", 95);
        System.out.println("操作結果 (查詢 S102): " + studentGrades.get("S102"));
        System.out.println("----------------------------------------");

        // 4. 依到達順序處理列印工作
        System.out.println("4. 需求：依到達順序處理列印工作");
        System.out.println("選擇 Interface: Queue (Deque) / 實作: ArrayDeque");
        Queue<String> printQueue = new ArrayDeque<>();
        printQueue.offer("Doc1.pdf");
        printQueue.offer("Doc2.pdf");
        System.out.println("操作結果 (列印第一個任務): " + printQueue.poll());
        System.out.println("----------------------------------------");

        // 5. 復原最近操作
        System.out.println("5. 需求：復原最近操作");
        System.out.println("選擇 Interface: Deque / 實作: ArrayDeque (作為 Stack)");
        Deque<String> undoStack = new ArrayDeque<>();
        undoStack.push("Action1");
        undoStack.push("Action2");
        System.out.println("操作結果 (復原最近動作): " + undoStack.pop());
        System.out.println("----------------------------------------");
    }
}