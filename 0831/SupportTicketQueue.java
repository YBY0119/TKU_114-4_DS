import java.util.PriorityQueue;

public class SupportTicketQueue {
    static class Ticket implements Comparable<Ticket> {
        int id;
        int severity;
        int createdOrder;

        public Ticket(int id, int severity, int createdOrder) {
            this.id = id;
            this.severity = severity;
            this.createdOrder = createdOrder;
        }

        @Override
        public int compareTo(Ticket o) {
            if (this.severity != o.severity) {
                return Integer.compare(o.severity, this.severity); // severity 越大越優先
            }
            return Integer.compare(this.createdOrder, o.createdOrder); // createdOrder 越小越早
        }

        @Override
        public String toString() {
            return id + "|" + severity + "|" + createdOrder;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Ticket> pq = new PriorityQueue<>();
        pq.add(new Ticket(101, 3, 1));
        pq.add(new Ticket(102, 5, 2));
        pq.add(new Ticket(103, 5, 3));
        pq.add(new Ticket(104, 2, 4));
        pq.add(new Ticket(105, 3, 5));

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}