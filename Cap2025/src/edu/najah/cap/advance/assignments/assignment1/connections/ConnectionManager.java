package edu.najah.cap.advance.assignments.assignment1.connections;

import java.util.LinkedList;
import java.util.Queue;


public class ConnectionManager {

    private static final int MAX = 10;

    private final Queue<Connection> availableConnections = new LinkedList<>();

    private int createdCount = 0;

    public ConnectionManager() {
    }

    public synchronized Connection acquire() {
        while (availableConnections.isEmpty() && createdCount >= MAX) {
            System.out.println("[Pool] All connections are busy, waiting...");
            try {
                wait(); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }

        if (!availableConnections.isEmpty()) {
            Connection c = availableConnections.poll();
            System.out.println("[Pool] Reusing connection: " + c.getId());
            return c;
        }

        createdCount++;
        Connection c = new Connection("Conn-" + createdCount);
        System.out.println("[Pool] Creating new connection: " + c.getId());
        return c;
    }

    public synchronized void release(Connection c) {
        if (c == null) {
            return;
        }
        System.out.println("[Pool] Returning connection to pool: " + c.getId());
        availableConnections.offer(c);
        notify();
    }

    public Connection createConnection() {
        return acquire();
    }

    public void closeConnection(Connection c) {
        release(c);
    }
}
