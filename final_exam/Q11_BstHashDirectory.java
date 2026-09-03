import java.util.*;

public class Q11_BstHashDirectory {

    private static class BSTNode {
        int id;
        BSTNode left, right;
        BSTNode(int id) { this.id = id; }
    }

    private BSTNode root = null;
    private final Map<Integer, String> idToName = new HashMap<>();

    public boolean add(int id, String name) {
        if (id <= 0 || name == null) {
            return false;
        }
        String trimmedName = name.trim();
        if (trimmedName.isEmpty() || idToName.containsKey(id)) {
            return false;
        }

        root = insertBST(root, id);
        idToName.put(id, trimmedName);
        return true;
    }

    private BSTNode insertBST(BSTNode node, int id) {
        if (node == null) return new BSTNode(id);
        if (id < node.id) {
            node.left = insertBST(node.left, id);
        } else if (id > node.id) {
            node.right = insertBST(node.right, id);
        }
        return node;
    }

    public String findName(int id) {
        return idToName.get(id);
    }

    public boolean remove(int id) {
        if (!idToName.containsKey(id)) {
            return false;
        }
        root = removeBST(root, id);
        idToName.remove(id);
        return true;
    }

    private BSTNode removeBST(BSTNode node, int id) {
        if (node == null) return null;
        if (id < node.id) {
            node.left = removeBST(node.left, id);
        } else if (id > node.id) {
            node.right = removeBST(node.right, id);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // 尋找右子樹最小值替代
            BSTNode minNode = getMin(node.right);
            node.id = minNode.id;
            node.right = removeBST(node.right, minNode.id);
        }
        return node;
    }

    private BSTNode getMin(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public List<Integer> idsBetween(int low, int high) {
        List<Integer> result = new ArrayList<>();
        if (low > high) {
            return result;
        }
        inOrderRange(root, low, high, result);
        return result;
    }

    private void inOrderRange(BSTNode node, int low, int high, List<Integer> result) {
        if (node == null) return;
        if (node.id > low) {
            inOrderRange(node.left, low, high, result);
        }
        if (node.id >= low && node.id <= high) {
            result.add(node.id);
        }
        if (node.id < high) {
            inOrderRange(node.right, low, high, result);
        }
    }

    public int size() {
        return idToName.size();
    }

    public static void main(String[] args) {
        Q11_BstHashDirectory dir = new Q11_BstHashDirectory();
        dir.add(10, " Alice ");
        dir.add(5, "Bob");
        dir.add(20, "Charlie");
        dir.add(15, "David");

        System.out.println("Find 10: " + dir.findName(10)); // Alice
        System.out.println("IDs between 6 and 18: " + dir.idsBetween(6, 18)); // [10, 15]
        
        dir.remove(10);
        System.out.println("Size after remove: " + dir.size()); // 3
        System.out.println("IDs between 1 and 25: " + dir.idsBetween(1, 25)); // [5, 15, 20]
    }
}