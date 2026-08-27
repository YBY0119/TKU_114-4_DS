public class OrderBstSystem {
    static class Order {
        int orderId;
        String customerName;
        double amount;

        Order(int orderId, String customerName, double amount) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return String.format("[訂單號: %d | 客戶: %s | 金額: %.1f]", orderId, customerName, amount);
        }
    }

    static class Node {
        Order order;
        Node left, right;
        Node(Order o) { this.order = o; }
    }

    private Node root;

    public void add(int orderId, String customerName, double amount) {
        root = insertRec(root, new Order(orderId, customerName, amount));
    }

    private Node insertRec(Node node, Order o) {
        if (node == null) return new Node(o);
        if (o.orderId < node.order.orderId) node.left = insertRec(node.left, o);
        else if (o.orderId > node.order.orderId) node.right = insertRec(node.right, o);
        else System.out.println("訂單號 " + o.orderId + " 已存在！");
        return node;
    }

    public Order find(int orderId) {
        Node cur = root;
        while (cur != null) {
            if (orderId == cur.order.orderId) return cur.order;
            else if (orderId < cur.order.orderId) cur = cur.left;
            else cur = cur.right;
        }
        return null;
    }

    public void cancel(int orderId) {
        root = deleteRec(root, orderId);
    }

    private Node deleteRec(Node node, int orderId) {
        if (node == null) return null;
        if (orderId < node.order.orderId) node.left = deleteRec(node.left, orderId);
        else if (orderId > node.order.orderId) node.right = deleteRec(node.right, orderId);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node minNode = getMin(node.right);
            node.order = minNode.order;
            node.right = deleteRec(node.right, minNode.order.orderId);
        }
        return node;
    }

    private Node getMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public void updateAmount(int orderId, double newAmount) {
        Order o = find(orderId);
        if (o != null) {
            o.amount = newAmount;
            System.out.println("更新金額成功: " + o);
        } else {
            System.out.println("找不到訂單號: " + orderId);
        }
    }

    public void rangeReport(int startId, int endId) {
        System.out.printf("--- 訂單範圍報表 [%d ~ %d] ---\n", startId, endId);
        rangeReportRec(root, startId, endId);
    }

    private void rangeReportRec(Node node, int low, int high) {
        if (node == null) return;
        if (node.order.orderId > low) rangeReportRec(node.left, low, high);
        if (node.order.orderId >= low && node.order.orderId <= high) {
            System.out.println(node.order);
        }
        if (node.order.orderId < high) rangeReportRec(node.right, low, high);
    }

    public void summary() {
        int[] count = new int[1];
        double[] totalAmount = new double[1];
        summaryRec(root, count, totalAmount);
        System.out.println("=== 訂單統計總結 ===");
        System.out.println("訂單總筆數: " + count[0]);
        System.out.printf("訂單總金額: %.2f\n", totalAmount[0]);
    }

    private void summaryRec(Node node, int[] count, double[] totalAmount) {
        if (node != null) {
            summaryRec(node.left, count, totalAmount);
            count[0]++;
            totalAmount[0] += node.order.amount;
            summaryRec(node.right, count, totalAmount);
        }
    }

    public static void main(String[] args) {
        OrderBstSystem sys = new OrderBstSystem();
        sys.add(1005, "Alice", 2500.0);
        sys.add(1002, "Bob", 1200.0);
        sys.add(1008, "Charlie", 4500.0);
        sys.add(1001, "David", 800.0);
        sys.add(1006, "Eve", 3100.0);

        sys.updateAmount(1002, 1500.0);
        sys.cancel(1001);

        sys.rangeReport(1002, 1007);
        sys.summary();
    }
}