import java.util.*;

public class IntegratedStructureAudit {
    public enum DataStructure { LIST, QUEUE, BST, HEAP, HASH_TABLE, GRAPH }

    public record AuditTestCase(String scenario, DataStructure proposedDS, boolean requiresOrder, boolean highFrequencyLookupByKey, boolean requiresPriority, boolean networkRelationships) {}

    public static String audit(AuditTestCase testCase) {
        if (testCase == null) return "INVALID TEST CASE";

        DataStructure proposed = testCase.proposedDS();
        StringBuilder auditLog = new StringBuilder();
        auditLog.append(String.format("情境評估: [%s] -> 選擇結構: %s | ", testCase.scenario(), proposed));

        boolean isCorrect = false;
        String recommendation = "";

        if (testCase.networkRelationships()) {
            isCorrect = (proposed == DataStructure.GRAPH);
            recommendation = "應使用 Graph 表達多對多節點關聯與路徑搜尋。";
        } else if (testCase.requiresPriority()) {
            isCorrect = (proposed == DataStructure.HEAP);
            recommendation = "應使用 Heap (PriorityQueue) 維持動態最高/最低優先級。";
        } else if (testCase.highFrequencyLookupByKey() && testCase.requiresOrder()) {
            isCorrect = (proposed == DataStructure.BST);
            recommendation = "應使用 BST (如 TreeMap) 同時兼顧鍵值查找與有序範圍巡訪。";
        } else if (testCase.highFrequencyLookupByKey() && !testCase.requiresOrder()) {
            isCorrect = (proposed == DataStructure.HASH_TABLE);
            recommendation = "應使用 Hash Table (HashMap) 達成 O(1) 快速精確檢索。";
        } else if (!testCase.highFrequencyLookupByKey() && testCase.requiresOrder()) {
            isCorrect = (proposed == DataStructure.LIST || proposed == DataStructure.QUEUE);
            recommendation = "應使用 List 或 Queue 處理序列化/先進先出順序。";
        }

        if (isCorrect) {
            auditLog.append("診斷結果: [通過] 選擇合理符合業務邏輯。");
        } else {
            auditLog.append("診斷結果: [不合理] 建議修正: ").append(recommendation);
        }

        return auditLog.toString();
    }

    public static void main(String[] args) {
        List<AuditTestCase> cases = Arrays.asList(
            new AuditTestCase("校園公車路網路徑規劃", DataStructure.GRAPH, false, false, false, true),
            new AuditTestCase("急診室患者分診系統", DataStructure.LIST, false, false, true, false),
            new AuditTestCase("學生證號快速查名", DataStructure.HASH_TABLE, false, true, false, false),
            new AuditTestCase("依年齡區間查詢員工名單", DataStructure.BST, true, true, false, false)
        );

        for (AuditTestCase tc : cases) {
            System.out.println(audit(tc));
        }
    }
}