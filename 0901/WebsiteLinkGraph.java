import java.util.*;

public class WebsiteLinkGraph {
    private Map<String, Set<String>> outgoing = new HashMap<>();
    private Map<String, Set<String>> incoming = new HashMap<>();

    public void addPage(String page) {
        outgoing.putIfAbsent(page, new HashSet<>());
        incoming.putIfAbsent(page, new HashSet<>());
    }

    public void addLink(String from, String to) {
        addPage(from);
        addPage(to);
        outgoing.get(from).add(to);
        incoming.get(to).add(from);
    }

    public Set<String> getOutgoingLinks(String page) {
        return outgoing.getOrDefault(page, Collections.emptySet());
    }

    public int getIncomingCount(String page) {
        return incoming.containsKey(page) ? incoming.get(page).size() : 0;
    }

    public List<String> getNoIncomingPages() {
        List<String> result = new ArrayList<>();
        for (String page : incoming.keySet()) {
            if (incoming.get(page).isEmpty()) result.add(page);
        }
        return result;
    }

    public List<String> getNoOutgoingPages() {
        List<String> result = new ArrayList<>();
        for (String page : outgoing.keySet()) {
            if (outgoing.get(page).isEmpty()) result.add(page);
        }
        return result;
    }

    public static void main(String[] args) {
        WebsiteLinkGraph graph = new WebsiteLinkGraph();
        graph.addLink("Home", "About");
        graph.addLink("Home", "Contact");
        graph.addLink("About", "Contact");
        graph.addPage("Standalone");

        System.out.println("Home 的 outgoing links: " + graph.getOutgoingLinks("Home"));
        System.out.println("Contact 的 incoming count: " + graph.getIncomingCount("Contact"));
        System.out.println("無 incoming 的頁面: " + graph.getNoIncomingPages());
        System.out.println("無 outgoing 的頁面: " + graph.getNoOutgoingPages());
    }
}