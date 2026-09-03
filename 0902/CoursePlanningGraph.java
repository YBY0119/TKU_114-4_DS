import java.util.*;

public class CoursePlanningGraph {
    // prerequisites: course -> list of courses that depend on this course
    private final Map<String, Set<String>> graph = new HashMap<>();

    public boolean addCourse(String course) {
        if (course == null || graph.containsKey(course)) return false;
        graph.put(course, new HashSet<>());
        return true;
    }

    public boolean addPrerequisite(String baseCourse, String dependentCourse) {
        if (baseCourse == null || dependentCourse == null || baseCourse.equals(dependentCourse)) return false;
        if (!graph.containsKey(baseCourse) || !graph.containsKey(dependentCourse)) return false;
        return graph.get(baseCourse).add(dependentCourse);
    }

    public boolean canReach(String startCourse, String targetCourse) {
        if (startCourse == null || targetCourse == null || !graph.containsKey(startCourse) || !graph.containsKey(targetCourse)) {
            return false;
        }
        if (startCourse.equals(targetCourse)) return true;
        Set<String> visited = new HashSet<>();
        return dfsCheck(startCourse, targetCourse, visited);
    }

    private boolean dfsCheck(String curr, String target, Set<String> visited) {
        if (curr.equals(target)) return true;
        visited.add(curr);
        for (String next : graph.getOrDefault(curr, Collections.emptySet())) {
            if (!visited.contains(next)) {
                if (dfsCheck(next, target, visited)) return true;
            }
        }
        return false;
    }

    public List<String> getAllImpactedCourses(String baseCourse) {
        List<String> impacted = new ArrayList<>();
        if (baseCourse == null || !graph.containsKey(baseCourse)) {
            return impacted;
        }
        Set<String> visited = new HashSet<>();
        dfsCollect(baseCourse, visited, impacted);
        impacted.remove(baseCourse); // 排除自身
        return impacted;
    }

    private void dfsCollect(String curr, Set<String> visited, List<String> list) {
        visited.add(curr);
        list.add(curr);
        for (String next : graph.getOrDefault(curr, Collections.emptySet())) {
            if (!visited.contains(next)) {
                dfsCollect(next, visited, list);
            }
        }
    }

    public static void main(String[] args) {
        CoursePlanningGraph planner = new CoursePlanningGraph();
        planner.addCourse("CS101");
        planner.addCourse("CS102");
        planner.addCourse("CS201");
        planner.addCourse("CS301");

        planner.addPrerequisite("CS101", "CS102");
        planner.addPrerequisite("CS102", "CS201");
        planner.addPrerequisite("CS201", "CS301");

        System.out.println("Can reach CS101 -> CS301: " + planner.canReach("CS101", "CS301"));
        System.out.println("All impacted by CS101: " + planner.getAllImpactedCourses("CS101"));
        System.out.println("Missing query: " + planner.canReach("CS101", "MATH101"));
    }
}