import java.util.*;

public class IterativeDfsTrace {

    public static void dfsWithTrace(Map<String, List<String>> graph, String start) {
        System.out.println("=== Starting DFS Trace from: " + start + " ===");
        if (graph == null || start == null || !graph.containsKey(start)) {
            System.out.println("Invalid graph or missing start vertex.");
            return;
        }

        Deque<String> stack = new ArrayDeque<>();
        Set<String> visited = new LinkedHashSet<>();

        stack.push(start);
        System.out.println("[PUSH] Stack: " + stack + " | Visited: " + visited);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            System.out.println("[POP ] Current: " + current + " | Stack: " + stack + " | Visited: " + visited);

            if (!visited.contains(current)) {
                visited.add(current);
                System.out.println("[VISIT] Marked " + current + " visited -> Visited: " + visited);

                List<String> neighbors = graph.get(current);
                if (neighbors != null) {
                    for (int i = neighbors.size() - 1; i >= 0; i--) {
                        String neighbor = neighbors.get(i);
                        if (neighbor != null && !visited.contains(neighbor)) {
                            stack.push(neighbor);
                            System.out.println("[PUSH] Stack: " + stack + " | Visited: " + visited);
                        }
                    }
                }
            }
        }
        System.out.println("Traversal completed. Final Visited Order: " + visited);
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("D", "E"));
        graph.put("C", Arrays.asList("F"));
        graph.put("D", Collections.emptyList());
        graph.put("E", Arrays.asList("F"));
        graph.put("F", Collections.emptyList());

        dfsWithTrace(graph, "A");
        dfsWithTrace(graph, "UNKNOWN");
    }
}