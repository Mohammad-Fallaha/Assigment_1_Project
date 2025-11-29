package edu.najah.cap.advance.assignments.assignment1.templates;

import edu.najah.cap.advance.assignments.assignment1.job.Job;

import java.util.HashMap;
import java.util.Map;

 
public class JobTemplateRegistry {

    private final Map<String, JobPrototype> prototypes = new HashMap<>();

    public void register(String type, JobPrototype prototype) {
        if (type == null || prototype == null) {
            throw new IllegalArgumentException("Type and prototype must not be null");
        }
        prototypes.put(type.toUpperCase(), prototype);
    }

    public Job create(String type, String jobId) {
        if (type == null) {
            throw new IllegalArgumentException("Type must not be null");
        }

        JobPrototype prototype = prototypes.get(type.toUpperCase());
        if (prototype == null) {
            throw new IllegalArgumentException("No job template registered for type: " + type);
        }

        JobPrototype cloned = prototype.clonePrototype();

        return cloned.createJobInstance(jobId);
    }
}
