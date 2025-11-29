package edu.najah.cap.advance.assignments.assignment1.templates;

import edu.najah.cap.advance.assignments.assignment1.job.Job;


public class TemplateManager {

    private final JobTemplateRegistry registry;

    public TemplateManager(JobTemplateRegistry registry) {
        this.registry = registry;
    }

    public Job createEmailJob(String jobId) {
        return registry.create("EMAIL", jobId);
    }

    public Job createDataProcessingJob(String jobId) {
        return registry.create("DATA", jobId);
    }

    public Job createReportJob(String jobId) {
        return registry.create("REPORT", jobId);
    }
}
