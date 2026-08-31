import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayMinHeap {
    private int[] data;
    private int size;

    public ArrayMinHeap(int initialCapacity) {
        this.data = new int[initialCapacity];
        this.size = 0;
    }

    public ArrayMinHeap() {
        this(10);
    }

    public void add(int val) {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2); // 自動擴充2倍
        }
        data[size] = val;
        siftUp(size);
        size++;
    }

    public int remove() {
        if (size == 0) throw new NoSuchElementException("Heap is empty");
        int min = data[0];
        data[0] = data[size - 1];
        size--;
        siftDown(0);
        return min;
    }

    public int peek() {
        if (size == 0) throw new NoSuchElementException("Heap is empty");
        return data[0];
    }

    public int[] snapshot() {
        return Arrays.copyOf(data, size);
    }

    public int size() {
        return size;
    }

    private void siftUp(int idx) {
        while (idx > 0) {
            int parent = (idx - 1) / 2;
            if (data[idx] < data[parent]) {
                swap(idx, parent);
                idx = parent;
            } else break;
        }
    }

    private void siftDown(int idx) {
        while (2 * idx + 1 < size) {
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;
            int smallest = (right < size && data[right] < data[left]) ? right : left;
            if (data[idx] > data[smallest]) {
                swap(idx, smallest);
                idx = smallest;
            } else break;
        }
    }

    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main(String[] args) {
        ArrayMinHeap heap = new ArrayMinHeap(4);
        int[] testData = {45, 12, 85, 32, 89, 39, 69, 44, 42, 1, 68, 73, 55, 87, 84, 11, 78, 61, 91, 5};

        System.out.println("--- 寫入 20 筆資料測試 ---");
        for (int v : testData) {
            heap.add(v);
        }
        System.out.println("Snapshot: " + Arrays.toString(heap.snapshot()));

        System.out.println("--- 依序取出 ---");
        while (heap.size() > 0) {
            System.out.print(heap.remove() + " ");
        }
        System.out.println();
    }
}