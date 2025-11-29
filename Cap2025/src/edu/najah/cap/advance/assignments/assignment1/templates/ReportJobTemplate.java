package edu.najah.cap.advance.assignments.assignment1.templates;


public class ReportJobTemplate extends HeavyTemplate {

    public ReportJobTemplate(String name, String config, String body) {
        super("REPORT", name, config, body);
    }

    @Override
    public JobPrototype clonePrototype() {
        return new ReportJobTemplate(getName(), getConfig(), getBody());
    }
}
