public class Q01_InventoryItem {
    private final String id;
    private final String name;
    private int stock;

    public Q01_InventoryItem(String id, String name, int stock) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("id 不得為 null 或空白");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name 不得為 null 或空白");
        }
        this.id = id.trim();
        this.name = name.trim();
        this.stock = Math.max(stock, 0);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public boolean restock(int amount) {
        if (amount > 0) {
            this.stock += amount;
            return true;
        }
        return false;
    }

    public boolean sell(int amount) {
        if (amount > 0 && this.stock >= amount) {
            this.stock -= amount;
            return true;
        }
        return false;
    }

    public String status() {
        return id + "|" + name + "|" + stock;
    }

    public static void main(String[] args) {
        Q01_InventoryItem item = new Q01_InventoryItem(" P100 ", " Keyboard ", 5);
        System.out.println(item.restock(3)); // true
        System.out.println(item.sell(6));    // true
        System.out.println(item.sell(3));    // false
        System.out.println(item.status());   // P100|Keyboard|2
    }
}