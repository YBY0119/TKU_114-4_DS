import java.util.*;

public class Q07_AdjacencyListGraph {
    // 使用 LinkedHashSet 保留 edge 加入順序並防止重複 edge
    private final Map<String, Set<String>> adjList = new HashMap<>();

    public boolean addVertex(String vertex) {
        if (vertex == null || adjList.containsKey(vertex)) {
            return false;
        }
        adjList.put(vertex, new LinkedHashSet<>());
        return true;
    }

    public boolean addEdge(String from, String to) {
        if (from == null || to == null || from.equals(to)) {
            return false; // 拒絕 null 與 self-loop
        }
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            return false; // missing vertex
        }
        return adjList.get(from).add(to); // 若已存在回傳 false
    }

    public boolean removeEdge(String from, String to) {
        if (from == null || to == null) {
            return false;
        }
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            return false;
        }
        return adjList.get(from).remove(to);
    }

    public List<String> outgoing(String vertex) {
        if (vertex == null || !adjList.containsKey(vertex)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(adjList.get(vertex));
    }

    public int inDegree(String vertex) {
        if (vertex == null || !adjList.containsKey(vertex)) {
            return 0;
        }
        int count = 0;
        for (Set<String> neighbors : adjList.values()) {
            if (neighbors.contains(vertex)) {
                count++;
            }
        }
        return count;
    }

    public int edgeCount() {
        int count = 0;
        for (Set<String> neighbors : adjList.values()) {
            count += neighbors.size();
        }
        return count;
    }

    public static void main(String[] args) {
        Q07_AdjacencyListGraph graph = new Q07_AdjacencyListGraph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");

        System.out.println("Outgoing A: " + graph.outgoing("A")); // [B, C]
        System.out.println("InDegree C: " + graph.inDegree("C")); // 2
        System.out.println("Total Edges: " + graph.edgeCount());   // 3
    }
}