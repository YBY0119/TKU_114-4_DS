import java.util.*;

public class Q01_PriorityRecord {
    public record Job(String id, int priority, long sequence) {}

    public static List<String> processOrder(List<Job> jobs) {
        List<String> result = new ArrayList<>();
        if (jobs == null || jobs.isEmpty()) {
            return result;
        }

        Comparator<Job> comparator = Comparator
                .comparingInt(Job::priority)
                .thenComparingLong(Job::sequence)
                .thenComparing(Job::id, Comparator.nullsLast(String::compareTo));

        PriorityQueue<Job> pq = new PriorityQueue<>(comparator);

        for (Job job : jobs) {
            if (job != null) {
                pq.offer(job);
            }
        }

        while (!pq.isEmpty()) {
            result.add(pq.poll().id());
        }

        return result;
    }

    public static void main(String[] args) {
        List<Job> testJobs = Arrays.asList(
            new Job("JobC", 2, 100L),
            new Job("JobA", 1, 200L),
            new Job("JobB", 1, 100L),
            null
        );

        List<String> order = processOrder(testJobs);
        System.out.println("Process Order: " + order); // 預期: [JobB, JobA, JobC]
    }
}