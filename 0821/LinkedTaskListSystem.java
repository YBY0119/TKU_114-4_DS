class Task {
    private final String id;
    private final String description;

    public Task(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() { return id; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "[" + id + ": " + description + "]";
    }
}

class TaskNode {
    Task task;
    TaskNode next;

    public TaskNode(Task task) {
        this.task = task;
        this.next = null;
    }
}

class TaskLinkedList {
    private TaskNode head;
    private int size;

    public TaskLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public boolean addFirst(Task task) {
        if (findById(task.getId()) != null) {
            System.out.println("Add failed: Duplicate ID " + task.getId());
            return false;
        }
        TaskNode newNode = new TaskNode(task);
        newNode.next = head;
        head = newNode;
        size++;
        return true;
    }

    public boolean addLast(Task task) {
        if (findById(task.getId()) != null) {
            System.out.println("Add failed: Duplicate ID " + task.getId());
            return false;
        }
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size++;
        return true;
    }

    public Task findById(String id) {
        TaskNode curr = head;
        while (curr != null) {
            if (curr.task.getId().equals(id)) {
                return curr.task;
            }
            curr = curr.next;
        }
        return null;
    }

    public boolean insertAfter(String existingId, Task task) {
        if (findById(task.getId()) != null) {
            System.out.println("Insert failed: Duplicate ID " + task.getId());
            return false;
        }
        TaskNode curr = head;
        while (curr != null) {
            if (curr.task.getId().equals(existingId)) {
                TaskNode newNode = new TaskNode(task);
                newNode.next = curr.next;
                curr.next = newNode;
                size++;
                return true;
            }
            curr = curr.next;
        }
        System.out.println("Insert failed: ID " + existingId + " not found.");
        return false;
    }

    public boolean removeById(String id) {
        if (head == null) return false;

        // 刪除 head
        if (head.task.getId().equals(id)) {
            head = head.next;
            size--;
            return true;
        }

        // 刪除 middle 或 tail
        TaskNode prev = head;
        TaskNode curr = head.next;
        while (curr != null) {
            if (curr.task.getId().equals(id)) {
                prev.next = curr.next;
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false; // 找不到
    }

    public int size() {
        return size;
    }

    public void printAll() {
        System.out.print("List (size=" + size + "): ");
        TaskNode curr = head;
        while (curr != null) {
            System.out.print(curr.task + (curr.next != null ? " -> " : ""));
            curr = curr.next;
        }
        System.out.println();
    }
}

public class LinkedTaskListSystem {
    public static void main(String[] args) {
        TaskLinkedList list = new TaskLinkedList();

        // 1. 測試空 list 刪除
        System.out.println("Remove from empty: " + list.removeById("T01"));

        // 新增元素
        list.addLast(new Task("T01", "Task 1"));
        list.addLast(new Task("T02", "Task 2"));
        list.addLast(new Task("T03", "Task 3"));
        list.addFirst(new Task("T00", "Task 0"));
        list.insertAfter("T02", new Task("T02.5", "Task 2.5"));
        list.printAll();

        // 2. 測試重複 ID
        list.addFirst(new Task("T01", "Duplicate"));

        // 3. 測試刪除找不到 ID
        System.out.println("Remove non-existing: " + list.removeById("T99"));

        // 4. 測試刪除 head
        list.removeById("T00");
        System.out.print("After remove head: ");
        list.printAll();

        // 5. 測試刪除 middle
        list.removeById("T02.5");
        System.out.print("After remove middle: ");
        list.printAll();

        // 6. 測試刪除 tail
        list.removeById("T03");
        System.out.print("After remove tail: ");
        list.printAll();
    }
}