package project20280.priorityqueue.JobScheduling;

public class Job implements Runnable {
    private String jobName;
    private JobPriority jobPriority;

    @Override
    public void run() {
        System.out.println("Job:" + jobName +
        " Priority:" + jobPriority);
        // do some work
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // to simulate actual execution time
    }
    // standard setters and getters
}
