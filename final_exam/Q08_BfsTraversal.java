import java.util.*;

public class Q08_BfsTraversal {

    public static List<String> bfs(Map<String, List<String>> graph, String start) {
        List<String> order = new ArrayList<>();
        if (graph == null || start == null || !graph.containsKey(start)) {
            return order;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            order.add(current);

            List<String> neighbors = graph.get(current);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (neighbor != null && !visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return order;
    }

    public static Map<String, Integer> distanceFrom(Map<String, List<String>> graph, String start) {
        Map<String, Integer> distance = new HashMap<>();
        if (graph == null || start == null || !graph.containsKey(start)) {
            return distance;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDist = distance.get(current);

            List<String> neighbors = graph.get(current);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (neighbor != null && !distance.containsKey(neighbor)) {
                        distance.put(neighbor, currentDist + 1);
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("D"));
        graph.put("C", Arrays.asList("D", "E"));
        graph.put("D", Collections.emptyList());
        graph.put("E", Collections.emptyList());

        System.out.println("BFS Order: " + bfs(graph, "A")); // [A, B, C, D, E]
        System.out.println("Distances: " + distanceFrom(graph, "A")); // {A=0, B=1, C=1, D=2, E=2}
    }
}