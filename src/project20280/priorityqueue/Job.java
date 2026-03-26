package project20280.priorityqueue;

public class Job implements Runnable, Comparable<Job> {
    private String jobName;
    private JobPriority jobPriority;
    private int duration;

    public Job (String JobName, JobPriority jobPriority, int duration) {
        this.jobName = jobName;
        this.jobPriority = jobPriority;
        this.duration = duration;
    }

    public JobPriority getJobPriority() {
        return jobPriority;
    }

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

    public int compareTo(Job b) {
        int priorityCompare = jobPriority.compareTo(b.jobPriority);
        if (priorityCompare != 0) {return priorityCompare;}
        else {return duration < b.duration ? 1 : -1;}
    }
}
