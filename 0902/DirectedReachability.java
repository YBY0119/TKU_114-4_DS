import java.util.*;

public class DirectedReachability {
    public record Query(String from, String to) {}

    public static Map<Query, Boolean> checkReachabilities(Map<String, List<String>> graph, List<Query> queries) {
        Map<Query, Boolean> results = new LinkedHashMap<>();
        if (queries == null) return results;

        for (Query q : queries) {
            if (q == null || q.from() == null || q.to() == null || graph == null) {
                results.put(q, false);
                continue;
            }
            if (!graph.containsKey(q.from()) || !graph.containsKey(q.to())) {
                results.put(q, false);
                continue;
            }
            results.put(q, isReachable(graph, q.from(), q.to()));
        }
        return results;
    }

    private static boolean isReachable(Map<String, List<String>> graph, String src, String dest) {
        if (src.equals(dest)) return true;

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(src);
        visited.add(src);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            List<String> neighbors = graph.get(curr);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (neighbor != null && !visited.contains(neighbor)) {
                        if (neighbor.equals(dest)) return true;
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B"));
        graph.put("B", Arrays.asList("C"));
        graph.put("C", Arrays.asList("D"));
        graph.put("D", Collections.emptyList());
        graph.put("E", Collections.emptyList());

        List<Query> queries = Arrays.asList(
            new Query("A", "D"),
            new Query("D", "A"),
            new Query("A", "E"),
            new Query("A", "A"),
            new Query("A", "Z")
        );

        Map<Query, Boolean> res = checkReachabilities(graph, queries);
        res.forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}