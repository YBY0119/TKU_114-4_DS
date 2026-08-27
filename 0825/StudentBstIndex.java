public class StudentBstIndex {
    static class Student {
        int studentId;
        String name;
        Student(int id, String name) {
            this.studentId = id;
            this.name = name;
        }
        @Override
        public String toString() {
            return "[" + studentId + ": " + name + "]";
        }
    }

    static class Node {
        Student data;
        Node left, right;
        Node(Student data) { this.data = data; }
    }

    private Node root;

    public boolean insert(Student s) {
        if (search(s.studentId) != null) {
            System.out.println("Insert 失敗: 學生 ID " + s.studentId + " 已存在！");
            return false;
        }
        root = insertRec(root, s);
        return true;
    }

    private Node insertRec(Node node, Student s) {
        if (node == null) return new Node(s);
        if (s.studentId < node.data.studentId) node.left = insertRec(node.left, s);
        else if (s.studentId > node.data.studentId) node.right = insertRec(node.right, s);
        return node;
    }

    public Student search(int studentId) {
        Node cur = root;
        while (cur != null) {
            if (studentId == cur.data.studentId) return cur.data;
            else if (studentId < cur.data.studentId) cur = cur.left;
            else cur = cur.right;
        }
        return null;
    }

    public void delete(int studentId) {
        root = deleteRec(root, studentId);
    }

    private Node deleteRec(Node node, int id) {
        if (node == null) return null;
        if (id < node.data.studentId) node.left = deleteRec(node.left, id);
        else if (id > node.data.studentId) node.right = deleteRec(node.right, id);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node minNode = getMin(node.right);
            node.data = minNode.data;
            node.right = deleteRec(node.right, minNode.data.studentId);
        }
        return node;
    }

    private Node getMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.data + " ");
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        StudentBstIndex idx = new StudentBstIndex();
        idx.insert(new Student(102, "Alice"));
        idx.insert(new Student(101, "Bob"));
        idx.insert(new Student(103, "Charlie"));
        idx.insert(new Student(101, "Duplicate Bob")); // 測試重複

        System.out.print("所有學生列表: ");
        idx.inorder();

        System.out.println("查詢 102: " + idx.search(102));
        idx.delete(102);
        System.out.print("刪除 102 後: ");
        idx.inorder();
    }
}