import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q04_NotificationRouter {
    public interface Channel {
        String name();
        boolean supports(String destination);
        String send(String destination, String message);
    }

    public static class EmailChannel implements Channel {
        @Override
        public String name() {
            return "EMAIL";
        }

        @Override
        public boolean supports(String destination) {
            if (destination == null) {
                return false;
            }
            int atIndex = destination.indexOf('@');
            // 包含 @，且不在開頭 (atIndex > 0) 也不在結尾 (atIndex < length - 1)
            return atIndex > 0 && atIndex < destination.length() - 1;
        }

        @Override
        public String send(String destination, String message) {
            return name() + "|" + destination + "|" + message;
        }
    }

    public static class SmsChannel implements Channel {
        @Override
        public String name() {
            return "SMS";
        }

        @Override
        public boolean supports(String destination) {
            if (destination == null) {
                return false;
            }
            // 去除 '-' 符號
            String stripped = destination.replace("-", "");
            // 檢查是否恰好為 10 個數字
            return stripped.matches("\\d{10}");
        }

        @Override
        public String send(String destination, String message) {
            return name() + "|" + destination + "|" + message;
        }
    }

    public static List<String> route(List<Channel> channels, String destination, String message) {
        if (channels == null || destination == null || message == null) {
            return Collections.emptyList();
        }

        List<String> results = new ArrayList<>();
        for (Channel ch : channels) {
            if (ch != null && ch.supports(destination)) {
                results.add(ch.send(destination, message));
            }
        }
        return results;
    }

    public static void main(String[] args) {
        var channels = List.of(
            new Q04_NotificationRouter.EmailChannel(),
            new Q04_NotificationRouter.SmsChannel()
        );
        System.out.println(Q04_NotificationRouter.route(channels, "a@b.com", "Ready"));        // [EMAIL|a@b.com|Ready]
        System.out.println(Q04_NotificationRouter.route(channels, "0912-345-678", "Go"));     // [SMS|0912-345-678|Go]
    }
}