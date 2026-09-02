import java.util.LinkedList;

public class BookIsbnHashTable {
    static class BookEntry {
        String isbn;
        String title;
        BookEntry(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }
    }

    private LinkedList<BookEntry>[] table;
    private int size;
    private static final int CAPACITY = 11;

    @SuppressWarnings("unchecked")
    public BookIsbnHashTable() {
        table = new LinkedList[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
    }

    private int hash(String isbn) {
        return Math.abs(isbn.hashCode()) % table.length;
    }

    public void insertOrUpdate(String isbn, String title) {
        int idx = hash(isbn);
        for (BookEntry entry : table[idx]) {
            if (entry.isbn.equals(isbn)) {
                entry.title = title;
                return;
            }
        }
        table[idx].add(new BookEntry(isbn, title));
        size++;
    }

    public String search(String isbn) {
        int idx = hash(isbn);
        for (BookEntry entry : table[idx]) {
            if (entry.isbn.equals(isbn)) return entry.title;
        }
        return null;
    }

    public boolean delete(String isbn) {
        int idx = hash(isbn);
        for (BookEntry entry : table[idx]) {
            if (entry.isbn.equals(isbn)) {
                table[idx].remove(entry);
                size--;
                return true;
            }
        }
        return false;
    }

    public int size() { return size; }
    public double getLoadFactor() { return (double) size / table.length; }

    public void printBucketReport() {
        System.out.println("=== Bucket Report ===");
        for (int i = 0; i < table.length; i++) {
            System.out.printf("Bucket %d (size %d): ", i, table[i].size());
            for (BookEntry entry : table[i]) {
                System.out.print("[" + entry.isbn + ": " + entry.title + "] ");
            }
            System.out.println();
        }
        System.out.printf("Total Size: %d, Load Factor: %.2f%n", size, getLoadFactor());
    }

    public static void main(String[] args) {
        BookIsbnHashTable ht = new BookIsbnHashTable();
        ht.insertOrUpdate("978-0134685991", "Effective Java");
        ht.insertOrUpdate("978-0596009205", "Head First Design Patterns");
        ht.insertOrUpdate("978-0132350884", "Clean Code");
        ht.printBucketReport();
    }
}