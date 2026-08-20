import java.util.*;
import java.util.stream.Collectors;

class CourseStudent {
    private final String studentId;
    private int score;
    private final String tag;

    public CourseStudent(String studentId, int score, String tag) {
        this.studentId = studentId;
        this.score = score;
        this.tag = tag;
    }

    public String getStudentId() { return studentId; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public String getTag() { return tag; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseStudent that = (CourseStudent) o;
        return Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public String toString() {
        return "{" + studentId + ", 分數: " + score + ", Tag: '" + tag + "'}";
    }
}

public class CourseCollectionManager {
    private final List<CourseStudent> studentList = new ArrayList<>();
    private final Set<CourseStudent> studentSet = new HashSet<>();
    private final Map<String, CourseStudent> studentMap = new HashMap<>();

    public boolean addStudent(CourseStudent s) {
        if (studentMap.containsKey(s.getStudentId())) {
            return false;
        }
        studentList.add(s);
        studentSet.add(s);
        studentMap.put(s.getStudentId(), s);
        return true;
    }

    // 1. updatesScore(studentId, score)
    public boolean updateScore(String studentId, int score) {
        CourseStudent s = studentMap.get(studentId);
        if (s != null) {
            s.setScore(score);
            return true;
        }
        return false;
    }

    // 2. findByTag(tag)
    public List<CourseStudent> findByTag(String tag) {
        List<CourseStudent> result = new ArrayList<>();
        for (CourseStudent s : studentList) {
            if (Objects.equals(s.getTag(), tag)) {
                result.add(s);
            }
        }
        return result;
    }

    // 3. scoreDistribution() 統計 A、B、C、D、F
    public Map<String, Integer> scoreDistribution() {
        Map<String, Integer> dist = new LinkedHashMap<>();
        dist.put("A", 0); // 90-100
        dist.put("B", 0); // 80-89
        dist.put("C", 0); // 70-79
        dist.put("D", 0); // 60-69
        dist.put("F", 0); // <60

        for (CourseStudent s : studentList) {
            int sc = s.getScore();
            if (sc >= 90) dist.put("A", dist.get("A") + 1);
            else if (sc >= 80) dist.put("B", dist.get("B") + 1);
            else if (sc >= 70) dist.put("C", dist.get("C") + 1);
            else if (sc >= 60) dist.put("D", dist.get("D") + 1);
            else dist.put("F", dist.get("F") + 1);
        }
        return dist;
    }

    // 4. top(count)
    public List<CourseStudent> top(int count) {
        return studentList.stream()
                .sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
                .limit(count)
                .collect(Collectors.toList());
    }

    // 5. removeBelow(minimum) 後，List、Set 與 Map 保持一致
    public void removeBelow(int minimum) {
        studentList.removeIf(s -> s.getScore() < minimum);
        studentSet.removeIf(s -> s.getScore() < minimum);
        studentMap.entrySet().removeIf(entry -> entry.getValue().getScore() < minimum);
    }

    public void printStatus() {
        System.out.println("List: " + studentList);
        System.out.println("Set size: " + studentSet.size() + ", Map size: " + studentMap.size());
    }

    public static void main(String[] args) {
        CourseCollectionManager manager = new CourseCollectionManager();

        // 至少 6 筆測試資料（包含重複學號、同分、空白/空值 tag）
        CourseStudent[] testData = {
            new CourseStudent("S101", 95, "Honor"),
            new CourseStudent("S102", 85, "Regular"),
            new CourseStudent("S103", 85, "Regular"), // 同分
            new CourseStudent("S104", 55, ""),        // 空白 tag
            new CourseStudent("S105", 72, null),      // null tag
            new CourseStudent("S106", 58, "Probation"),
            new CourseStudent("S101", 99, "Honor")    // 重複學號
        };

        for (CourseStudent s : testData) {
            boolean added = manager.addStudent(s);
            System.out.println("新增 " + s.getStudentId() + ": " + added);
        }

        System.out.println("\n=== 1. 更新分數 (S104 更新為 65) ===");
        manager.updateScore("S104", 65);
        manager.printStatus();

        System.out.println("\n=== 2. 依 Tag 查詢 (空白 tag) ===");
        System.out.println(manager.findByTag(""));

        System.out.println("\n=== 3. 等級分布統計 ===");
        System.out.println(manager.scoreDistribution());

        System.out.println("\n=== 4. 前 3 名學生 (若 count > 總人數則全部回傳) ===");
        System.out.println(manager.top(3));

        System.out.println("\n=== 5. 移除低於 60 分的資料 ===");
        manager.removeBelow(60);
        manager.printStatus();
    }
}