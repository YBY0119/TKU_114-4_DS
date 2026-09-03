import java.util.*;

public class Q09_DfsPathSearch {

    public static List<String> dfs(Map<String, List<String>> graph, String start) {
        List<String> result = new ArrayList<>();
        if (graph == null || start == null || !graph.containsKey(start)) {
            return result;
        }
        Set<String> visited = new HashSet<>();
        dfsRecursive(graph, start, visited, result);
        return result;
    }

    private static void dfsRecursive(Map<String, List<String>> graph, String current, Set<String> visited, List<String> result) {
        visited.add(current);
        result.add(current);

        List<String> neighbors = graph.get(current);
        if (neighbors != null) {
            for (String neighbor : neighbors) {
                if (neighbor != null && !visited.contains(neighbor)) {
                    dfsRecursive(graph, neighbor, visited, result);
                }
            }
        }
    }

    public static boolean reachable(Map<String, List<String>> graph, String start, String target) {
        if (graph == null || start == null || target == null) {
            return false;
        }
        if (!graph.containsKey(start) || !graph.containsKey(target)) {
            return false;
        }
        if (start.equals(target)) {
            return true;
        }

        Set<String> visited = new HashSet<>();
        return reachRecursive(graph, start, target, visited);
    }

    private static boolean reachRecursive(Map<String, List<String>> graph, String current, String target, Set<String> visited) {
        if (current.equals(target)) {
            return true;
        }
        visited.add(current);

        List<String> neighbors = graph.get(current);
        if (neighbors != null) {
            for (String neighbor : neighbors) {
                if (neighbor != null && !visited.contains(neighbor)) {
                    if (reachRecursive(graph, neighbor, target, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("D"));
        graph.put("C", Collections.emptyList());
        graph.put("D", Arrays.asList("A")); // 含 cycle

        System.out.println("DFS from A: " + dfs(graph, "A")); // [A, B, D, C]
        System.out.println("Reachable A to D: " + reachable(graph, "A", "D")); // true
        System.out.println("Reachable C to D: " + reachable(graph, "C", "D")); // false
    }
}