import java.util.*;

public class EnrollmentConflictSet {
    static class EnrollmentKey {
        String studentId;
        String courseId;

        EnrollmentKey(String studentId, String courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof EnrollmentKey)) return false;
            EnrollmentKey that = (EnrollmentKey) o;
            return Objects.equals(studentId, that.studentId) &&
                   Objects.equals(courseId, that.courseId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseId);
        }

        @Override
        public String toString() {
            return studentId + "-" + courseId;
        }
    }

    public static void processEnrollments(List<EnrollmentKey> records) {
        Set<EnrollmentKey> seen = new HashSet<>();
        List<EnrollmentKey> duplicates = new ArrayList<>();
        Map<String, Set<String>> studentCourses = new HashMap<>();
        Map<String, Integer> courseStudentCounts = new HashMap<>();

        for (EnrollmentKey record : records) {
            if (!seen.add(record)) {
                duplicates.add(record);
            } else {
                studentCourses.computeIfAbsent(record.studentId, k -> new HashSet<>()).add(record.courseId);
                courseStudentCounts.put(record.courseId, courseStudentCounts.getOrDefault(record.courseId, 0) + 1);
            }
        }

        System.out.println("重複選課紀錄: " + duplicates);
        System.out.println("每人選課集合: " + studentCourses);
        System.out.println("每門課修課人數: " + courseStudentCounts);
    }

    public static void main(String[] args) {
        List<EnrollmentKey> rawLogs = Arrays.asList(
            new EnrollmentKey("S01", "CS101"),
            new EnrollmentKey("S01", "MATH101"),
            new EnrollmentKey("S02", "CS101"),
            new EnrollmentKey("S01", "CS101") // 重複
        );
        processEnrollments(rawLogs);
    }
}