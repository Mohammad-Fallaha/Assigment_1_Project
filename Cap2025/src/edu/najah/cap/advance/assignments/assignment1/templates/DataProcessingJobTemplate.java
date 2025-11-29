package edu.najah.cap.advance.assignments.assignment1.templates;


 
public class DataProcessingJobTemplate extends HeavyTemplate {

    public DataProcessingJobTemplate(String name, String config, String body) {
        super("DATA", name, config, body);
    }

    @Override
    public JobPrototype clonePrototype() {
        return new DataProcessingJobTemplate(getName(), getConfig(), getBody());
    }
}
