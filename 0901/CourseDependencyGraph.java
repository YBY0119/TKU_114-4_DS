import java.util.*;

public class CourseDependencyGraph {
    private Map<String, Set<String>> outgoing = new HashMap<>(); // course -> next courses
    private Map<String, Set<String>> incoming = new HashMap<>(); // course -> prerequisites

    public void addCourse(String course) {
        outgoing.putIfAbsent(course, new HashSet<>());
        incoming.putIfAbsent(course, new HashSet<>());
    }

    public void addPrerequisite(String pre, String next) {
        addCourse(pre);
        addCourse(next);
        outgoing.get(pre).add(next);
        incoming.get(next).add(pre);
    }

    public Set<String> getPrerequisites(String course) {
        return incoming.getOrDefault(course, Collections.emptySet());
    }

    public Set<String> getNextCourses(String course) {
        return outgoing.getOrDefault(course, Collections.emptySet());
    }

    public int getInDegree(String course) {
        return incoming.containsKey(course) ? incoming.get(course).size() : 0;
    }

    public int getOutDegree(String course) {
        return outgoing.containsKey(course) ? outgoing.get(course).size() : 0;
    }

    public static void main(String[] args) {
        CourseDependencyGraph graph = new CourseDependencyGraph();
        graph.addPrerequisite("CS101", "CS102");
        graph.addPrerequisite("CS102", "CS201");
        graph.addPrerequisite("MATH101", "CS201");

        System.out.println("CS201 的先修課: " + graph.getPrerequisites("CS201"));
        System.out.println("CS101 的後續課: " + graph.getNextCourses("CS101"));
        System.out.println("CS201 In-degree: " + graph.getInDegree("CS201") + ", Out-degree: " + graph.getOutDegree("CS201"));
    }
}