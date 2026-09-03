import java.util.*;

public class Q03_MinHeapRemove {
    private final List<Integer> heap = new ArrayList<>();

    public Q03_MinHeapRemove(List<Integer> values) {
        if (values != null) {
            for (Integer val : values) {
                if (val != null) {
                    heap.add(val);
                }
            }
            for (int i = (heap.size() / 2) - 1; i >= 0; i--) {
                bubbleDown(i);
            }
        }
    }

    public Integer removeMin() {
        if (heap.isEmpty()) {
            return null;
        }
        int min = heap.get(0);
        int lastVal = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastVal);
            bubbleDown(0);
        }
        return min;
    }

    private void bubbleDown(int index) {
        int size = heap.size();
        while (index < size) {
            int smallest = index;
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            if (left < size && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }
            if (right < size && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }

            if (smallest != index) {
                int temp = heap.get(index);
                heap.set(index, heap.get(smallest));
                heap.set(smallest, temp);
                index = smallest;
            } else {
                break;
            }
        }
    }

    public Integer peek() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public List<Integer> snapshot() {
        return new ArrayList<>(heap);
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(9, 4, 7, 1, null, 2, 6);
        Q03_MinHeapRemove minHeap = new Q03_MinHeapRemove(list);

        System.out.println("Snapshot after heapify: " + minHeap.snapshot());
        System.out.println("Removed Min: " + minHeap.removeMin()); // 預期: 1
        System.out.println("New Peek: " + minHeap.peek());        // 預期: 2
    }
}