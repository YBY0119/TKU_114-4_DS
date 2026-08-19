class WalletTransaction {
    private int sequence;
    private String type; // "DEPOSIT", "WITHDRAW", "TRANSFER_IN", "TRANSFER_OUT"
    private double amount;

    public WalletTransaction(int sequence, String type, double amount) {
        this.sequence = sequence;
        this.type = type;
        this.amount = amount;
    }

    public int getSequence() { return sequence; }
    public String getType() { return type; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return String.format("#%d [%s] 金额: %.1f", sequence, type, amount);
    }
}

class WalletTransactionSystem {
    private String walletId;
    private double balance;
    private WalletTransaction[] history;
    private int transactionCount;

    public WalletTransactionSystem(String walletId, double balance, int maxHistoryCapacity) {
        this.walletId = walletId;
        this.balance = Math.max(0, balance);
        this.history = new WalletTransaction[maxHistoryCapacity];
        this.transactionCount = 0;
    }

    public double getBalance() { return balance; }

    private boolean addTransaction(String type, double amount) {
        // 阵列满时不得修改余额
        if (transactionCount >= history.length) {
            return false;
        }
        history[transactionCount] = new WalletTransaction(transactionCount + 1, type, amount);
        transactionCount++;
        return true;
    }

    public boolean deposit(double amount) {
        if (amount <= 0 || transactionCount >= history.length) return false;
        balance += amount;
        addTransaction("DEPOSIT", amount);
        return true;
    }

    public boolean transferTo(WalletTransactionSystem target, double amount) {
        if (target == null || target == this || amount <= 0 || balance < amount) return false;
        // 必须确保两者的交易阵列都未满
        if (this.transactionCount >= this.history.length || target.transactionCount >= target.history.length) {
            return false;
        }

        this.balance -= amount;
        target.balance += amount;

        this.addTransaction("TRANSFER_OUT", amount);
        target.addTransaction("TRANSFER_IN", amount);
        return true;
    }

    public WalletTransaction findTransaction(int sequence) {
        for (int i = 0; i < transactionCount; i++) {
            if (history[i].getSequence() == sequence) {
                return history[i];
            }
        }
        return null;
    }

    public double totalByType(String type) {
        double total = 0;
        for (int i = 0; i < transactionCount; i++) {
            if (history[i].getType().equalsIgnoreCase(type)) {
                total += history[i].getAmount();
            }
        }
        return total;
    }

    public void printStatement() {
        System.out.printf("=== 钱包历史对账单 [%s] (当前余额: %.1f) ===\n", walletId, balance);
        for (int i = 0; i < transactionCount; i++) {
            System.out.println(history[i]);
        }
    }
}

public class WalletHistoryManager {
    public static void main(String[] args) {
        WalletTransactionSystem w1 = new WalletTransactionSystem("W01", 1000, 5);
        WalletTransactionSystem w2 = new WalletTransactionSystem("W02", 500, 5);

        w1.deposit(500);
        w1.transferTo(w2, 300);

        System.out.println("=== w1 的交易纪录 ===");
        w1.printStatement();

        System.out.println("\n=== w2 的交易纪录 ===");
        w2.printStatement();

        System.out.println("\n=== 查询测试 ===");
        System.out.println("w1 寻找序列号 2 的交易: " + w1.findTransaction(2));
        System.out.println("w1 寻找序列号 99 的交易: " + w1.findTransaction(99)); // 应为 null
        System.out.println("w1 TRANSFER_OUT 类型的总金额: " + w1.totalByType("TRANSFER_OUT"));
    }
}