import java.util.*;

public class WordIndexSystem {
    public static void main(String[] args) {
        String[] sentences = {
            "Hello world, welcome to Java.",
            "Java is powerful, and Java is versatile.",
            "Hello, welcome back!"
        };

        Map<String, Integer> wordCountMap = new HashMap<>();
        Set<String> uniqueWords = new HashSet<>();

        for (String sentence : sentences) {
            // 移除標點符號並轉為小寫
            String cleaned = sentence.replaceAll("[.,!?]", "").toLowerCase();
            String[] words = cleaned.split("\\s+");

            for (String w : words) {
                if (w.isEmpty()) continue;
                uniqueWords.add(w);
                wordCountMap.put(w, wordCountMap.getOrDefault(w, 0) + 1);
            }
        }

        System.out.println("=== 所有不重複單字 (Set) ===");
        System.out.println(uniqueWords);

        System.out.println("\n=== 單字出現次數 (Map) ===");
        wordCountMap.forEach((k, v) -> System.out.println(k + ": " + v));

        System.out.println("\n=== 出現至少兩次的單字 ===");
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            if (entry.getValue() >= 2) {
                System.out.println(entry.getKey() + " -> " + entry.getValue() + " 次");
            }
        }
    }
}