import java.util.*;

public class Q10_UnweightedShortestPath {

    public static List<String> shortestPath(Map<String, List<String>> graph, String start, String target) {
        List<String> path = new ArrayList<>();
        if (graph == null || start == null || target == null) {
            return path;
        }
        if (!graph.containsKey(start) || !graph.containsKey(target)) {
            return path;
        }
        if (start.equals(target)) {
            path.add(start);
            return path;
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> predecessor = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        boolean found = false;
        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(target)) {
                found = true;
                break;
            }

            List<String> neighbors = graph.get(current);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (neighbor != null && !visited.contains(neighbor)) {
                        visited.add(neighbor);
                        predecessor.put(neighbor, current);
                        queue.offer(neighbor);
                    }
                }
            }
        }

        if (!found) {
            return path;
        }

        // 回溯重構路徑
        String curr = target;
        while (curr != null) {
            path.add(curr);
            curr = predecessor.get(curr);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("D"));
        graph.put("C", Arrays.asList("D"));
        graph.put("D", Collections.emptyList());

        System.out.println("Shortest path A -> D: " + shortestPath(graph, "A", "D")); // [A, B, D] (依順序先探索 B)
    }
}