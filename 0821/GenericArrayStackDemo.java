

class ArrayStack<T> {
    private Object[] elements;
    private int top;
    private final int capacity;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.elements = new Object[capacity];
        this.top = -1;
    }

    public void push(T value) {
        if (isFull()) {
            throw new IllegalStateException("Stack Overflow: Stack is full.");
        }
        elements[++top] = value;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack Underflow: Stack is empty.");
        }
        T item = (T) elements[top];
        elements[top--] = null;
        return item;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return (T) elements[top];
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }
}

public class GenericArrayStackDemo {
    public static void main(String[] args) {
        System.out.println("--- Testing ArrayStack<String> ---");
        ArrayStack<String> strStack = new ArrayStack<>(3);
        strStack.push("A");
        strStack.push("B");
        strStack.push("C");
        System.out.println("Is Full: " + strStack.isFull());
        System.out.println("Peek: " + strStack.peek());
        while (!strStack.isEmpty()) {
            System.out.println("Pop: " + strStack.pop());
        }

        System.out.println("\n--- Testing ArrayStack<Integer> ---");
        ArrayStack<Integer> intStack = new ArrayStack<>(2);
        intStack.push(100);
        intStack.push(200);
        System.out.println("Size: " + intStack.size());
        System.out.println("Pop: " + intStack.pop());
        System.out.println("Peek: " + intStack.peek());
    }
}