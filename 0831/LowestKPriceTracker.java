import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class LowestKPriceTracker {
    public static List<Integer> getLowestKPrices(List<Integer> prices, int k) {
        if (prices == null || k <= 0) return new ArrayList<>();

        // 建立容量為 K 的 Max Heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (Integer price : prices) {
            if (price == null || price < 0) continue; // 忽略 null 與負數

            if (maxHeap.size() < k) {
                maxHeap.offer(price);
            } else if (price < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(price);
            }
        }

        List<Integer> result = new ArrayList<>(maxHeap);
        Collections.sort(result); // 依價格遞增排列
        return result;
    }

    public static void main(String[] args) {
        List<Integer> input = List.of(50, -5, 20, 10, 80, 5, 30, 15);
        System.out.println(getLowestKPrices(input, 3)); // [5, 10, 15]
    }
}