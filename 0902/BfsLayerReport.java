import java.util.*;

public class BfsLayerReport {

    public static Map<String, Integer> reportMinEdgeDistances(Map<String, List<String>> graph, String start) {
        Map<String, Integer> distances = new LinkedHashMap<>();
        if (graph == null || start == null || !graph.containsKey(start)) {
            return distances;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDist = distances.get(current);

            List<String> neighbors = graph.get(current);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (neighbor != null && !distances.containsKey(neighbor)) {
                        distances.put(neighbor, currentDist + 1);
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("A", "D", "E"));
        graph.put("C", Arrays.asList("A", "F"));
        graph.put("D", Arrays.asList("B"));
        graph.put("E", Arrays.asList("B", "F"));
        graph.put("F", Arrays.asList("C", "E"));

        System.out.println("Distances from A: " + reportMinEdgeDistances(graph, "A"));
        System.out.println("Missing vertex: " + reportMinEdgeDistances(graph, "Z"));
        System.out.println("Null graph: " + reportMinEdgeDistances(null, "A"));
    }
}