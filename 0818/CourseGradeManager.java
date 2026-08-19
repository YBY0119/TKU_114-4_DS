class CourseGrade {
    private String studentId;
    private String name;
    private double usualScore;
    private double midtermScore;
    private double finalScore;
    private double attendanceScore;

    public CourseGrade(String studentId, String name, double usual, double midterm, double finalScore, double attendance) {
        this.studentId = studentId;
        this.name = name;
        this.usualScore = validateScore(usual);
        this.midtermScore = validateScore(midterm);
        this.finalScore = validateScore(finalScore);
        this.attendanceScore = validateScore(attendance);
    }

    private double validateScore(double score) {
        if (score < 0) return 0;
        if (score > 100) return 100;
        return score;
    }

    public double calculateFinalScore() {
        return usualScore * 0.50 + midtermScore * 0.20 + finalScore * 0.20 + attendanceScore * 0.10;
    }

    public String getLevel() {
        double score = calculateFinalScore();
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        return String.format("学号: %s | 姓名: %-5s | 平时: %5.1f | 期中: %5.1f | 期末: %5.1f | 出席: %5.1f | 总结算: %5.1f | 等第: %s",
                studentId, name, usualScore, midtermScore, finalScore, attendanceScore, calculateFinalScore(), getLevel());
    }
}

public class CourseGradeManager {
    public static void main(String[] args) {
        CourseGrade[] grades = {
            new CourseGrade("S01", "David", 85, 90, 88, 100),
            new CourseGrade("S02", "Eva",   40, 50, 45, 60),  // 不及格
            new CourseGrade("S03", "Frank", 95, 92, 96, 90),
            new CourseGrade("S04", "Grace", 70, 65, 72, 80),
            new CourseGrade("S05", "Hank",  50, 40, 30, 50)   // 不及格
        };

        double totalScoreSum = 0;
        CourseGrade highestStudent = grades[0];

        System.out.println("=== 所有学生成绩摘要 ===");
        for (CourseGrade g : grades) {
            System.out.println(g);
            double score = g.calculateFinalScore();
            totalScoreSum += score;
            if (score > highestStudent.calculateFinalScore()) {
                highestStudent = g;
            }
        }

        System.out.printf("\n全班平均分数: %.2f\n", totalScoreSum / grades.length);
        System.out.printf("最高分学生: %s (%.1f 分)\n", highestStudent.getName(), highestStudent.calculateFinalScore());

        System.out.println("\n=== 不及格名单 (F) ===");
        for (CourseGrade g : grades) {
            if ("F".equals(g.getLevel())) {
                System.out.println(g);
            }
        }
    }
}
