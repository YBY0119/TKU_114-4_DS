class Account {
    private String id;
    private int balance;

    public Account(String id, int balance) {
        this.id = id;
        this.balance = Math.max(0, balance);
    }

    public String getId() { return id; }
    public int getBalance() { return balance; }

    public void deposit(int amount) { balance += amount; }
    public void withdraw(int amount) { balance -= amount; }

    @Override
    public String toString() {
        return String.format("Account[%s, 余额: %d]", id, balance);
    }
}

class TransferService {
    public static boolean transfer(Account source, Account target, int amount) {
        // 验证 1: 来源与目标不是 null
        if (source == null || target == null) return false;
        // 验证 2: 来源与目标不能是同一物件
        if (source == target) return false;
        // 验证 3: 金额大于 0 且来源余额足够
        if (amount <= 0 || source.getBalance() < amount) return false;

        // 验证全数通过才执行扣款与入帐
        source.withdraw(amount);
        target.deposit(amount);
        return true;
    }
}

public class AccountTransferService {
    public static void main(String[] args) {
        Account accA = new Account("A101", 1000);
        Account accB = new Account("B202", 500);

        System.out.println("初始状态: " + accA + " | " + accB);

        System.out.println("\n测试 1 - 成功转帐 300: " + TransferService.transfer(accA, accB, 300));
        System.out.println("状态: " + accA + " | " + accB);

        System.out.println("\n测试 2 - 余额不足转帐 2000: " + TransferService.transfer(accA, accB, 2000));
        System.out.println("状态: " + accA + " | " + accB);

        System.out.println("\n测试 3 - 同帐户转帐: " + TransferService.transfer(accA, accA, 100));
        System.out.println("状态: " + accA);

        System.out.println("\n测试 4 - 目标为 null: " + TransferService.transfer(accA, null, 100));
        System.out.println("状态: " + accA);
    }
}