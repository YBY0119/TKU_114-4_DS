import java.util.Arrays;

class CircularQueue<T> {
    private final Object[] elements;
    private int front;
    private int rear;
    private int size;
    private final int capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.elements = new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public boolean enqueue(T item) {
        if (size == capacity) {
            System.out.println("Enqueue failed: Queue is full");
            return false;
        }
        elements[rear] = item;
        rear = (rear + 1) % capacity;
        size++;
        printState("enqueue " + item);
        return true;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (size == 0) {
            System.out.println("Dequeue failed: Queue is empty");
            return null;
        }
        T item = (T) elements[front];
        elements[front] = null;
        front = (front + 1) % capacity;
        size--;
        printState("dequeue");
        return item;
    }

    public void printState(String op) {
        System.out.printf("After [%-12s] -> Array: %s, front: %d, rear: %d, size: %d%n",
                op, Arrays.toString(elements), front, rear, size);
    }
}

public class CircularQueuePractice {
    public static void main(String[] args) {
        CircularQueue<String> queue = new CircularQueue<>(4);

        System.out.println("--- 步驟執行追蹤 ---");
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("D");
        queue.enqueue("E");
        queue.enqueue("F");
        queue.dequeue();
        queue.enqueue("G");

        System.out.println("\n--- FIFO 依序取出剩餘元素 ---");
        while (true) {
            String val = queue.dequeue();
            if (val == null) break;
            System.out.println("Removed: " + val);
        }
    }
}