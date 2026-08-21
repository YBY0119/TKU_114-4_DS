import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserBackStack {
    private final Deque<String> history = new ArrayDeque<>();

    public void visit(String url) {
        history.push(url);
        System.out.println("Visited: " + url);
    }

    public String back() {
        if (history.isEmpty()) {
            System.out.println("Back failed: No history.");
            return null;
        }
        String popped = history.pop();
        System.out.println("Back from: " + popped);
        return popped;
    }

    public String current() {
        if (history.isEmpty()) {
            System.out.println("Current: None (Empty Stack)");
            return null;
        }
        System.out.println("Current: " + history.peek());
        return history.peek();
    }

    public static void main(String[] args) {
        BrowserBackStack browser = new BrowserBackStack();

        // 連續測試至少五個操作（包含空 Stack 不丟出例外）
        browser.current();               // 操作 1: 空 stack current
        browser.back();                  // 操作 2: 空 stack back
        browser.visit("google.com");    // 操作 3: visit
        browser.visit("github.com");    // 操作 4: visit
        browser.current();               // 操作 5: current
        browser.visit("stackoverflow.com"); // 操作 6
        browser.back();                  // 操作 7: back
        browser.current();               // 操作 8: current
    }
}