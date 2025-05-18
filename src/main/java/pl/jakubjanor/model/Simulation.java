package pl.jakubjanor.model;

import java.util.LinkedList;

public class Simulation {
    private final EventRunner runner;

    public Simulation(LinkedList<Event> events) {
        this.runner = new EventRunner(events);
    }
    public void start() {
        CrossroadAlgorithm crossroadAlgorithm = new CrossroadAlgorithm();

    }
}
