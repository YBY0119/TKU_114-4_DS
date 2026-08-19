class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getName() { return name; }
    public String getId() { return id; }
}

class OrderItem {
    private String itemName;
    private double price;
    private int quantity;

    public OrderItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public double getSubtotal() { return price * quantity; }
    public int getQuantity() { return quantity; }
    public String getItemName() { return itemName; }
}

class CustomerOrder {
    private Customer customer;
    private OrderItem[] items;

    public CustomerOrder(Customer customer, OrderItem[] items) {
        this.customer = customer;
        this.items = (items != null) ? items : new OrderItem[0];
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            if (item != null) total += item.getSubtotal();
        }
        return total;
    }

    public int getTotalQuantity() {
        int totalQty = 0;
        for (OrderItem item : items) {
            if (item != null) totalQty += item.getQuantity();
        }
        return totalQty;
    }

    public void printSummary() {
        System.out.println("=== 订单摘要 ===");
        System.out.printf("顾客: %s (%s)\n", customer.getName(), customer.getId());
        System.out.println("品项明细:");
        for (OrderItem item : items) {
            if (item != null) {
                System.out.printf(" - %s x%d = %.1f\n", item.getItemName(), item.getQuantity(), item.getSubtotal());
            }
        }
        System.out.println("总品项数量: " + getTotalQuantity());
        System.out.printf("订单总金额: %.1f\n", calculateTotal());
    }
}

public class CustomerOrderSystem {
    public static void main(String[] args) {
        Customer cust = new Customer("C001", "Charlie");
        OrderItem[] items = {
            new OrderItem("键盘", 1200, 1),
            new OrderItem("滑鼠", 600, 2),
            new OrderItem("滑鼠垫", 150, 1)
        };

        CustomerOrder order = new CustomerOrder(cust, items);
        order.printSummary();
    }
}