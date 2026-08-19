class Book {
    private String bookId;
    private String title;
    private double price;
    private int stock;

    public Book(String bookId, String title, double price, int stock) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.stock = stock;
    }

    public double getPrice() { return price; }
    public int getStock() { return stock; }

    @Override
    public String toString() {
        return String.format("书号: %s, 书名: %-15s, 价格: %6.1f, 库存: %d", bookId, title, price, stock);
    }
}

public class BookArrayReport {
    public static void main(String[] args) {
        Book[] books = {
            new Book("B01", "Java 程式设计", 580.0, 10),
            new Book("B02", "资料结构导论", 650.0, 2),
            new Book("B03", "演算法图解", 420.0, 5),
            new Book("B04", "作业系统原理", 720.0, 1)
        };

        System.out.println("=== 1. 所有书籍列表 ===");
        double totalValue = 0;
        Book highestPriceBook = books[0];

        for (Book b : books) {
            System.out.println(b);
            totalValue += b.getPrice() * b.getStock();
            if (b.getPrice() > highestPriceBook.getPrice()) {
                highestPriceBook = b;
            }
        }

        System.out.printf("\n=== 2. 库存总价值: %.1f ===\n", totalValue);
        
        System.out.println("\n=== 3. 价格最高的书籍 ===");
        System.out.println(highestPriceBook);

        System.out.println("\n=== 4. 库存小于等于 3 的书籍 ===");
        for (Book b : books) {
            if (b.getStock() <= 3) {
                System.out.println(b);
            }
        }
    }
}