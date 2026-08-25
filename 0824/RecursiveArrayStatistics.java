public class RecursiveArrayStatistics {

    // 計算最大值 (Wrapper)
    public static int maximum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        return maximumHelper(arr, 0);
    }

    private static int maximumHelper(int[] arr, int index) {
        if (index == arr.length - 1) return arr[index];
        return Math.max(arr[index], maximumHelper(arr, index + 1));
    }

    // 計算最小值 (Wrapper)
    public static int minimum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        return minimumHelper(arr, 0);
    }

    private static int minimumHelper(int[] arr, int index) {
        if (index == arr.length - 1) return arr[index];
        return Math.min(arr[index], minimumHelper(arr, index + 1));
    }

    // 計算大於 threshold 的元素個數 (Wrapper)
    public static int countAbove(int[] arr, int threshold) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        return countAboveHelper(arr, threshold, 0);
    }

    private static int countAboveHelper(int[] arr, int threshold, int index) {
        if (index == arr.length) return 0;
        int count = (arr[index] > threshold) ? 1 : 0;
        return count + countAboveHelper(arr, threshold, index + 1);
    }

    public static void main(String[] args) {
        int[] data = {12, 45, 7, 89, 23, 56};
        System.out.println("Max: " + maximum(data));
        System.out.println("Min: " + minimum(data));
        System.out.println("Count > 30: " + countAbove(data, 30));

        // 異常處理測試
        try {
            maximum(new int[]{});
        } catch (IllegalArgumentException e) {
            System.out.println("成功捕捉空陣列異常: " + e.getMessage());
        }
    }
}