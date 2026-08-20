import java.util.Arrays;
import java.util.Objects;

public class GenericArrayTools {

    public static <T> int countMatches(T[] data, T target) {
        if (data == null) return 0;
        int count = 0;
        for (T item : data) {
            if (Objects.equals(item, target)) {
                count++;
            }
        }
        return count;
    }

    public static <T> T last(T[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        return data[data.length - 1];
    }

    public static <T> void swap(T[] data, int first, int second) {
        if (data == null) {
            System.out.println("陣列為 null，無法交換。");
            return;
        }
        if (first < 0 || first >= data.length || second < 0 || second >= data.length) {
            System.out.println("Index 不合法: first=" + first + ", second=" + second + " (陣列長度: " + data.length + ")");
            return;
        }
        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    public static void main(String[] args) {
        String[] words = {"Java", "C++", "Python", "Java", null};
        
        System.out.println("countMatches 'Java': " + countMatches(words, "Java"));
        System.out.println("countMatches null: " + countMatches(words, null));
        System.out.println("last element: " + last(words));

        Integer[] empty = new Integer[0];
        System.out.println("empty last: " + last(empty));
        System.out.println("null last: " + last((String[]) null));

        swap(words, 0, 2);
        System.out.println("交換 0 與 2 後: " + Arrays.toString(words));
        swap(words, -1, 3); // 測試不合法 index
    }
}