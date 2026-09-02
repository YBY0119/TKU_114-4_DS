import java.util.*;

public class SocialNetworkGraph {
    private Map<String, Set<String>> adjList = new HashMap<>();

    public void addUser(String user) {
        adjList.putIfAbsent(user, new HashSet<>());
    }

    public void addFriend(String u, String v) {
        addUser(u);
        addUser(v);
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    public void removeFriend(String u, String v) {
        if (adjList.containsKey(u)) adjList.get(u).remove(v);
        if (adjList.containsKey(v)) adjList.get(v).remove(u);
    }

    public Set<String> getMutualFriends(String u, String v) {
        if (!adjList.containsKey(u) || !adjList.containsKey(v)) return Collections.emptySet();
        Set<String> mutual = new HashSet<>(adjList.get(u));
        mutual.retainAll(adjList.get(v));
        return mutual;
    }

    public List<String> getIsolatedUsers() {
        List<String> isolated = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : adjList.entrySet()) {
            if (entry.getValue().isEmpty()) {
                isolated.add(entry.getKey());
            }
        }
        return isolated;
    }

    public static void main(String[] args) {
        SocialNetworkGraph sn = new SocialNetworkGraph();
        sn.addFriend("Alice", "Bob");
        sn.addFriend("Alice", "Charlie");
        sn.addFriend("Bob", "Charlie");
        sn.addUser("David"); // 孤立用戶

        System.out.println("Alice 與 Bob 共同好友: " + sn.getMutualFriends("Alice", "Bob"));
        System.out.println("孤立用戶: " + sn.getIsolatedUsers());
    }
}