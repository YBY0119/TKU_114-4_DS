import java.util.*;

public class EnrollmentCleanup {
    public static void main(String[] args) {
        List<String> enrollments = new ArrayList<>(Arrays.asList(
            "Alice", "Bob", "", null, "Charlie", "Alice", "   ", "David", "Bob", null
        ));

        System.out.println("清理前名單 (" + enrollments.size() + " 筆): " + enrollments);

        // 1. 使用 Iterator 安全移除不合法資料 (null 與 空白)
        Iterator<String> it = enrollments.iterator();
        while (it.hasNext()) {
            String name = it.next();
            if (name == null || name.trim().isEmpty()) {
                it.remove();
            }
        }
        System.out.println("清理後名單 (" + enrollments.size() + " 筆): " + enrollments);

        // 2. 使用 Set 找出重複姓名
        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new HashSet<>();

        for (String name : enrollments) {
            if (!seen.add(name)) {
                duplicates.add(name);
            }
        }

        System.out.println("重複名單報告: " + duplicates);
    }
}