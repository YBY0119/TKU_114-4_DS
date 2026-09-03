import java.util.*;

public class Q12_CampusDispatchSystem {
    public record Request(String id, String location, int priority, long sequence) {}

    private final Map<String, Set<String>> graph = new HashMap<>();
    private final Map<String, Request> idToRequest = new HashMap<>();
    private final PriorityQueue<Request> pq = new PriorityQueue<>(
        Comparator.comparingInt(Request::priority)
                  .thenComparingLong(Request::sequence)
    );

    public boolean addLocation(String location) {
        if (location == null || graph.containsKey(location)) {
            return false;
        }
        graph.put(location, new HashSet<>());
        return true;
    }

    public boolean addRoad(String first, String second) {
        if (first == null || second == null || first.equals(second)) {
            return false;
        }
        if (!graph.containsKey(first) || !graph.containsKey(second)) {
            return false;
        }
        graph.get(first).add(second);
        graph.get(second).add(first);
        return true;
    }

    public boolean submit(Request request) {
        if (request == null || request.id() == null || request.location() == null) {
            return false;
        }
        if (!graph.containsKey(request.location()) || idToRequest.containsKey(request.id())) {
            return false; // missing location 或重複 request id
        }
        idToRequest.put(request.id(), request);
        pq.offer(request);
        return true;
    }

    public Request nextReachable(String serviceCenter) {
        if (serviceCenter == null || !graph.containsKey(serviceCenter) || pq.isEmpty()) {
            return null;
        }

        // BFS 取得從 serviceCenter 可達的所有地點
        Set<String> reachableLocations = getReachableLocations(serviceCenter);

        List<Request> skipped = new ArrayList<>();
        Request found = null;

        while (!pq.isEmpty()) {
            Request candidate = pq.poll();
            if (reachableLocations.contains(candidate.location())) {
                found = candidate;
                idToRequest.remove(candidate.id());
                break;
            } else {
                skipped.add(candidate);
            }
        }

        // 將不可到達的 request 放回佇列
        pq.addAll(skipped);
        return found;
    }

    private Set<String> getReachableLocations(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            for (String neighbor : graph.getOrDefault(curr, Collections.emptySet())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return visited;
    }

    public List<String> route(String start, String target) {
        List<String> path = new ArrayList<>();
        if (start == null || target == null || !graph.containsKey(start) || !graph.containsKey(target)) {
            return path;
        }
        if (start.equals(target)) {
            path.add(start);
            return path;
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> predecessor = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        boolean reached = false;
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.equals(target)) {
                reached = true;
                break;
            }

            for (String neighbor : graph.getOrDefault(curr, Collections.emptySet())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    predecessor.put(neighbor, curr);
                    queue.offer(neighbor);
                }
            }
        }

        if (!reached) {
            return path;
        }

        String curr = target;
        while (curr != null) {
            path.add(curr);
            curr = predecessor.get(curr);
        }
        Collections.reverse(path);
        return path;
    }

    public int pendingCount() {
        return pq.size();
    }

    public static void main(String[] args) {
        Q12_CampusDispatchSystem system = new Q12_CampusDispatchSystem();
        system.addLocation("Center");
        system.addLocation("Library");
        system.addLocation("Dorm");
        system.addLocation("IsolatedBuilding");

        system.addRoad("Center", "Library");
        system.addRoad("Library", "Dorm");

        system.submit(new Request("REQ1", "IsolatedBuilding", 1, 10L));
        system.submit(new Request("REQ2", "Dorm", 2, 20L));

        // REQ1 優先級高但不可達，應取出可達的 REQ2
        Request next = system.nextReachable("Center");
        System.out.println("Dispatched: " + next); // REQ2
        System.out.println("Pending (Isolated preserved): " + system.pendingCount()); // 1 (REQ1 保留)
        System.out.println("Route: " + system.route("Center", "Dorm")); // [Center, Library, Dorm]
    }
}