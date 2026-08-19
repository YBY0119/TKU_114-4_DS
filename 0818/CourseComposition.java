class Instructor {
    private String id;
    private String name;

    public Instructor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
}

class Course {
    private String courseCode;
    private String title;
    private Instructor instructor; // Composition 引用

    public Course(String courseCode, String title, Instructor instructor) {
        this.courseCode = courseCode;
        this.title = title;
        this.instructor = instructor;
    }

    public String summary() {
        String instructorName = (instructor != null) ? instructor.getName() : "未定";
        return String.format("课程代码: %s | 课程名称: %s | 授课教师: %s", courseCode, title, instructorName);
    }
}

public class CourseComposition {
    public static void main(String[] args) {
        Instructor teacher = new Instructor("T001", "张教授");

        // 共用同一个 instructor
        Course c1 = new Course("CS101", "资料结构", teacher);
        Course c2 = new Course("CS102", "演算法", teacher);

        System.out.println(c1.summary());
        System.out.println(c2.summary());
    }
}