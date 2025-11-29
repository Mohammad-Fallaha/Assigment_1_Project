package edu.najah.cap.advance.assignments.assignment1.executor;

import edu.najah.cap.advance.assignments.assignment1.connections.Connection;
import edu.najah.cap.advance.assignments.assignment1.job.Job;


public interface JobStrategy {

    
    void execute(Job job, Connection connection);
}
