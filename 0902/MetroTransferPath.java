import java.util.*;

public class MetroTransferPath {
    public record PathResult(List<String> path, int edgeCount) {}

    public static PathResult findShortestMetroPath(Map<String, List<String>> network, String start, String destination) {
        if (network == null || start == null || destination == null) {
            return new PathResult(Collections.emptyList(), -1);
        }
        if (!network.containsKey(start) || !network.containsKey(destination)) {
            return new PathResult(Collections.emptyList(), -1);
        }
        if (start.equals(destination)) {
            return new PathResult(Collections.singletonList(start), 0);
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> prev = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        boolean reached = false;
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.equals(destination)) {
                reached = true;
                break;
            }

            List<String> neighbors = network.get(curr);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (neighbor != null && !visited.contains(neighbor)) {
                        visited.add(neighbor);
                        prev.put(neighbor, curr);
                        queue.offer(neighbor);
                    }
                }
            }
        }

        if (!reached) {
            return new PathResult(Collections.emptyList(), -1);
        }

        List<String> fullPath = new ArrayList<>();
        String step = destination;
        while (step != null) {
            fullPath.add(step);
            step = prev.get(step);
        }
        Collections.reverse(fullPath);
        return new PathResult(fullPath, fullPath.size() - 1);
    }

    public static void main(String[] args) {
        Map<String, List<String>> metro = new HashMap<>();
        metro.put("StationA", Arrays.asList("StationB", "StationC"));
        metro.put("StationB", Arrays.asList("StationA", "StationD"));
        metro.put("StationC", Arrays.asList("StationA", "StationD", "StationE"));
        metro.put("StationD", Arrays.asList("StationB", "StationC", "StationF"));
        metro.put("StationE", Arrays.asList("StationF"));
        metro.put("StationF", Arrays.asList("StationD", "StationE"));

        System.out.println(findShortestMetroPath(metro, "StationA", "StationF"));
        System.out.println(findShortestMetroPath(metro, "StationA", "StationA"));
        System.out.println(findShortestMetroPath(metro, "StationA", "MissingStation"));
    }
}