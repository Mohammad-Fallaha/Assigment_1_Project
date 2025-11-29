package edu.najah.cap.advance.assignments.assignment1.executor;

import edu.najah.cap.advance.assignments.assignment1.connections.Connection;
import edu.najah.cap.advance.assignments.assignment1.job.Job;


public class DataProcessingStrategy implements JobStrategy {

    @Override
    public void execute(Job job, Connection connection) {
        System.out.println("[DataJob] Reading & transforming data using config: " + job.getConfig());
        connection.executeQuery("SELECT * FROM source_table WHERE job_id = '" + job.getId() + "'");
        connection.executeQuery("INSERT INTO processed_results (job_id) VALUES ('" + job.getId() + "')");
    }
}
