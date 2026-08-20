import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Enrollment {
    private final String studentId;
    private final String courseCode;

    public Enrollment(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    public String getStudentId() { return studentId; }
    public String getCourseCode() { return courseCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(studentId, that.studentId) &&
               Objects.equals(courseCode, that.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseCode);
    }

    @Override
    public String toString() {
        return "[Student: " + studentId + ", Course: " + courseCode + "]";
    }
}

public class EnrollmentSetSystem {
    public static void main(String[] args) {
        Set<Enrollment> enrollments = new HashSet<>();

        // 1. 同一人加入不同課程 -> 應成功 (true)
        System.out.println("S001 加 CS101: " + enrollments.add(new Enrollment("S001", "CS101")));
        System.out.println("S001 加 CS102: " + enrollments.add(new Enrollment("S001", "CS102")));

        // 2. 同一人重複加入同一課程 -> 應失敗 (false)
        System.out.println("S001 重複加 CS101: " + enrollments.add(new Enrollment("S001", "CS101")));

        // 3. 以新建但身份相同的 object 測試 contains 與 remove
        Enrollment lookup = new Enrollment("S001", "CS101");
        System.out.println("查詢 S001-CS101 是否存在: " + enrollments.contains(lookup));

        System.out.println("取消/移除 S001-CS101: " + enrollments.remove(lookup));
        System.out.println("移除後再查詢 S001-CS101: " + enrollments.contains(lookup));
    }
}