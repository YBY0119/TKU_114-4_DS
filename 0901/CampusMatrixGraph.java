import java.util.*;

public class CampusMatrixGraph {
    private boolean[][] adjMatrix;
    private String[] vertices;
    private Map<String, Integer> vertexMap;
    private int edgeCount = 0;

    public CampusMatrixGraph(String[] places) {
        int n = places.length;
        this.vertices = places.clone();
        this.adjMatrix = new boolean[n][n];
        this.vertexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            vertexMap.put(places[i], i);
        }
    }

    public void addEdge(String u, String v) {
        if (!vertexMap.containsKey(u) || !vertexMap.containsKey(v)) return;
        int i = vertexMap.get(u);
        int j = vertexMap.get(v);
        if (!adjMatrix[i][j]) {
            adjMatrix[i][j] = true;
            adjMatrix[j][i] = true;
            edgeCount++;
        }
    }

    public void removeEdge(String u, String v) {
        if (!vertexMap.containsKey(u) || !vertexMap.containsKey(v)) return;
        int i = vertexMap.get(u);
        int j = vertexMap.get(v);
        if (adjMatrix[i][j]) {
            adjMatrix[i][j] = false;
            adjMatrix[j][i] = false;
            edgeCount--;
        }
    }

    public int getDegree(String u) {
        if (!vertexMap.containsKey(u)) return -1;
        int i = vertexMap.get(u);
        int degree = 0;
        for (int j = 0; j < vertices.length; j++) {
            if (adjMatrix[i][j]) degree++;
        }
        return degree;
    }

    public List<String> getNeighbors(String u) {
        List<String> neighbors = new ArrayList<>();
        if (!vertexMap.containsKey(u)) return neighbors;
        int i = vertexMap.get(u);
        for (int j = 0; j < vertices.length; j++) {
            if (adjMatrix[i][j]) neighbors.add(vertices[j]);
        }
        return neighbors;
    }

    public int getEdgeCount() { return edgeCount; }

    public static void main(String[] args) {
        String[] spots = {"圖書館", "學餐", "宿舍", "體育館"};
        CampusMatrixGraph graph = new CampusMatrixGraph(spots);
        graph.addEdge("圖書館", "學餐");
        graph.addEdge("圖書館", "宿舍");
        graph.addEdge("學餐", "圖書館"); // 重複新增測試

        System.out.println("邊總數: " + graph.getEdgeCount()); // 應為 2
        System.out.println("圖書館 Degree: " + graph.getDegree("圖書館"));
        System.out.println("圖書館 鄰居: " + graph.getNeighbors("圖書館"));
    }
}