import java.util.ArrayList;
import java.util.List;

public class Q05_BoundedBox<T extends Comparable<T>> {
    private final int capacity;
    private final List<T> items;

    public Q05_BoundedBox(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity 必須大於等於 1");
        }
        this.capacity = capacity;
        this.items = new ArrayList<>(capacity);
    }

    public boolean add(T value) {
        if (value == null || isFull()) {
            return false;
        }
        items.add(value);
        return true;
    }

    public int size() {
        return items.size();
    }

    public boolean isFull() {
        return items.size() >= capacity;
    }

    public T minimum() {
        if (items.isEmpty()) {
            return null;
        }
        T min = items.get(0);
        for (int i = 1; i < items.size(); i++) {
            T cur = items.get(i);
            if (cur.compareTo(min) < 0) {
                min = cur;
            }
        }
        return min;
    }

    public T maximum() {
        if (items.isEmpty()) {
            return null;
        }
        T max = items.get(0);
        for (int i = 1; i < items.size(); i++) {
            T cur = items.get(i);
            if (cur.compareTo(max) > 0) {
                max = cur;
            }
        }
        return max;
    }

    public int countGreaterThan(T threshold) {
        if (threshold == null) {
            return 0;
        }
        int count = 0;
        for (T item : items) {
            if (item.compareTo(threshold) > 0) {
                count++;
            }
        }
        return count;
    }

    public List<T> snapshot() {
        // 回傳副本，caller 修改回傳 List 時不影響內部
        return new ArrayList<>(items);
    }

    public static void main(String[] args) {
        Q05_BoundedBox<Integer> box = new Q05_BoundedBox<>(3);
        System.out.println(box.add(40));                // true
        System.out.println(box.add(10));                // true
        System.out.println(box.add(30));                // true
        System.out.println(box.add(20));                // false (超過容量)
        System.out.println(box.minimum());              // 10
        System.out.println(box.maximum());              // 40
        System.out.println(box.countGreaterThan(25));   // 2
        System.out.println(box.snapshot());             // [40, 10, 30]
    }
}