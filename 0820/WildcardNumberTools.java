import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WildcardNumberTools {

    public static double average(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Number num : values) {
            if (num != null) {
                sum += num.doubleValue();
            }
        }
        return sum / values.size();
    }

    public static double maximum(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return Double.NaN;
        }
        double max = -Double.MAX_VALUE;
        boolean hasValidNum = false;
        for (Number num : values) {
            if (num != null) {
                max = Math.max(max, num.doubleValue());
                hasValidNum = true;
            }
        }
        return hasValidNum ? max : Double.NaN;
    }

    public static void addRange(List<? super Integer> target, int start, int end) {
        if (target == null || start > end) {
            return;
        }
        for (int i = start; i <= end; i++) {
            target.add(i);
        }
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(10, 20, 30);
        List<Double> doubleList = Arrays.asList(12.5, 45.8, 3.2);
        List<Double> emptyList = new ArrayList<>();

        System.out.println("Integer List Average: " + average(intList));
        System.out.println("Double List Average: " + average(doubleList));
        System.out.println("Empty List Average: " + average(emptyList));

        System.out.println("Integer List Max: " + maximum(intList));
        System.out.println("Double List Max: " + maximum(doubleList));
        System.out.println("Empty List Max: " + maximum(emptyList));

        List<Number> numTarget = new ArrayList<>();
        addRange(numTarget, 5, 8);
        System.out.println("addRange (5 to 8): " + numTarget);

        addRange(numTarget, 10, 5); // start > end 不加入
        System.out.println("addRange (10 to 5, 應不變): " + numTarget);
    }
}