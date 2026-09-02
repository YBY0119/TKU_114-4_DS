import java.util.*;

public class LoginActivityReport {
    static class LoginRecord {
        String username;
        String ip;
        LoginRecord(String username, String ip) {
            this.username = username;
            this.ip = ip;
        }
    }

    public static void analyzeLogins(List<LoginRecord> records, int alertThreshold) {
        Map<String, Integer> userCounts = new HashMap<>();
        Set<String> uniqueIPs = new HashSet<>();

        for (LoginRecord record : records) {
            userCounts.put(record.username, userCounts.getOrDefault(record.username, 0) + 1);
            uniqueIPs.add(record.ip);
        }

        System.out.println("=== 登入活動分析報告 ===");
        System.out.println("不重複 IP 總數: " + uniqueIPs.size());
        System.out.println("\n異常頻繁登入帳號 (次數 >= " + alertThreshold + "):");
        for (Map.Entry<String, Integer> entry : userCounts.entrySet()) {
            if (entry.getValue() >= alertThreshold) {
                System.out.println("- 帳號: " + entry.getKey() + "，登入次數: " + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {
        List<LoginRecord> logs = Arrays.asList(
            new LoginRecord("alice", "192.168.1.1"),
            new LoginRecord("bob", "192.168.1.2"),
            new LoginRecord("alice", "192.168.1.1"),
            new LoginRecord("alice", "192.168.1.3"),
            new LoginRecord("alice", "192.168.1.1"),
            new LoginRecord("charlie", "10.0.0.1")
        );
        analyzeLogins(logs, 3);
    }
}