import java.util.*;

public class ServiceRequestSystem {
    public record Request(String id, String description, int priority, long timestamp) {}

    private final Map<String, Request> lookupMap = new HashMap<>();
    private final PriorityQueue<Request> pq = new PriorityQueue<>(
        Comparator.comparingInt(Request::priority)
                  .thenComparingLong(Request::timestamp)
    );

    public boolean submitRequest(Request req) {
        if (req == null || req.id() == null || lookupMap.containsKey(req.id())) {
            return false;
        }
        lookupMap.put(req.id(), req);
        pq.offer(req);
        return true;
    }

    public Request queryById(String id) {
        if (id == null) return null;
        return lookupMap.get(id);
    }

    public Request processNext() {
        if (pq.isEmpty()) return null;
        Request next = pq.poll();
        lookupMap.remove(next.id());
        return next;
    }

    public boolean cancelRequest(String id) {
        if (id == null || !lookupMap.containsKey(id)) {
            return false;
        }
        Request target = lookupMap.remove(id);
        pq.remove(target); // 確保 PriorityQueue 與 HashMap 一致
        return true;
    }

    public int pendingCount() {
        return lookupMap.size();
    }

    public static void main(String[] args) {
        ServiceRequestSystem sys = new ServiceRequestSystem();
        sys.submitRequest(new Request("REQ1", "Printer issue", 2, 1000L));
        sys.submitRequest(new Request("REQ2", "Network down", 1, 1050L));
        sys.submitRequest(new Request("REQ3", "Password reset", 1, 1100L));

        System.out.println("Query REQ1: " + sys.queryById("REQ1"));
        sys.cancelRequest("REQ2"); // 取消高優先級 REQ2

        System.out.println("Processed: " + sys.processNext()); // 應為 REQ3
        System.out.println("Remaining Count: " + sys.pendingCount()); // 1
    }
}