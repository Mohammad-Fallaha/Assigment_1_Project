package edu.najah.cap.advance.assignments.assignment1.templates;


public class EmailJobTemplate extends HeavyTemplate {

    public EmailJobTemplate(String name, String config, String body) {
        super("EMAIL", name, config, body);
    }

    @Override
    public JobPrototype clonePrototype() {
        return new EmailJobTemplate(getName(), getConfig(), getBody());
    }
}
