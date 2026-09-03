import java.util.*;

public class Q06_AdjacencyMatrixGraph {
    private final List<String> vertexList;
    private final Map<String, Integer> vertexMap;
    private final boolean[][] matrix;

    public Q06_AdjacencyMatrixGraph(List<String> vertices) {
        this.vertexList = new ArrayList<>();
        this.vertexMap = new HashMap<>();

        if (vertices != null) {
            for (String v : vertices) {
                if (v != null && !vertexMap.containsKey(v)) {
                    vertexMap.put(v, vertexList.size());
                    vertexList.add(v);
                }
            }
        }

        int n = vertexList.size();
        this.matrix = new boolean[n][n];
    }

    public boolean addEdge(String first, String second) {
        if (first == null || second == null || first.equals(second)) {
            return false;
        }
        Integer u = vertexMap.get(first);
        Integer v = vertexMap.get(second);

        if (u == null || v == null || matrix[u][v]) {
            return false;
        }

        matrix[u][v] = true;
        matrix[v][u] = true;
        return true;
    }

    public boolean removeEdge(String first, String second) {
        if (first == null || second == null) {
            return false;
        }
        Integer u = vertexMap.get(first);
        Integer v = vertexMap.get(second);

        if (u == null || v == null || !matrix[u][v]) {
            return false;
        }

        matrix[u][v] = false;
        matrix[v][u] = false;
        return true;
    }

    public boolean hasEdge(String first, String second) {
        if (first == null || second == null) {
            return false;
        }
        Integer u = vertexMap.get(first);
        Integer v = vertexMap.get(second);

        if (u == null || v == null) {
            return false;
        }
        return matrix[u][v];
    }

    public int degree(String vertex) {
        if (vertex == null) {
            return 0;
        }
        Integer u = vertexMap.get(vertex);
        if (u == null) {
            return 0;
        }

        int count = 0;
        for (int j = 0; j < vertexList.size(); j++) {
            if (matrix[u][j]) {
                count++;
            }
        }
        return count;
    }

    public List<String> neighbors(String vertex) {
        List<String> result = new ArrayList<>();
        if (vertex == null) {
            return result;
        }
        Integer u = vertexMap.get(vertex);
        if (u == null) {
            return result;
        }

        for (int j = 0; j < vertexList.size(); j++) {
            if (matrix[u][j]) {
                result.add(vertexList.get(j));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> vertices = Arrays.asList("A", "B", "C", "D");
        Q06_AdjacencyMatrixGraph graph = new Q06_AdjacencyMatrixGraph(vertices);

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");

        System.out.println("Degree of A: " + graph.degree("A")); // 預期: 2
        System.out.println("Neighbors of A: " + graph.neighbors("A")); // 預期: [B, C]
        System.out.println("Missing vertex query test: " + graph.neighbors("Z")); // 預期: [] (不拋錯)
    }
}