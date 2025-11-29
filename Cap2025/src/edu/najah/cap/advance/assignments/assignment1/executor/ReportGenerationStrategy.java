package edu.najah.cap.advance.assignments.assignment1.executor;

import edu.najah.cap.advance.assignments.assignment1.connections.Connection;
import edu.najah.cap.advance.assignments.assignment1.job.Job;


public class ReportGenerationStrategy implements JobStrategy {

    @Override
    public void execute(Job job, Connection connection) {
        System.out.println("[ReportJob] Generating report (" + job.getName() + ") using config: " + job.getConfig());

        connection.executeQuery("SELECT * FROM report_source WHERE report = '" + job.getName() + "'");

        connection.executeQuery(
                "INSERT INTO generated_reports (job_id, path) VALUES ('"
                        + job.getId() + "', '/reports/" + job.getId() + ".pdf')"
        );

    }
    
}