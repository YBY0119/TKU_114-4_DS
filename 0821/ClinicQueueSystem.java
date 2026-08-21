import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Patient {
    private final String id;
    private final String name;

    public Patient(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Patient{id='" + id + "', name='" + name + "'}";
    }
}

public class ClinicQueueSystem {
    private final Deque<Patient> waitingQueue = new ArrayDeque<>();
    private final List<Patient> completedList = new ArrayList<>();

    public void register(Patient patient) {
        waitingQueue.addLast(patient);
        System.out.println("Registered: " + patient);
    }

    public boolean cancel(String patientId) {
        for (Patient p : waitingQueue) {
            if (p.getId().equals(patientId)) {
                waitingQueue.remove(p);
                System.out.println("Cancelled: " + p);
                return true;
            }
        }
        System.out.println("Cancel failed: Patient ID " + patientId + " not found.");
        return false;
    }

    public Patient callNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("Call next failed: Queue is empty.");
            return null;
        }
        Patient next = waitingQueue.pollFirst(); // FIFO
        completedList.add(next);
        System.out.println("Called: " + next);
        return next;
    }

    public Patient peekNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("No next patient.");
            return null;
        }
        System.out.println("Next up: " + waitingQueue.peekFirst());
        return waitingQueue.peekFirst();
    }

    public void printCompleted() {
        System.out.println("Completed Patients Today: " + completedList);
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();

        clinic.register(new Patient("P01", "Alice"));
        clinic.register(new Patient("P02", "Bob"));
        clinic.register(new Patient("P03", "Charlie"));

        clinic.peekNext();
        clinic.cancel("P02");
        clinic.cancel("P99"); // 測試不存在 ID

        clinic.callNext();
        clinic.callNext();
        clinic.callNext(); // 測試空隊列叫號

        clinic.printCompleted();
    }
}