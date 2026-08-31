import java.util.PriorityQueue;

public class EmergencyTriageQueue {
    static class Patient implements Comparable<Patient> {
        String patientId;
        int triageLevel; // 1 (最緊急) ~ 5 (最不緊急)
        int arrivalOrder;

        public Patient(String patientId, int triageLevel, int arrivalOrder) {
            this.patientId = patientId;
            this.triageLevel = triageLevel;
            this.arrivalOrder = arrivalOrder;
        }

        @Override
        public int compareTo(Patient o) {
            if (this.triageLevel != o.triageLevel) {
                return Integer.compare(this.triageLevel, o.triageLevel);
            }
            if (this.arrivalOrder != o.arrivalOrder) {
                return Integer.compare(this.arrivalOrder, o.arrivalOrder);
            }
            return this.patientId.compareTo(o.patientId);
        }

        @Override
        public String toString() {
            return "Patient[" + patientId + ", Level=" + triageLevel + ", Order=" + arrivalOrder + "]";
        }
    }

    private final PriorityQueue<Patient> pq = new PriorityQueue<>();
    private int orderCounter = 0;

    public void checkIn(String patientId, int triageLevel) {
        pq.offer(new Patient(patientId, triageLevel, ++orderCounter));
    }

    public Patient peekNext() {
        return pq.peek();
    }

    public Patient callNext() {
        if (pq.isEmpty()) {
            System.out.println("目前無候診病人 (Queue is empty).");
            return null;
        }
        Patient p = pq.poll();
        System.out.println("叫號: " + p);
        return p;
    }

    public int getWaitingCount() {
        return pq.size();
    }

    public static void main(String[] args) {
        EmergencyTriageQueue eq = new EmergencyTriageQueue();
        eq.checkIn("P001", 3);
        eq.checkIn("P002", 1);
        eq.checkIn("P003", 1);
        eq.checkIn("P004", 2);

        System.out.println("目前等待人數: " + eq.getWaitingCount());
        eq.callNext();
        eq.callNext();
        eq.callNext();
        eq.callNext();
        eq.callNext(); // 測試空佇列
    }
}