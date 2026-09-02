import java.util.*;

public class CourseGradeMap {
    private Map<String, List<Integer>> grades = new HashMap<>();

    public void addGrade(String courseId, int score) {
        grades.computeIfAbsent(courseId, k -> new ArrayList<>()).add(score);
    }

    public double getAverage(String courseId) {
        List<Integer> list = grades.get(courseId);
        if (list == null || list.isEmpty()) return 0.0;
        int sum = 0;
        for (int score : list) sum += score;
        return (double) sum / list.size();
    }

    public int getMax(String courseId) {
        List<Integer> list = grades.get(courseId);
        if (list == null || list.isEmpty()) return -1;
        return Collections.max(list);
    }

    public void printSortedReport() {
        List<String> sortedCourses = new ArrayList<>(grades.keySet());
        Collections.sort(sortedCourses);
        for (String course : sortedCourses) {
            System.out.printf("課程: %s | 平均: %.2f | 最高: %d | 成績列表: %s%n",
                    course, getAverage(course), getMax(course), grades.get(course));
        }
    }

    public static void main(String[] args) {
        CourseGradeMap report = new CourseGradeMap();
        report.addGrade("CS101", 85);
        report.addGrade("CS101", 92);
        report.addGrade("MATH201", 78);
        report.addGrade("CS101", 95);
        report.addGrade("MATH201", 88);
        report.printSortedReport();
    }
}