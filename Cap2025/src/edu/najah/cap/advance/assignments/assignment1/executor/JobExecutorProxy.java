package edu.najah.cap.advance.assignments.assignment1.executor;

import edu.najah.cap.advance.assignments.assignment1.connections.Connection;
import edu.najah.cap.advance.assignments.assignment1.connections.ConnectionManager;
import edu.najah.cap.advance.assignments.assignment1.job.Job;
import edu.najah.cap.advance.assignments.assignment1.model.User;

/**
 * Proxy for controlled job execution.
 * - Validates permissions
 * - Logs start/end
 * - Measures execution time
 * - Acquires a connection from ConnectionManager (connection pool)
 * - Delegates to the real executor
 * - Releases the connection afterwards
 */
public class JobExecutorProxy {

    private final JobExecutor realExecutor;
    private final ConnectionManager connectionManager;

    public JobExecutorProxy(JobExecutor realExecutor, ConnectionManager connectionManager) {
        this.realExecutor = realExecutor;
        this.connectionManager = connectionManager;
    }

    /**
     * Controlled execution method (Proxy).
     * This is the "new" execution flow.
     */
    public void executeJob(Job job) {
        // 1) Validate user permissions
        User user = job.getRequestedBy();
        if (!hasPermission(user)) {
            throw new SecurityException("User is not allowed to execute this job.");
        }

        long startTime = System.currentTimeMillis();

        // 2) Log job start
        System.out.printf("[Proxy] Starting job %s (%s) requested by %s%n",
                job.getName(), job.getType(),
                user == null ? "unknown" : user.getName());

        // 3) Acquire a DB connection from the ConnectionManager (pool)
        Connection connection = connectionManager.acquire();

        try {
            // 4) Delegate to the real executor (no type-checking here)
            realExecutor.executeJobInternal(job, connection);
        } finally {
            // 5) Release the connection afterwards
            connectionManager.release(connection);

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            // 6) Log job end + execution time
            System.out.printf("[Proxy] Finished job %s in %d ms%n",
                    job.getName(), duration);
        }
    }

    private boolean hasPermission(User user) {
        if (user == null) {
            return false; // no user -> no permission
        }
        // For now: any non-null user is allowed.
        return true;
    }
}
