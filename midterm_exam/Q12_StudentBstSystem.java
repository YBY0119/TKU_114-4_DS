import java.util.*;

public class Q12_StudentBstSystem {

    public static class Student {
        private final int id;
        private final String name;
        private int score;

        public Student(int id, String name, int score) {
            if (id <= 0) {
                throw new IllegalArgumentException("Student id 必須大於 0");
            }
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("name 不得為 null 或 blank");
            }
            this.id = id;
            this.name = name;
            this.score = Math.max(0, Math.min(100, score));
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = Math.max(0, Math.min(100, score));
        }

        @Override
        public String toString() {
            return id + "|" + name + "|" + score;
        }
    }

    private static class Node {
        Student student;
        Node left, right;
        Node(Student student) {
            this.student = student;
        }
    }

    private Node root;

    public boolean add(Student student) {
        if (student == null || find(student.getId()) != null) {
            return false;
        }
        root = insertRec(root, student);
        return true;
    }

    private Node insertRec(Node node, Student student) {
        if (node == null) {
            return new Node(student);
        }
        if (student.getId() < node.student.getId()) {
            node.left = insertRec(node.left, student);
        } else if (student.getId() > node.student.getId()) {
            node.right = insertRec(node.right, student);
        }
        return node;
    }

    public Student find(int id) {
        Node cur = root;
        while (cur != null) {
            if (id == cur.student.getId()) return cur.student;
            else if (id < cur.student.getId()) cur = cur.left;
            else cur = cur.right;
        }
        return null;
    }

    public boolean updateScore(int id, int score) {
        Student s = find(id);
        if (s == null) {
            return false;
        }
        s.setScore(score);
        return true;
    }

    public boolean remove(int id) {
        if (find(id) == null) {
            return false;
        }
        root = removeRec(root, id);
        return true;
    }

    private Node removeRec(Node node, int id) {
        if (node == null) return null;
        if (id < node.student.getId()) {
            node.left = removeRec(node.left, id);
        } else if (id > node.student.getId()) {
            node.right = removeRec(node.right, id);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node minNode = getMin(node.right);
            node.student = minNode.student;
            node.right = removeRec(node.right, minNode.student.getId());
        }
        return node;
    }

    private Node getMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public List<Student> studentsBetween(int lowId, int highId) {
        if (lowId > highId) {
            return Collections.emptyList();
        }
        List<Student> result = new ArrayList<>();
        rangeRec(root, lowId, highId, result);
        return result;
    }

    private void rangeRec(Node node, int low, int high, List<Student> result) {
        if (node == null) return;
        if (node.student.getId() > low) {
            rangeRec(node.left, low, high, result);
        }
        if (node.student.getId() >= low && node.student.getId() <= high) {
            result.add(node.student);
        }
        if (node.student.getId() < high) {
            rangeRec(node.right, low, high, result);
        }
    }

    public List<Student> inorder() {
        List<Student> result = new ArrayList<>();
        inorderRec(root, result);
        return result;
    }

    private void inorderRec(Node node, List<Student> result) {
        if (node != null) {
            inorderRec(node.left, result);
            result.add(node.student);
            inorderRec(node.right, result);
        }
    }

    public static void main(String[] args) {
        Q12_StudentBstSystem system = new Q12_StudentBstSystem();
        system.add(new Q12_StudentBstSystem.Student(300, "Mina", 78));
        system.add(new Q12_StudentBstSystem.Student(100, "Leo", 84));
        system.add(new Q12_StudentBstSystem.Student(500, "Nora", 105)); // 105 自動限縮為 100
        system.add(new Q12_StudentBstSystem.Student(200, "Ivy", 69));

        System.out.println(system.updateScore(200, 88));             // true
        System.out.println(system.studentsBetween(150, 500));         // [200|Ivy|88, 300|Mina|78, 500|Nora|100]
        System.out.println(system.remove(300));                       // true
        System.out.println(system.inorder());                         // [100|Leo|84, 200|Ivy|88, 500|Nora|100]
    }
}