import java.util.*;

public class CampusNavigationSystem {
    private final Map<String, Set<String>> roadMap = new HashMap<>();

    public boolean addLocation(String location) {
        if (location == null || roadMap.containsKey(location)) return false;
        roadMap.put(location, new HashSet<>());
        return true;
    }

    public boolean addRoad(String loc1, String loc2) {
        if (loc1 == null || loc2 == null || loc1.equals(loc2)) return false;
        if (!roadMap.containsKey(loc1) || !roadMap.containsKey(loc2)) return false;
        roadMap.get(loc1).add(loc2);
        roadMap.get(loc2).add(loc1);
        return true;
    }

    public List<String> findShortestPath(String start, String target) {
        List<String> path = new ArrayList<>();
        if (start == null || target == null || !roadMap.containsKey(start) || !roadMap.containsKey(target)) {
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

            for (String neighbor : roadMap.get(curr)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    predecessor.put(neighbor, curr);
                    queue.offer(neighbor);
                }
            }
        }

        if (!reached) return path;

        String step = target;
        while (step != null) {
            path.add(step);
            step = predecessor.get(step);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        CampusNavigationSystem nav = new CampusNavigationSystem();
        nav.addLocation("MainGate");
        nav.addLocation("Library");
        nav.addLocation("EngineeringHall");
        nav.addLocation("Dormitory");

        nav.addRoad("MainGate", "Library");
        nav.addRoad("Library", "EngineeringHall");
        nav.addRoad("MainGate", "EngineeringHall");
        nav.addRoad("EngineeringHall", "Dormitory");

        System.out.println("Shortest path MainGate -> Dormitory: " + nav.findShortestPath("MainGate", "Dormitory"));
        System.out.println("Invalid search: " + nav.findShortestPath("MainGate", "Gym"));
    }
}