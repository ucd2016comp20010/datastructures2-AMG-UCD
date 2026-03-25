package project20280.priorityqueue.JobScheduling;

import java.util.Comparator;
import java.util.concurrent.*;
import project20280.interfaces.PriorityQueue;

public class PriorityJobScheduler {
    private ExecutorService priorityJobPoolExecutor;
    private ExecutorService priorityJobScheduler = Executors.newSingleThreadExecutor();
    private PriorityBlockingQueue<Job> priorityQueue;

    public PriorityJobScheduler(Integer poolSize, Integer queueSize) {
        priorityJobPoolExecutor = Executors.newFixedThreadPool(poolSize);
        priorityQueue = new PriorityBlockingQueue<Job>(queueSize, Comparator.comparing(Job::getJobPriority));
        priorityJobScheduler.execute(() -> {
            while (true) {
                try {
                    priorityJobPoolExecutor.execute(priorityQueue.take());
                } 
                catch (InterruptedException e) {
                // exception needs special handling
                    break;
                }
            }
        });
    }
    public void scheduleJob(Job job) {
    priorityQueue.add(job);
}
}