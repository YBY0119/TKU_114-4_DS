import java.util.ArrayList;
import java.util.List;

class Repository<T> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public T get(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public boolean remove(T item) {
        return items.remove(item);
    }

    public int size() {
        return items.size();
    }

    public void printAll() {
        System.out.println(items);
    }
}

class Product {
    private final String name;
    private final int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

public class GenericRepositorySystem {
    public static void main(String[] args) {
        // 測試 Repository<String>
        Repository<String> strRepo = new Repository<>();
        strRepo.add("Java");
        strRepo.add("Spring");
        System.out.println("String Repo Size: " + strRepo.size());
        strRepo.printAll();
        strRepo.remove("Java");
        System.out.print("移除 Java 後: ");
        strRepo.printAll();

        // 測試 Repository<Product>
        Repository<Product> prodRepo = new Repository<>();
        Product p1 = new Product("筆電", 32000);
        Product p2 = new Product("滑鼠", 650);
        prodRepo.add(p1);
        prodRepo.add(p2);
        System.out.println("Product Repo Size: " + prodRepo.size());
        prodRepo.printAll();
    }
}