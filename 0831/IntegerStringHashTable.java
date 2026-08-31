import java.util.LinkedList;

public class IntegerStringHashTable {
    static class Entry {
        int key;
        String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry>[] buckets;
    private int size;
    private static final int DEFAULT_CAPACITY = 11;

    @SuppressWarnings("unchecked")
    public IntegerStringHashTable(int capacity) {
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
        size = 0;
    }

    public IntegerStringHashTable() {
        this(DEFAULT_CAPACITY);
    }

    private int hash(int key) {
        return Math.floorMod(key, buckets.length);
    }

    public void put(int key, String value) {
        int idx = hash(key);
        for (Entry entry : buckets[idx]) {
            if (entry.key == key) {
                entry.value = value; // key 存在則更新，size 不增加
                return;
            }
        }
        buckets[idx].add(new Entry(key, value));
        size++;
    }

    public String get(int key) {
        int idx = hash(key);
        for (Entry entry : buckets[idx]) {
            if (entry.key == key) return entry.value;
        }
        return null;
    }

    public boolean containsKey(int key) {
        int idx = hash(key);
        for (Entry entry : buckets[idx]) {
            if (entry.key == key) return true;
        }
        return false;
    }

    public boolean remove(int key) {
        int idx = hash(key);
        for (Entry entry : buckets[idx]) {
            if (entry.key == key) {
                buckets[idx].remove(entry);
                size--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void bucketReport() {
        System.out.println("=== Bucket Report (Size: " + size + ") ===");
        for (int i = 0; i < buckets.length; i++) {
            System.out.print("Bucket [" + i + "]: ");
            for (Entry entry : buckets[i]) {
                System.out.print("(" + entry.key + " -> " + entry.value + ") ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        IntegerStringHashTable ht = new IntegerStringHashTable(5);
        ht.put(1, "Alice");
        ht.put(6, "Bob");
        ht.put(11, "Charlie");
        ht.put(1, "Updated_Alice");

        System.out.println("Size: " + ht.size()); // 3
        ht.bucketReport();
    }
}