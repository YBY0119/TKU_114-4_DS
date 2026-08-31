import java.util.ArrayList;
import java.util.List;

public class StudentIdHashAnalysis {

    public static void analyze(List<String> studentIds, int bucketCount) {
        List<List<String>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (String id : studentIds) {
            int hash = Math.floorMod(id.hashCode(), bucketCount);
            buckets.get(hash).add(id);
        }

        int totalCollisions = 0;
        int maxChain = 0;
        int totalElements = 0;

        System.out.println("--- 分析結果 (Bucket Count = " + bucketCount + ") ---");
        for (int i = 0; i < bucketCount; i++) {
            int chainLen = buckets.get(i).size();
            totalElements += chainLen;
            if (chainLen > 1) {
                totalCollisions += (chainLen - 1);
            }
            if (chainLen > maxChain) {
                maxChain = chainLen;
            }
            System.out.println("Bucket " + i + " 筆數: " + chainLen + " -> " + buckets.get(i));
        }

        double avgChain = (double) totalElements / bucketCount;
        System.out.println("總碰撞次數 (Total Collisions): " + totalCollisions);
        System.out.println("最大 Chain 長度 (Max Chain): " + maxChain);
        System.out.printf("平均 Chain 長度 (Avg Chain): %.2f\n\n", avgChain);
    }

    public static void main(String[] args) {
        List<String> studentIds = List.of(
            "11001", "11002", "11003", "11004", "11005",
            "11011", "11012", "11013", "11021", "11031"
        );

        int bucketCount1 = 5;
        int bucketCount2 = 11;

        analyze(studentIds, bucketCount1);
        analyze(studentIds, bucketCount2);
    }
}