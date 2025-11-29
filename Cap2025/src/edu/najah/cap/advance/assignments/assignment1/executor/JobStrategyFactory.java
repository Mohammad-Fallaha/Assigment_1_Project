package edu.najah.cap.advance.assignments.assignment1.executor;

import java.util.HashMap;
import java.util.Map;


public class JobStrategyFactory {

    private final Map<String, JobStrategy> strategies = new HashMap<>();

    public JobStrategyFactory() {
        strategies.put("EMAIL", new EmailJobStrategy());
        strategies.put("DATA", new DataProcessingStrategy());
        strategies.put("REPORT", new ReportGenerationStrategy());
    }

    public JobStrategy getStrategy(String jobType) {
        if (jobType == null) {
            throw new IllegalArgumentException("Job type must not be null");
        }

        JobStrategy strategy = strategies.get(jobType.toUpperCase());
        if (strategy == null) {
            throw new IllegalArgumentException("No strategy found for job type: " + jobType);
        }

        return strategy;
    }
}
