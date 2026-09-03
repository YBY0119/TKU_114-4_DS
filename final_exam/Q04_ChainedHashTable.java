import java.util.*;

public class Q04_ChainedHashTable {
    private static class Entry {
        int key;
        String value;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private final List<List<Entry>> buckets;
    private final int bucketCount;
    private int size = 0;

    public Q04_ChainedHashTable(int bucketCount) {
        if (bucketCount <= 0) {
            throw new IllegalArgumentException("Bucket count must be greater than 0");
        }
        this.bucketCount = bucketCount;
        this.buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    private int getBucketIndex(int key) {
        return Math.floorMod(key, bucketCount);
    }

    public void put(int key, String value) {
        int idx = getBucketIndex(key);
        List<Entry> chain = buckets.get(idx);

        for (Entry entry : chain) {
            if (entry.key == key) {
                entry.value = value;
                return;
            }
        }

        chain.add(new Entry(key, value));
        size++;
    }

    public String get(int key) {
        int idx = getBucketIndex(key);
        List<Entry> chain = buckets.get(idx);

        for (Entry entry : chain) {
            if (entry.key == key) {
                return entry.value;
            }
        }
        return null;
    }

    public boolean remove(int key) {
        int idx = getBucketIndex(key);
        List<Entry> chain = buckets.get(idx);

        Iterator<Entry> it = chain.iterator();
        while (it.hasNext()) {
            Entry entry = it.next();
            if (entry.key == key) {
                it.remove();
                size--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public int longestChain() {
        int max = 0;
        for (List<Entry> chain : buckets) {
            max = Math.max(max, chain.size());
        }
        return max;
    }

    public static void main(String[] args) {
        Q04_ChainedHashTable table = new Q04_ChainedHashTable(5);
        table.put(1, "One");
        table.put(-4, "Minus Four (collision with 1)");
        table.put(6, "Six (collision with 1)");
        table.put(1, "Updated One");

        System.out.println("Get -4: " + table.get(-4)); // 預期: Minus Four (collision with 1)
        System.out.println("Get 1: " + table.get(1));   // 預期: Updated One
        System.out.println("Size: " + table.size());     // 預期: 3
        System.out.println("Longest Chain: " + table.longestChain()); // 預期: 3
    }
}