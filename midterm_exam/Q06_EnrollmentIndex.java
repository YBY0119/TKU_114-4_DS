import java.util.*;

public class Q06_EnrollmentIndex {
    // 內部使用 Map<String, Set<String>>
    private final Map<String, Set<String>> courseMap;

    public Q06_EnrollmentIndex() {
        this.courseMap = new HashMap<>();
    }

    private boolean isInvalid(String str) {
        return str == null || str.trim().isEmpty();
    }

    public boolean enroll(String courseCode, String studentId) {
        if (isInvalid(courseCode) || isInvalid(studentId)) {
            return false;
        }
        Set<String> students = courseMap.computeIfAbsent(courseCode, k -> new HashSet<>());
        if (students.contains(studentId)) {
            return false; // 重複選課
        }
        return students.add(studentId);
    }

    public boolean drop(String courseCode, String studentId) {
        if (isInvalid(courseCode) || isInvalid(studentId)) {
            return false;
        }
        if (!courseMap.containsKey(courseCode)) {
            return false;
        }

        Set<String> students = courseMap.get(courseCode);
        boolean removed = students.remove(studentId);
        if (removed) {
            if (students.isEmpty()) {
                courseMap.remove(courseCode); // 若無人選課則移除該課程
            }
            return true;
        }
        return false;
    }

    public int courseSize(String courseCode) {
        if (isInvalid(courseCode) || !courseMap.containsKey(courseCode)) {
            return 0;
        }
        return courseMap.get(courseCode).size();
    }

    public List<String> studentsOf(String courseCode) {
        if (isInvalid(courseCode) || !courseMap.containsKey(courseCode)) {
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<>(courseMap.get(courseCode));
        Collections.sort(list); // 依字典順序排序
        return Collections.unmodifiableList(list);
    }

    public List<String> coursesOf(String studentId) {
        if (isInvalid(studentId)) {
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : courseMap.entrySet()) {
            if (entry.getValue().contains(studentId)) {
                list.add(entry.getKey());
            }
        }
        Collections.sort(list); // 依字典順序排序
        return Collections.unmodifiableList(list);
    }

    public Map<String, Integer> summary() {
        // 使用 TreeMap 自動依課程名稱字典順序排列
        Map<String, Integer> sortedSummary = new TreeMap<>();
        for (Map.Entry<String, Set<String>> entry : courseMap.entrySet()) {
            sortedSummary.put(entry.getKey(), entry.getValue().size());
        }
        return Collections.unmodifiableMap(sortedSummary);
    }

    public static void main(String[] args) {
        Q06_EnrollmentIndex index = new Q06_EnrollmentIndex();
        index.enroll("DS", "S02");
        index.enroll("DS", "S01");
        index.enroll("JAVA", "S01");
        System.out.println(index.studentsOf("DS")); // [S01, S02]
        System.out.println(index.coursesOf("S01")); // [DS, JAVA]
        System.out.println(index.summary());        // {DS=2, JAVA=1}
    }
}