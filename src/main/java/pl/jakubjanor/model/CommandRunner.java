package pl.jakubjanor.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CommandRunner {
    private final Queue<Command> commandQueue;
    private Crossroad crossroad;
    private final List<Note> vehicleNotes = new ArrayList<>();
    private final CrossroadAlgorithm lightsAlgorithm = new CrossroadAlgorithm();

    public CommandRunner(Queue<Command> eventQueue, Crossroad crossroad) {
        this.commandQueue = eventQueue;
        this.crossroad = crossroad;
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
        return commandQueue.isEmpty();
    }

    public List<Note> getVehicleNotes() {
        return vehicleNotes;
    }
}
