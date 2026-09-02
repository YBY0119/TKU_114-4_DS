import java.util.LinkedList;

public class ResizableStringMap {
    static class Entry {
        String key;
        String value;
        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry>[] buckets;
    private int size;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    @SuppressWarnings("unchecked")
    public ResizableStringMap(int capacity) {
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
        size = 0;
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public void put(String key, String value) {
        int index = hash(key);
        for (Entry entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        buckets[index].add(new Entry(key, value));
        size++;

        if ((double) size / buckets.length > LOAD_FACTOR_THRESHOLD) {
            resize();
        }
    }

    public String get(String key) {
        int index = hash(key);
        for (Entry entry : buckets[index]) {
            if (entry.key.equals(key)) return entry.value;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = buckets.length * 2 + 1;
        LinkedList<Entry>[] oldBuckets = buckets;
        buckets = new LinkedList[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            buckets[i] = new LinkedList<>();
        }
        size = 0;
        for (LinkedList<Entry> bucket : oldBuckets) {
            for (Entry entry : bucket) {
                put(entry.key, entry.value);
            }
        }
    }

    public int size() { return size; }
    public int getBucketCount() { return buckets.length; }

    public static void main(String[] args) {
        ResizableStringMap map = new ResizableStringMap(3);
        map.put("A", "Alpha");
        map.put("B", "Beta");
        map.put("C", "Gamma");
        System.out.println("Buckets after 3 inserts: " + map.getBucketCount());
        map.put("D", "Delta"); // 觸發 resize (4 / 3 > 0.75 -> capacity = 3*2+1 = 7)
        System.out.println("Buckets after resize: " + map.getBucketCount());
        System.out.println("Get B: " + map.get("B"));
    }
}