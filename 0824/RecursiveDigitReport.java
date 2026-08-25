public class RecursiveDigitReport {

    // 計算所有位數總和
    public static int digitSum(int n) {
        if (n < 0) return digitSum(-n);
        if (n < 10) return n;
        return (n % 10) + digitSum(n / 10);
    }

    // 計算總位數（含 0 回傳 1）
    public static int digitCount(int n) {
        if (n < 0) return digitCount(-n);
        if (n < 10) return 1;
        return 1 + digitCount(n / 10);
    }

    // 計算特定數字 (target) 出現的次數
    public static int countDigit(int n, int target) {
        if (n < 0) return countDigit(-n, target);
        if (n < 10) return (n == target) ? 1 : 0;
        return ((n % 10 == target) ? 1 : 0) + countDigit(n / 10, target);
    }

    public static void main(String[] args) {
        int[] testCases = {50205, 0, -731};
        for (int num : testCases) {
            System.out.println("--- 測試數字: " + num + " ---");
            System.out.println("digitSum: " + digitSum(num));
            System.out.println("digitCount: " + digitCount(num));
            System.out.println("countDigit (找 0): " + countDigit(num, 0));
            System.out.println("countDigit (找 5): " + countDigit(num, 5));
            System.out.println();
        }
    }
}