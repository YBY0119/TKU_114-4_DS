public class ScoreRangeBst {
    static class Record implements Comparable<Record> {
        int score;
        int studentId;

        Record(int score, int studentId) {
            this.score = score;
            this.studentId = studentId;
        }

        @Override
        public int compareTo(Record o) {
            if (this.score != o.score) {
                return Integer.compare(this.score, o.score);
            }
            return Integer.compare(this.studentId, o.studentId);
        }

        @Override
        public String toString() {
            return "[學號: " + studentId + ", 分數: " + score + "]";
        }
    }

    static class Node {
        Record record;
        Node left, right;
        Node(Record r) { this.record = r; }
    }

    private Node root;

    public void insert(int score, int studentId) {
        root = insertRec(root, new Record(score, studentId));
    }

    private Node insertRec(Node node, Record r) {
        if (node == null) return new Node(r);
        int cmp = r.compareTo(node.record);
        if (cmp < 0) node.left = insertRec(node.left, r);
        else if (cmp > 0) node.right = insertRec(node.right, r);
        return node;
    }

    public void queryRange(int minScore, int maxScore) {
        System.out.printf("分數區間 [%d ~ %d] 名單:\n", minScore, maxScore);
        queryRangeRec(root, minScore, maxScore);
    }

    private void queryRangeRec(Node node, int minScore, int maxScore) {
        if (node == null) return;
        if (node.record.score > minScore) {
            queryRangeRec(node.left, minScore, maxScore);
        }
        if (node.record.score >= minScore && node.record.score <= maxScore) {
            System.out.println("  " + node.record);
        }
        if (node.record.score < maxScore) {
            queryRangeRec(node.right, minScore, maxScore);
        }
    }

    public static void main(String[] args) {
        ScoreRangeBst bst = new ScoreRangeBst();
        // 支援同分但學號不同
        bst.insert(85, 1001);
        bst.insert(90, 1002);
        bst.insert(85, 1003);
        bst.insert(70, 1004);
        bst.insert(95, 1005);
        bst.insert(85, 1000);

        bst.queryRange(80, 90);
    }
}