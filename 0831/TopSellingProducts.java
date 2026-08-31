import java.util.*;

public class TopSellingProducts {
    static class Product {
        String id;
        int sales;

        public Product(String id, int sales) {
            this.id = id;
            this.sales = sales;
        }
    }

    public static List<Product> getTopK(List<Product> inputList, int k) {
        if (inputList == null || k <= 0) return Collections.emptyList();

        // 1. 合併相同 ID 之銷量
        Map<String, Integer> mergedSales = new HashMap<>();
        for (Product p : inputList) {
            mergedSales.put(p.id, mergedSales.getOrDefault(p.id, 0) + p.sales);
        }

        // 2. 排序規則：銷量大優先；銷量相同時 id 字典序小優先
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : mergedSales.entrySet()) {
            productList.add(new Product(entry.getKey(), entry.getValue()));
        }

        productList.sort((a, b) -> {
            if (a.sales != b.sales) {
                return Integer.compare(b.sales, a.sales);
            }
            return a.id.compareTo(b.id);
        });

        // 3. 取前 K 筆
        int limit = Math.min(k, productList.size());
        return productList.subList(0, limit);
    }

    public static void main(String[] args) {
        List<Product> list = List.of(
            new Product("P100", 10),
            new Product("P200", 25),
            new Product("P100", 15),
            new Product("P300", 25),
            new Product("P400", 5)
        );

        List<Product> topK = getTopK(list, 2);
        for (Product p : topK) {
            System.out.println(p.id + ": " + p.sales);
        }
    }
}