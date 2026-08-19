// DeliveryStrategySystem.java
interface DeliveryMethod {
    double calculateShippingFee(double weight);
    String getEstimateDescription();
}

class HomeDelivery implements DeliveryMethod {
    @Override
    public double calculateShippingFee(double weight) {
        // 宅配：基本運費 100 元，每公斤加收 10 元
        return 100 + (weight * 10);
    }

    @Override
    public String getEstimateDescription() {
        return "黑貓宅急便專人配送，預計 1~2 個工作天內送達指定地址。";
    }
}

class StorePickupDelivery implements DeliveryMethod {
    @Override
    public double calculateShippingFee(double weight) {
        // 超商取貨：均一價 60 元（限重 5kg 內）
        return 60.0;
    }

    @Override
    public String getEstimateDescription() {
        return "7-11 / 全家便利商店取貨，預計 2~3 天送達指定門市。";
    }
}

class SelfPickupDelivery implements DeliveryMethod {
    @Override
    public double calculateShippingFee(double weight) {
        // 實體門市自取：免運費
        return 0.0;
    }

    @Override
    public String getEstimateDescription() {
        return "倉庫/門市現場自取，訂單備妥後隨時可提貨。";
    }
}

class OrderService {
    // 使用 Composition 保存 DeliveryMethod
    private DeliveryMethod deliveryMethod;

    public OrderService(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void printDeliveryDetails(double packageWeight) {
        double fee = deliveryMethod.calculateShippingFee(packageWeight);
        String desc = deliveryMethod.getEstimateDescription();

        System.out.printf("包裹重量: %.2f kg | 配送運費: NT$ %.1f%n", packageWeight, fee);
        System.out.println("物流說明: " + desc);
        System.out.println("--------------------------------------------------");
    }
}

public class DeliveryStrategySystem {
    public static void main(String[] args) {
        System.out.println("=== 訂單配送方式切換與計算示範 ===");
        double packageWeight = 4.5;

        OrderService order = new OrderService(new HomeDelivery());
        System.out.println("【方案一：選擇宅配】");
        order.printDeliveryDetails(packageWeight);

        System.out.println("【方案二：切換超商取貨】");
        order.setDeliveryMethod(new StorePickupDelivery());
        order.printDeliveryDetails(packageWeight);

        System.out.println("【方案三：切換門市自取】");
        order.setDeliveryMethod(new SelfPickupDelivery());
        order.printDeliveryDetails(packageWeight);
    }
}