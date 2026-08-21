import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class DeliveryTask {
    private final String id;
    private final String address;

    public DeliveryTask(String id, String address) {
        this.id = id;
        this.address = address;
    }

    public String getId() { return id; }
    public String getAddress() { return address; }

    @Override
    public String toString() {
        return "DeliveryTask{id='" + id + "', address='" + address + "'}";
    }
}

public class DeliveryWorkflowSystem {
    private final Map<String, DeliveryTask> taskMap = new HashMap<>();
    private final Deque<DeliveryTask> waitingQueue = new ArrayDeque<>();
    private final Deque<DeliveryTask> completedStack = new ArrayDeque<>();

    public boolean addTask(String id, String address) {
        if (taskMap.containsKey(id)) {
            System.out.println("Add failed: Duplicate ID " + id);
            return false;
        }
        DeliveryTask task = new DeliveryTask(id, address);
        taskMap.put(id, task);
        waitingQueue.addLast(task);
        System.out.println("Added: " + task);
        return true;
    }

    public DeliveryTask processNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("Process failed: No tasks in queue.");
            return null;
        }
        DeliveryTask task = waitingQueue.pollFirst();
        completedStack.push(task);
        System.out.println("Processed: " + task);
        return task;
    }

    public DeliveryTask undo() {
        if (completedStack.isEmpty()) {
            System.out.println("Undo failed: No completed tasks.");
            return null;
        }
        DeliveryTask undone = completedStack.pop();
        waitingQueue.addFirst(undone);
        System.out.println("Undone: " + undone);
        return undone;
    }

    public DeliveryTask findById(String id) {
        return taskMap.get(id);
    }

    public void printStats() {
        System.out.println("--- Delivery Stats ---");
        System.out.println("Total Tasks: " + taskMap.size());
        System.out.println("Waiting Queue Size: " + waitingQueue.size());
        System.out.println("Completed Count: " + completedStack.size());
        System.out.println("----------------------");
    }

    public static void main(String[] args) {
        DeliveryWorkflowSystem system = new DeliveryWorkflowSystem();

        system.addTask("D01", "100 Main St");
        system.addTask("D02", "200 Oak St");
        system.addTask("D01", "300 Pine St"); // 重複 ID 測試

        system.processNext();
        system.printStats();

        system.undo();
        system.printStats();

        System.out.println("Query D01: " + system.findById("D01"));
        System.out.println("Query D99: " + system.findById("D99"));
    }
}