import java.util.*;

class StoreProduct implements Comparable<StoreProduct> {
    private final int id;
    private final String name;
    private final double price;
    private final int stock;

    public StoreProduct(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    @Override
    public int compareTo(StoreProduct other) {
        return Integer.compare(this.id, other.id); // 自然順序：依 id 升冪
    }

    @Override
    public String toString() {
        return String.format("[ID: %d | %s | Price: %.1f | Stock: %d]", id, name, price, stock);
    }
}

public class ProductComparatorPractice {
    public static void main(String[] args) {
        List<StoreProduct> products = Arrays.asList(
            new StoreProduct(105, "Keyboard", 1200.0, 30),
            new StoreProduct(101, "Mouse", 800.0, 50),
            new StoreProduct(103, "Monitor", 4500.0, 10),
            new StoreProduct(102, "Headset", 800.0, 20),
            new StoreProduct(104, "Webcam", 1200.0, 10)
        );

        System.out.println("=== 原始順序 ===");
        products.forEach(System.out::println);

        // 1. 自然順序：依 id 升冪
        List<StoreProduct> naturalSorted = new ArrayList<>(products);
        Collections.sort(naturalSorted);
        System.out.println("\n=== 1. 自然順序 (依 id 升冪) ===");
        naturalSorted.forEach(System.out::println);

        // 2. Comparator 一：依 price 升冪，同價時依 name 升冪
        List<StoreProduct> priceSorted = new ArrayList<>(products);
        priceSorted.sort(Comparator.comparingDouble(StoreProduct::getPrice)
                                   .thenComparing(StoreProduct::getName));
        System.out.println("\n=== 2. 依 price 升冪 (同價依 name) ===");
        priceSorted.forEach(System.out::println);

        // 3. Comparator 二：依 stock 降冪，同庫存時依 id 升冪
        List<StoreProduct> stockSorted = new ArrayList<>(products);
        stockSorted.sort(Comparator.comparingInt(StoreProduct::getStock).reversed()
                                   .thenComparingInt(StoreProduct::getId));
        System.out.println("\n=== 3. 依 stock 降冪 (同庫存依 id 升冪) ===");
        stockSorted.forEach(System.out::println);
    }
}