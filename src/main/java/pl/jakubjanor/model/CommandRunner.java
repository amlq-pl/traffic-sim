package pl.jakubjanor.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CommandRunner {
    private final Queue<Command> commandQueue;
    private final Crossroad crossroad;
    private final List<Note> vehicleNotes = new LinkedList<>();
    private final CrossroadAlgorithm lightsAlgorithm = new CrossroadAlgorithm();

    public CommandRunner(Queue<Command> eventQueue, Crossroad crossroad) {
        this.commandQueue = eventQueue;
        this.crossroad = crossroad;
        this.lightsAlgorithm.turnOnLights(this.crossroad);
    }

    public void runNextCommand() {
        if (!commandQueue.isEmpty()) {
            Command cmd = commandQueue.poll();
            Note note = cmd.execute(crossroad, lightsAlgorithm);

            if (note != null) {
                vehicleNotes.add(note);
            }
        }
    }

    public boolean hasCommandLeft() {
        return !commandQueue.isEmpty();
    }

    public List<Note> getVehicleNotes() {
        return vehicleNotes;
    }
}
