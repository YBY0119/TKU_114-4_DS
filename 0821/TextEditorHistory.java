import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {
    private final Deque<String> undoStack = new ArrayDeque<>();
    private final Deque<String> redoStack = new ArrayDeque<>();

    public void execute(String action) {
        undoStack.push(action);
        redoStack.clear(); // 新增操作清空 redo
        printStatus("Execute [" + action + "]");
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            printStatus("Undo (Failed: Undo stack is empty)");
            return;
        }
        String action = undoStack.pop();
        redoStack.push(action);
        printStatus("Undo [" + action + "]");
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            printStatus("Redo (Failed: Redo stack is empty)");
            return;
        }
        String action = redoStack.pop();
        undoStack.push(action);
        printStatus("Redo [" + action + "]");
    }

    public void printStatus(String stepName) {
        System.out.println("Step: " + stepName);
        System.out.println("  Undo Stack: " + undoStack);
        System.out.println("  Redo Stack: " + redoStack);
        System.out.println("----------------------------------------");
    }

    public static void main(String[] args) {
        TextEditorHistory editor = new TextEditorHistory();

        // 測試正常流程與空 stack 處理
        editor.undo(); // 空 undo
        editor.redo(); // 空 redo
        editor.execute("Type Hello");
        editor.execute("Type World");
        editor.execute("Bold Text");
        editor.undo();
        editor.undo();
        editor.redo();
        editor.execute("Italic Text"); // 觸發清空 redo
        editor.redo(); // redo 應為空
    }
}