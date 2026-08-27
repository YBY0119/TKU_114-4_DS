import java.util.*;

public class Q07_RequestPipeline {

    public static boolean isBalanced(String text) {
        if (text == null) {
            return false;
        }
        if (text.isEmpty()) {
            return true;
        }

        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static List<String> process(String[] commands) {
        if (commands == null) {
            return Collections.emptyList();
        }

        Deque<String> normalQueue = new ArrayDeque<>();
        Deque<String> urgentQueue = new ArrayDeque<>();
        List<String> result = new ArrayList<>();

        for (String cmd : commands) {
            if (cmd == null || cmd.trim().isEmpty()) {
                continue;
            }
            String trimmed = cmd.trim();
            if (trimmed.equals("PROCESS")) {
                if (!urgentQueue.isEmpty()) {
                    result.add(urgentQueue.pollFirst());
                } else if (!normalQueue.isEmpty()) {
                    result.add(normalQueue.pollFirst());
                } else {
                    result.add("EMPTY");
                }
            } else if (trimmed.startsWith("NORMAL ")) {
                String id = trimmed.substring(7).trim();
                if (!id.isEmpty()) {
                    normalQueue.addLast(id);
                }
            } else if (trimmed.startsWith("URGENT ")) {
                String id = trimmed.substring(7).trim();
                if (!id.isEmpty()) {
                    urgentQueue.addLast(id);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] commands = {
            "NORMAL N1", "URGENT U1", "NORMAL N2", "PROCESS", "PROCESS", "PROCESS"
        };
        System.out.println(Q07_RequestPipeline.isBalanced("a{b[c](d)}")); // true
        System.out.println(Q07_RequestPipeline.isBalanced("([)]"));         // false
        System.out.println(Q07_RequestPipeline.process(commands));          // [U1, N1, N2]
    }
}