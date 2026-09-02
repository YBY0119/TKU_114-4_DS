import java.util.*;

public class LogisticsWeightedGraph {
    private Map<String, Map<String, Double>> adjMap = new HashMap<>();

    public void addVertex(String v) {
        adjMap.putIfAbsent(v, new HashMap<>());
    }

    public boolean addOrUpdateEdge(String u, String v, double weight) {
        if (!adjMap.containsKey(u) || !adjMap.containsKey(v)) {
            System.err.println("錯誤：起點或終點節點不存在 (" + u + ", " + v + ")");
            return false;
        }
        if (weight < 0) {
            System.err.println("錯誤：物流成本不能為負數 (" + weight + ")");
            return false;
        }
        adjMap.get(u).put(v, weight);
        return true;
    }

    public boolean removeEdge(String u, String v) {
        if (!adjMap.containsKey(u) || !adjMap.get(u).containsKey(v)) {
            return false;
        }
        adjMap.get(u).remove(v);
        return true;
    }

    public Double getEdgeWeight(String u, String v) {
        if (!adjMap.containsKey(u)) return null;
        return adjMap.get(u).get(v);
    }

    public static void main(String[] args) {
        LogisticsWeightedGraph graph = new LogisticsWeightedGraph();
        graph.addVertex("Taipei");
        graph.addVertex("Taichung");
        graph.addVertex("Kaohsiung");

        graph.addOrUpdateEdge("Taipei", "Taichung", 150.0);
        graph.addOrUpdateEdge("Taipei", "Taichung", 130.0); // 更新權重
        graph.addOrUpdateEdge("Taipei", "Tainan", 200.0);   // 節點不存在 -> 拒絕
        graph.addOrUpdateEdge("Taichung", "Kaohsiung", -50); // 負權重 -> 拒絕

        System.out.println("Taipei -> Taichung 運費: " + graph.getEdgeWeight("Taipei", "Taichung"));
    }
}