package pl.jakubjanor.model;

import java.util.Queue;

public class EventRunner {
    private Queue<Event> eventQueue;

    public EventRunner(Queue<Event> eventQueue) {
        this.eventQueue = eventQueue;
    }

    public void runNextEvent() {

    }

    public boolean hasEventLeft() {
        return true;
    }
}
