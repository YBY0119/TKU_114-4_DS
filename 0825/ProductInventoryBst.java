public class ProductInventoryBst {
    static class Product {
        int id;
        String name;
        int stock;

        Product(int id, String name, int stock) {
            this.id = id;
            this.name = name;
            this.stock = stock;
        }

        @Override
        public String toString() {
            return String.format("[ID: %d | 名稱: %s | 庫存: %d]", id, name, stock);
        }
    }

    static class Node {
        Product product;
        Node left, right;
        Node(Product p) { this.product = p; }
    }

    private Node root;

    public void addProduct(int id, String name, int stock) {
        root = insertRec(root, new Product(id, name, stock));
    }

    private Node insertRec(Node node, Product p) {
        if (node == null) return new Node(p);
        if (p.id < node.product.id) node.left = insertRec(node.left, p);
        else if (p.id > node.product.id) node.right = insertRec(node.right, p);
        else System.out.println("商品 ID " + p.id + " 已存在！");
        return node;
    }

    public Product find(int id) {
        Node cur = root;
        while (cur != null) {
            if (id == cur.product.id) return cur.product;
            else if (id < cur.product.id) cur = cur.left;
            else cur = cur.right;
        }
        return null;
    }

    public void restock(int id, int amount) {
        Product p = find(id);
        if (p != null) {
            p.stock += amount;
            System.out.println("補貨成功: " + p);
        } else {
            System.out.println("找不到商品 ID: " + id);
        }
    }

    public void deductStock(int id, int amount) {
        Product p = find(id);
        if (p != null) {
            if (p.stock >= amount) {
                p.stock -= amount;
                System.out.println("扣庫存成功: " + p);
            } else {
                System.out.println("庫存不足！當前庫存: " + p.stock);
            }
        } else {
            System.out.println("找不到商品 ID: " + id);
        }
    }

    public void delete(int id) {
        root = deleteRec(root, id);
    }

    private Node deleteRec(Node node, int id) {
        if (node == null) return null;
        if (id < node.product.id) node.left = deleteRec(node.left, id);
        else if (id > node.product.id) node.right = deleteRec(node.right, id);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node minNode = getMin(node.right);
            node.product = minNode.product;
            node.right = deleteRec(node.right, minNode.product.id);
        }
        return node;
    }

    private Node getMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public void inorderReport() {
        System.out.println("=== 庫存報表 (依 ID 排序) ===");
        inorderRec(root);
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.println(node.product);
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        ProductInventoryBst inv = new ProductInventoryBst();
        inv.addProduct(103, "鍵盤", 10);
        inv.addProduct(101, "滑鼠", 20);
        inv.addProduct(105, "螢幕", 5);

        inv.restock(101, 10);
        inv.deductStock(103, 3);
        inv.delete(105);

        inv.inorderReport();
    }
}