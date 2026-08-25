public class RecursiveTextTools {

    // 遞迴字串反轉
    public static String reverse(String s) {
        if (s == null) return null;
        if (s.length() <= 1) return s;
        return reverse(s.substring(1)) + s.charAt(0);
    }

    // 回文檢查（忽略大小寫與空白）
    public static boolean isPalindrome(String s) {
        if (s == null) return false;
        // 預處理：轉小寫並移除非字母數字（或去除空白）
        String cleaned = s.replaceAll("\\s+", "").toLowerCase();
        return isPalindromeHelper(cleaned, 0, cleaned.length() - 1);
    }

    private static boolean isPalindromeHelper(String s, int left, int right) {
        if (left >= right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return isPalindromeHelper(s, left + 1, right - 1);
    }

    // 遞迴計算特定字元出現次數
    public static int countCharacter(String s, char target) {
        if (s == null || s.isEmpty()) return 0;
        int count = (s.charAt(0) == target) ? 1 : 0;
        return count + countCharacter(s.substring(1), target);
    }

    public static void main(String[] args) {
        String[] testStrings = {"", "a", "Level", "Race car", "hello"};

        System.out.println("=== 遞迴字串工具測試 ===");
        for (String str : testStrings) {
            System.out.println("原字串: \"" + str + "\"");
            System.out.println("  reverse: \"" + reverse(str) + "\"");
            System.out.println("  isPalindrome: " + isPalindrome(str));
            System.out.println("  countCharacter ('e'): " + countCharacter(str, 'e'));
            System.out.println();
        }
    }
}