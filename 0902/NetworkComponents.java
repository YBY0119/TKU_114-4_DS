import java.util.*;

public class NetworkComponents {
    public record ComponentAnalysis(List<Set<String>> components, int componentCount, Set<String> largestComponent) {}

    public static ComponentAnalysis analyzeNetwork(Map<String, List<String>> graph) {
        if (graph == null || graph.isEmpty()) {
            return new ComponentAnalysis(Collections.emptyList(), 0, Collections.emptySet());
        }

        List<Set<String>> components = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                Set<String> currentComp = new TreeSet<>();
                exploreBfs(graph, node, visited, currentComp);
                components.add(currentComp);
            }
        }

        Set<String> maxComp = Collections.emptySet();
        for (Set<String> comp : components) {
            if (comp.size() > maxComp.size()) {
                maxComp = comp;
            }
        }

        return new ComponentAnalysis(components, components.size(), maxComp);
    }

    private static void exploreBfs(Map<String, List<String>> graph, String start, Set<String> visited, Set<String> comp) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        comp.add(start);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            List<String> neighbors = graph.get(curr);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (neighbor != null && !visited.contains(neighbor)) {
                        visited.add(neighbor);
                        comp.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Map<String, List<String>> network = new HashMap<>();
        network.put("1", Arrays.asList("2"));
        network.put("2", Arrays.asList("1", "3"));
        network.put("3", Arrays.asList("2"));
        network.put("4", Arrays.asList("5"));
        network.put("5", Arrays.asList("4"));
        network.put("6", Collections.emptyList());

        ComponentAnalysis report = analyzeNetwork(network);
        System.out.println("Components: " + report.components());
        System.out.println("Total Count: " + report.componentCount());
        System.out.println("Largest Component: " + report.largestComponent());
    }
}