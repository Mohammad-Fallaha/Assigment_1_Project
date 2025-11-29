package edu.najah.cap.advance.assignments.assignment1;

import edu.najah.cap.advance.assignments.assignment1.connections.ConnectionManager;
import edu.najah.cap.advance.assignments.assignment1.executor.*;
import edu.najah.cap.advance.assignments.assignment1.job.Job;
import edu.najah.cap.advance.assignments.assignment1.model.User;
import edu.najah.cap.advance.assignments.assignment1.templates.*;

import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("=== TMPS Refactored App (Prototype + Strategy + Proxy) ===");

        ConnectionManager connectionManager = new ConnectionManager();

        JobTemplateRegistry templateRegistry = new JobTemplateRegistry();

        templateRegistry.register("EMAIL",
                new EmailJobTemplate("Default Email Template",
                        "priority=HIGH;sender=system",
                        "Hello user, this is a default email."));

        templateRegistry.register("DATA",
                new DataProcessingJobTemplate("Default Data Job",
                        "batchSize=1000;source=db1",
                        "SELECT * FROM source_table"));

        templateRegistry.register("REPORT",
                new ReportJobTemplate("Default Report Template",
                        "format=PDF;orientation=portrait",
                        "This is the default report body."));

        TemplateManager templateManager = new TemplateManager(templateRegistry);

        JobStrategyFactory strategyFactory = new JobStrategyFactory();

        JobExecutor realExecutor = new JobExecutor(connectionManager, strategyFactory);

        JobExecutorProxy executorProxy = new JobExecutorProxy(realExecutor, connectionManager);

        User alice = new User("alice", Arrays.asList("EMAIL", "REPORT"));

        System.out.println("\n--- Create & Execute REPORT job (Prototype + Proxy) ---");
        Job reportJob = templateManager.createReportJob("job-1");
        reportJob.setRequestedBy(alice);
        executorProxy.executeJob(reportJob);

        System.out.println("\n--- Create & Execute EMAIL job (Prototype + Proxy) ---");
        Job emailJob = templateManager.createEmailJob("job-2");
        emailJob.setRequestedBy(alice);
        executorProxy.executeJob(emailJob);
    }
}
