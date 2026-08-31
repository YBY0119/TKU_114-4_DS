import java.util.ArrayList;
import java.util.List;

public class CollisionBucketReport {
    public static void generateReport(int[] keys, int bucketCount) {
        if (bucketCount <= 0) return;

        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        if (keys != null) {
            for (int key : keys) {
                int index = Math.floorMod(key, bucketCount); // 處理負數 key
                buckets.get(index).add(key);
            }
        }

        int totalCollisions = 0;
        int maxChain = 0;

        for (int i = 0; i < bucketCount; i++) {
            List<Integer> b = buckets.get(i);
            System.out.println("Bucket " + i + ": " + b);
            if (b.size() > 1) {
                totalCollisions += (b.size() - 1);
            }
            if (b.size() > maxChain) {
                maxChain = b.size();
            }
        }

        System.out.println("Total collisions: " + totalCollisions);
        System.out.println("Max chain length: " + maxChain);
    }

    public static void main(String[] args) {
        int[] keys = {10, -5, 15, 20, 25, 10, -15};
        generateReport(keys, 5);
    }
}