public class Q08_RecursiveAudit {

    public static int sumValid(int[] data, int index) {
        if (data == null) {
            return 0;
        }
        if (index < 0) {
            index = 0;
        }
        if (index >= data.length) {
            return 0;
        }

        int current = data[index];
        int val = (current >= 0 && current <= 100) ? current : 0;
        return val + sumValid(data, index + 1);
    }

    public static int countOccurrences(int[] data, int index, int target) {
        if (data == null) {
            return 0;
        }
        if (index < 0) {
            index = 0;
        }
        if (index >= data.length) {
            return 0;
        }

        int match = (data[index] == target) ? 1 : 0;
        return match + countOccurrences(data, index + 1, target);
    }

    public static boolean isPalindrome(String text, int left, int right) {
        if (text == null) {
            return false;
        }
        if (left >= right) {
            return true;
        }

        char cLeft = Character.toLowerCase(text.charAt(left));
        char cRight = Character.toLowerCase(text.charAt(right));

        if (cLeft != cRight) {
            return false;
        }
        return isPalindrome(text, left + 1, right - 1);
    }

    public static void main(String[] args) {
        int[] data = {10, -1, 20, 101, 20};
        System.out.println(Q08_RecursiveAudit.sumValid(data, 0));                // 50
        System.out.println(Q08_RecursiveAudit.countOccurrences(data, 0, 20));    // 2
        System.out.println(Q08_RecursiveAudit.isPalindrome("Level", 0, 4));      // true
    }
}