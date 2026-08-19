class DigitalWallet {
    private String walletId;
    private String owner;
    private double balance;
    private int transactionCount;

    public DigitalWallet(String walletId, String owner, double balance) {
        this.walletId = walletId;
        this.owner = owner;
        this.balance = Math.max(0, balance);
        this.transactionCount = 0;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) return false;
        balance += amount;
        transactionCount++;
        return true;
    }

    public boolean pay(double amount) {
        if (amount <= 0 || balance < amount) return false;
        balance -= amount;
        transactionCount++;
        return true;
    }

    public boolean refund(double amount) {
        if (amount <= 0) return false;
        balance += amount;
        transactionCount++;
        return true;
    }

    public double getBalance() { return balance; }
    public int getTransactionCount() { return transactionCount; }

    @Override
    public String toString() {
        return String.format("Wallet[%s, 持有人: %s, 余额: %.1f, 交易次数: %d]", 
                walletId, owner, balance, transactionCount);
    }
}

public class DigitalWalletSystem {
    public static void main(String[] args) {
        DigitalWallet wallet = new DigitalWallet("W100", "Bob", 500);
        System.out.println("初始状态: " + wallet);

        System.out.println("正常储值 200: " + wallet.deposit(200));
        System.out.println("正常付款 300: " + wallet.pay(300));
        System.out.println("余额不足付款 1000: " + wallet.pay(1000)); // 应失败
        System.out.println("负数金额付款 -50: " + wallet.pay(-50));     // 应失败
        System.out.println("退款 100: " + wallet.refund(100));

        System.out.println("最终状态: " + wallet);
    }
}