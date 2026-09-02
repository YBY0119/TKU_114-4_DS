import java.util.*;

public class MetroMatrixGraph {
    private boolean[][] matrix;
    private String[] stations;
    private Map<String, Integer> indexMap;
    private int edgeCount = 0;

    public MetroMatrixGraph(String[] stations) {
        this.stations = stations.clone();
        int n = stations.length;
        this.matrix = new boolean[n][n];
        this.indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) indexMap.put(stations[i], i);
    }

    public void addEdge(String u, String v) {
        if (!indexMap.containsKey(u) || !indexMap.containsKey(v)) return;
        int i = indexMap.get(u), j = indexMap.get(v);
        if (!matrix[i][j]) {
            matrix[i][j] = true;
            matrix[j][i] = true;
            edgeCount++;
        }
    }

    public List<String> getNeighbors(String station) {
        List<String> list = new ArrayList<>();
        if (!indexMap.containsKey(station)) return list;
        int i = indexMap.get(station);
        for (int j = 0; j < stations.length; j++) {
            if (matrix[i][j]) list.add(stations[j]);
        }
        return list;
    }

    public int getDegree(String station) {
        return getNeighbors(station).size();
    }

    public void printMatrixReport() {
        System.out.println("=== Metro Matrix Report ===");
        System.out.printf("%-10s", "");
        for (String s : stations) System.out.printf("%-10s", s);
        System.out.println();

        for (int i = 0; i < stations.length; i++) {
            System.out.printf("%-10s", stations[i]);
            for (int j = 0; j < stations.length; j++) {
                System.out.printf("%-10d", matrix[i][j] ? 1 : 0);
            }
            System.out.println();
        }
        System.out.println("Total Edges: " + edgeCount);
    }

    public static void main(String[] args) {
        String[] stations = {"台北車站", "中山", "雙連", "西門"};
        MetroMatrixGraph metro = new MetroMatrixGraph(stations);
        metro.addEdge("台北車站", "中山");
        metro.addEdge("中山", "雙連");
        metro.addEdge("台北車站", "西門");

        metro.printMatrixReport();
        System.out.println("台北車站 鄰站: " + metro.getNeighbors("台北車站"));
        System.out.println("台北車站 Degree: " + metro.getDegree("台北車站"));
    }
}