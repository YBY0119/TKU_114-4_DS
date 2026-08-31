import java.util.Arrays;
import java.util.NoSuchElementException;

public class IntegerMinHeap {
    private int[] heap;
    private int size;

    public IntegerMinHeap() {
        this.heap = new int[10];
        this.size = 0;
    }

    public void add(int val) {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
        heap[size] = val;
        siftUp(size);
        size++;
    }

    public int peek() {
        if (isEmpty()) throw new NoSuchElementException("Heap is empty");
        return heap[0];
    }

    public int removeMin() {
        if (isEmpty()) throw new NoSuchElementException("Heap is empty");
        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        siftDown(0);
        return min;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void siftUp(int idx) {
        while (idx > 0) {
            int parent = (idx - 1) / 2;
            if (heap[idx] < heap[parent]) {
                swap(idx, parent);
                idx = parent;
            } else {
                break;
            }
        }
    }

    private void siftDown(int idx) {
        while (2 * idx + 1 < size) {
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;
            int smallest = (right < size && heap[right] < heap[left]) ? right : left;
            if (heap[idx] > heap[smallest]) {
                swap(idx, smallest);
                idx = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static void main(String[] args) {
        IntegerMinHeap minHeap = new IntegerMinHeap();
        int[] inputs = {30, 10, 50, 20, 40, 5, 20};
        for (int v : inputs) minHeap.add(v);

        System.out.println("Polling elements (should be non-decreasing):");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.removeMin() + " ");
        }
        System.out.println();
    }
}