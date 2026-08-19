// FlexibleCheckoutSystem.java
// 1. 三種定價策略 (PricingPolicy)
interface PricingPolicy {
    double calculateFinalPrice(double originalPrice);
    String getPolicyName();
}

class StandardPricingPolicy implements PricingPolicy {
    @Override
    public double calculateFinalPrice(double originalPrice) {
        return originalPrice;
    }

    @Override
    public String getPolicyName() {
        return "原價計費";
    }
}

class VipDiscountPolicy implements PricingPolicy {
    @Override
    public double calculateFinalPrice(double originalPrice) {
        return originalPrice * 0.85; // VIP 八五折
    }

    @Override
    public String getPolicyName() {
        return "VIP 尊榮 85 折優惠";
    }
}

class ThresholdDiscountPolicy implements PricingPolicy {
    @Override
    public double calculateFinalPrice(double originalPrice) {
        if (originalPrice >= 2000) {
            return originalPrice - 300; // 滿 2000 折 300
        }
        return originalPrice;
    }

    @Override
    public String getPolicyName() {
        return "滿 2000 折 300 優惠";
    }
}

// 2. 三種通知管道 (NotificationChannel)
interface NotificationChannel {
    boolean sendNotification(String orderId, double finalPrice);
}

class EmailChannel implements NotificationChannel {
    @Override
    public boolean sendNotification(String orderId, double finalPrice) {
        System.out.println("[Email 通知] 訂單 " + orderId + " 結帳成功，應付金額 NT$ " + finalPrice);
        return true;
    }
}

class SmsChannel implements NotificationChannel {
    @Override
    public boolean sendNotification(String orderId, double finalPrice) {
        System.out.println("[SMS 簡訊] 您的訂單 " + orderId + " 金額 NT$ " + finalPrice + " 已扣款。");
        return true;
    }
}

class ConsoleChannel implements NotificationChannel {
    @Override
    public boolean sendNotification(String orderId, double finalPrice) {
        System.out.println("[系統 Log] 訂單記錄: ID=" + orderId + ", 實收=" + finalPrice);
        return true;
    }
}

// 3. 結帳結果物件
class CheckoutResult {
    private String orderId;
    private double originalPrice;
    private double finalPrice;
    private boolean notificationStatus;

    public CheckoutResult(String orderId, double originalPrice, double finalPrice, boolean notificationStatus) {
        this.orderId = orderId;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.notificationStatus = notificationStatus;
    }

    @Override
    public String toString() {
        return String.format("結帳結果 [訂單編號: %s | 原價: NT$ %.1f | 實付: NT$ %.1f | 通知狀態: %s]",
                orderId, originalPrice, finalPrice, (notificationStatus ? "成功" : "失敗"));
    }
}

// 4. 結帳主服務
class CheckoutService {
    public static CheckoutResult checkout(String orderId, double originalPrice, 
                                          PricingPolicy pricingPolicy, NotificationChannel channel) {
        double finalPrice = pricingPolicy.calculateFinalPrice(originalPrice);
        boolean status = channel.sendNotification(orderId, finalPrice);
        return new CheckoutResult(orderId, originalPrice, finalPrice, status);
    }
}

public class FlexibleCheckoutSystem {
    public static void main(String[] args) {
        PricingPolicy standard = new StandardPricingPolicy();
        PricingPolicy vip = new VipDiscountPolicy();
        PricingPolicy threshold = new ThresholdDiscountPolicy();

        NotificationChannel email = new EmailChannel();
        NotificationChannel sms = new SmsChannel();
        NotificationChannel console = new ConsoleChannel();

        System.out.println("=== 測試 6 種 定價策略 x 通知管道 組合 ===");

        // 組合 1: 原價 + Email
        CheckoutResult r1 = CheckoutService.checkout("ORD-001", 1500, standard, email);
        System.out.println(r1 + "\n");

        // 組合 2: 原價 + SMS
        CheckoutResult r2 = CheckoutService.checkout("ORD-002", 800, standard, sms);
        System.out.println(r2 + "\n");

        // 組合 3: VIP 85折 + SMS
        CheckoutResult r3 = CheckoutService.checkout("ORD-003", 3000, vip, sms);
        System.out.println(r3 + "\n");

        // 組合 4: VIP 85折 + Console
        CheckoutResult r4 = CheckoutService.checkout("ORD-004", 4500, vip, console);
        System.out.println(r4 + "\n");

        // 組合 5: 滿2000折300 + Email
        CheckoutResult r5 = CheckoutService.checkout("ORD-005", 2500, threshold, email);
        System.out.println(r5 + "\n");

        // 組合 6: 滿2000折300 + Console (未達門檻測試)
        CheckoutResult r6 = CheckoutService.checkout("ORD-006", 1800, threshold, console);
        System.out.println(r6 + "\n");
    }
}