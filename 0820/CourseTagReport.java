import java.util.*;

public class CourseTagReport {
    public static void main(String[] args) {
        String[] rawTags = {"Java", "Backend", "Database", "Java", "Spring", "Backend", "Java"};

        List<String> tagList = new ArrayList<>();
        Set<String> tagSet = new LinkedHashSet<>();
        Map<String, Integer> tagCountMap = new LinkedHashMap<>();

        for (String tag : rawTags) {
            tagList.add(tag);
            tagSet.add(tag);
            tagCountMap.put(tag, tagCountMap.getOrDefault(tag, 0) + 1);
        }

        System.out.println("=== 課程標籤報告 ===");
        System.out.println("1. List (保存原始順序): " + tagList);
        System.out.println("   用途: 保留所有使用者輸入的原始記錄與順序，可重複。\n");

        System.out.println("2. Set (不重複標籤): " + tagSet);
        System.out.println("   用途: 快速獲取所有不重複的標籤種類列表，便於分類篩選。\n");

        System.out.println("3. Map (統計次數): " + tagCountMap);
        System.out.println("   用途: 統計各個標籤被使用的熱門程度與頻率。");
    }
}