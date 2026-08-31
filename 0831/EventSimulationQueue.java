import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class EventSimulationQueue {
    static class Event implements Comparable<Event> {
        int eventId;
        int time;
        String type;
        int sequence;

        public Event(int eventId, int time, String type, int sequence) {
            this.eventId = eventId;
            this.time = time;
            this.type = type;
            this.sequence = sequence;
        }

        @Override
        public int compareTo(Event o) {
            if (this.time != o.time) {
                return Integer.compare(this.time, o.time);
            }
            return Integer.compare(this.sequence, o.sequence);
        }

        @Override
        public String toString() {
            return "Event{id=" + eventId + ", time=" + time + ", type='" + type + "', seq=" + sequence + "}";
        }
    }

    private final PriorityQueue<Event> pq = new PriorityQueue<>();
    private final List<String> logs = new ArrayList<>();

    public void addEvent(Event e) {
        pq.offer(e);
    }

    public boolean cancelEvent(int eventId) {
        return pq.removeIf(e -> e.eventId == eventId);
    }

    public void runSimulation() {
        while (!pq.isEmpty()) {
            Event current = pq.poll();
            String log = "Executed: " + current;
            logs.add(log);
            System.out.println(log);
        }
    }

    public static void main(String[] args) {
        EventSimulationQueue sim = new EventSimulationQueue();
        sim.addEvent(new Event(1, 10, "SPAWN", 1));
        sim.addEvent(new Event(2, 5, "START", 1));
        sim.addEvent(new Event(3, 10, "ATTACK", 0));
        sim.addEvent(new Event(4, 8, "CANCEL_ME", 1));

        sim.cancelEvent(4);
        sim.runSimulation();
    }
}