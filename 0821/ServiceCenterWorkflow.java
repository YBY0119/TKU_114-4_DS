import java.util.*;

class ServiceTicket {
    private final String id;
    private final String customerName;

    public ServiceTicket(String id, String customerName) {
        this.id = id;
        this.customerName = customerName;
    }

    public String getId() { return id; }
    public String getCustomerName() { return customerName; }

    @Override
    public String toString() {
        return "Ticket{id='" + id + "', name='" + customerName + "'}";
    }
}

public class ServiceCenterWorkflow {
    private final Map<String, ServiceTicket> ticketMap = new HashMap<>();
    private final Deque<ServiceTicket> waitingQueue = new ArrayDeque<>();
    private final Deque<ServiceTicket> completedStack = new ArrayDeque<>();
    private final Set<String> idSet = new HashSet<>();

    public boolean createTicket(String id, String customerName) {
        if (!idSet.add(id)) {
            System.out.println("Create failed: Duplicate ticket ID " + id);
            return false;
        }
        ServiceTicket ticket = new ServiceTicket(id, customerName);
        ticketMap.put(id, ticket);
        waitingQueue.addLast(ticket);
        System.out.println("Created: " + ticket);
        return true;
    }

    public ServiceTicket processNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("Process failed: Waiting queue is empty.");
            return null;
        }
        ServiceTicket ticket = waitingQueue.pollFirst();
        completedStack.push(ticket);
        System.out.println("Processed: " + ticket);
        return ticket;
    }

    public boolean cancelWaiting(String id) {
        // 只能作用於尚未處理的 ticket
        for (ServiceTicket ticket : waitingQueue) {
            if (ticket.getId().equals(id)) {
                waitingQueue.remove(ticket);
                ticketMap.remove(id);
                idSet.remove(id);
                System.out.println("Cancelled waiting ticket: " + ticket);
                return true;
            }
        }
        System.out.println("Cancel failed: Ticket ID " + id + " not found in waiting queue.");
        return false;
    }

    public ServiceTicket undoLastCompletion() {
        if (completedStack.isEmpty()) {
            System.out.println("Undo failed: No completed tickets to undo.");
            return null;
        }
        ServiceTicket ticket = completedStack.pop();
        waitingQueue.addFirst(ticket); // 放回 waiting queue 前端
        System.out.println("Undone and put back to front: " + ticket);
        return ticket;
    }

    public ServiceTicket findById(String id) {
        return ticketMap.get(id);
    }

    public void printSummary() {
        System.out.println("=== Service Center Summary ===");
        System.out.println("Waiting Queue (" + waitingQueue.size() + "): " + waitingQueue);
        System.out.println("Completed Stack (" + completedStack.size() + "): " + completedStack);
        System.out.println("Total Tracked IDs: " + idSet);
        System.out.println("==============================");
    }

    public static void main(String[] args) {
        ServiceCenterWorkflow workflow = new ServiceCenterWorkflow();

        // 測試 1: 重複 ID
        workflow.createTicket("T01", "Alice");
        workflow.createTicket("T01", "Alice Clone");

        // 測試 2: 取消不存在 ID
        workflow.cancelWaiting("T99");

        // 測試 3: 取消待處理 ID
        workflow.createTicket("T02", "Bob");
        workflow.cancelWaiting("T02");

        // 處理並測試
        workflow.createTicket("T03", "Charlie");
        workflow.processNext(); // T01
        workflow.processNext(); // T03

        // 測試 4: 空 Queue 處理
        workflow.processNext();

        // 測試 5: 連續兩次 undo
        workflow.undoLastCompletion(); // T03 回到隊首
        workflow.undoLastCompletion(); // T01 回到隊首
        workflow.undoLastCompletion(); // 測試空 completedStack undo

        workflow.printSummary();
    }
}