// MessageSenderSystem.java
interface MessageSender {
    void send(String receiver, String message);
}

class EmailSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        System.out.println("[Email] 寄送至 <" + receiver + ">: " + message);
    }
}

class SmsSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        System.out.println("[SMS 簡訊] 發送至 +" + receiver + ": " + message);
    }
}

class ConsoleSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        System.out.println("[主控台日誌] 接收端 [" + receiver + "] -> 訊息: " + message);
    }
}

public class MessageSenderSystem {
    // notify 只依賴 MessageSender 介面，新增 sender 時無需修改此方法
    public static void notify(MessageSender sender, String receiver, String message) {
        // 處理空白 receiver 或 message
        if (receiver == null || receiver.trim().isEmpty()) {
            System.out.println("❌ 發送失敗：接收者 (receiver) 不可為空！");
            return;
        }
        if (message == null || message.trim().isEmpty()) {
            System.out.println("❌ 發送失敗：發送內容 (message) 不可為空！");
            return;
        }
        sender.send(receiver.trim(), message.trim());
    }

    public static void main(String[] args) {
        MessageSender email = new EmailSender();
        MessageSender sms = new SmsSender();
        MessageSender console = new ConsoleSender();

        System.out.println("=== 訊息發送測試 ===");
        notify(email, "student@mail.tku.edu.tw", "您的作業已成功繳交。");
        notify(sms, "886912345678", "驗證碼為 849201。");
        notify(console, "AdminUser", "系統定期備份完成。");

        System.out.println("\n=== 邊界條件與空白驗證測試 ===");
        notify(email, "", "測試無效接收者");
        notify(sms, "886987654321", "   ");
        notify(console, null, "測試 null 接收者");
    }
}