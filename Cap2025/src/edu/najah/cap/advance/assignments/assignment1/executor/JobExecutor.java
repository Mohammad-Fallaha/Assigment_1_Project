package edu.najah.cap.advance.assignments.assignment1.executor;

import edu.najah.cap.advance.assignments.assignment1.connections.Connection;
import edu.najah.cap.advance.assignments.assignment1.connections.ConnectionManager;
import edu.najah.cap.advance.assignments.assignment1.job.Job;

/**
 * Real JobExecutor that executes jobs using the Strategy Pattern.
 * The old executeJob(Job) method remains accessible.
 */
public class JobExecutor {

    private final ConnectionManager cm;
    private final JobStrategyFactory strategyFactory;

    public JobExecutor(ConnectionManager cm, JobStrategyFactory strategyFactory) {
        this.cm = cm;
        this.strategyFactory = strategyFactory;
    }

    /**
     * Existing execution method.
     * Still accessible and may be used internally where no permission checks are needed.
     */
    public void executeJob(Job job) {
        System.out.printf("[RealExecutor] Starting job %s (%s) requested by %s%n",
                job.getName(), job.getType(),
                job.getRequestedBy() == null ? "unknown" : job.getRequestedBy().getName());

        Connection c = cm.createConnection();
        try {
            // نستخدم الميثود الداخلية التي لا تدير Connection ولا صلاحيات
            executeJobInternal(job, c);
        } finally {
            cm.closeConnection(c);
            System.out.printf("[RealExecutor] Finished job %s%n", job.getName());
        }
    }

    /**
     * Internal execution method for the Proxy.
     * - No permission checks
     * - No logging
     * - No connection management
     * The Proxy will handle all of that.
     */
    public void executeJobInternal(Job job, Connection connection) {
        JobStrategy strategy = strategyFactory.getStrategy(job.getType());
        strategy.execute(job, connection);
    }
}
