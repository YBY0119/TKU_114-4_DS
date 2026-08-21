import java.util.ArrayDeque;
import java.util.Deque;

class Customer {
    private final String id;
    private final String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Customer{id='" + id + "', name='" + name + "'}";
    }
}

public class CounterWaitingQueue {
    private final Deque<Customer> queue = new ArrayDeque<>();

    public void enqueue(Customer customer) {
        queue.addLast(customer);
        System.out.println("Joined: " + customer);
    }

    public Customer peekNext() {
        if (queue.isEmpty()) {
            System.out.println("Peek: Queue is empty.");
            return null;
        }
        System.out.println("Next customer: " + queue.peekFirst());
        return queue.peekFirst();
    }

    public Customer serveNext() {
        if (queue.isEmpty()) {
            System.out.println("Serve failed: Queue is empty.");
            return null;
        }
        Customer served = queue.pollFirst();
        System.out.println("Served: " + served);
        return served;
    }

    public int getWaitingCount() {
        return queue.size();
    }

    public static void main(String[] args) {
        CounterWaitingQueue counter = new CounterWaitingQueue();

        // 測試空隊列處理
        counter.serveNext();
        counter.peekNext();

        // 加入顧客
        counter.enqueue(new Customer("C01", "Alice"));
        counter.enqueue(new Customer("C02", "Bob"));
        counter.enqueue(new Customer("C03", "Charlie"));

        System.out.println("Waiting count: " + counter.getWaitingCount());
        counter.peekNext();
        counter.serveNext();
        counter.serveNext();
        System.out.println("Waiting count: " + counter.getWaitingCount());
        counter.serveNext();
        counter.serveNext(); // 再次測試空隊列
    }
}