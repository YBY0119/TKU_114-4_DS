import java.util.*;

public class Q05_StudentHashIndex {
    private final Map<String, Set<String>> studentToCourses = new HashMap<>();
    private final Map<String, Set<String>> courseToStudents = new HashMap<>();
    private int totalEnrollments = 0;

    private String normalize(String str) {
        if (str == null) return null;
        String trimmed = str.trim();
        return trimmed.isEmpty() ? null : trimmed.toUpperCase();
    }

    public boolean enroll(String studentId, String courseId) {
        String sId = normalize(studentId);
        String cId = normalize(courseId);

        if (sId == null || cId == null) {
            return false;
        }

        Set<String> courses = studentToCourses.computeIfAbsent(sId, k -> new HashSet<>());
        if (courses.contains(cId)) {
            return false;
        }

        courses.add(cId);
        courseToStudents.computeIfAbsent(cId, k -> new HashSet<>()).add(sId);
        totalEnrollments++;
        return true;
    }

    public boolean drop(String studentId, String courseId) {
        String sId = normalize(studentId);
        String cId = normalize(courseId);

        if (sId == null || cId == null) {
            return false;
        }

        Set<String> courses = studentToCourses.get(sId);
        if (courses == null || !courses.contains(cId)) {
            return false;
        }

        courses.remove(cId);
        if (courses.isEmpty()) {
            studentToCourses.remove(sId);
        }

        Set<String> students = courseToStudents.get(cId);
        students.remove(sId);
        if (students.isEmpty()) {
            courseToStudents.remove(cId);
        }

        totalEnrollments--;
        return true;
    }

    public Set<String> coursesOf(String studentId) {
        String sId = normalize(studentId);
        if (sId == null || !studentToCourses.containsKey(sId)) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet<>(studentToCourses.get(sId)));
    }

    public Set<String> studentsIn(String courseId) {
        String cId = normalize(courseId);
        if (cId == null || !courseToStudents.containsKey(cId)) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet<>(courseToStudents.get(cId)));
    }

    public int enrollmentCount() {
        return totalEnrollments;
    }

    public static void main(String[] args) {
        Q05_StudentHashIndex index = new Q05_StudentHashIndex();
        index.enroll("  s101 ", "cs101");
        index.enroll("S101", "  MATH101  ");

        System.out.println("Courses of S101: " + index.coursesOf("s101")); // [CS101, MATH101]
        System.out.println("Total Enrollments: " + index.enrollmentCount()); // 2

        index.drop("s101", "cs101");
        System.out.println("After Drop - Total: " + index.enrollmentCount()); // 1
    }
}