import java.util.List;

public class HeapPropertyValidator {
    public static boolean isMinHeap(List<Integer> list) {
        if (list == null) return false;
        if (list.size() <= 1) return true;

        int n = list.size();
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && list.get(i) > list.get(left)) return false;
            if (right < n && list.get(i) > list.get(right)) return false;
        }
        return true;
    }

    public static boolean isMaxHeap(List<Integer> list) {
        if (list == null) return false;
        if (list.size() <= 1) return true;

        int n = list.size();
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && list.get(i) < list.get(left)) return false;
            if (right < n && list.get(i) < list.get(right)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> minList = List.of(10, 15, 20, 30, 40);
        List<Integer> maxList = List.of(50, 40, 30, 10, 20);

        System.out.println("isMinHeap: " + isMinHeap(minList)); // true
        System.out.println("isMaxHeap: " + isMaxHeap(maxList)); // true
    }
}