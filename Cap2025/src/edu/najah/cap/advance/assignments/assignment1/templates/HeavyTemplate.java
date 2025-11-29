package edu.najah.cap.advance.assignments.assignment1.templates;

import edu.najah.cap.advance.assignments.assignment1.job.Job;

public class HeavyTemplate implements JobPrototype {

    private final String type;
    private final String name;
    private final String config;
    private final String body;

    public HeavyTemplate(String type, String name, String config, String body) {
        this.type = type;
        this.name = name;
        this.config = config;
        this.body = body;
    }

    @Override
    public JobPrototype clonePrototype() {
        return new HeavyTemplate(type, name, config, body);
    }

    @Override
    public Job createJobInstance(String id) {
        return new Job(id, type, name, config);
    }

    @Override
    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getConfig() {
        return config;
    }

    public String getBody() {
        return body;
    }
}
