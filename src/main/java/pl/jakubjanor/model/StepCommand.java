package pl.jakubjanor.model;

import java.util.List;

public class StepCommand implements Command {
    @Override
    public String getType() {
        return "Step";
    }

    @Override
    public Note execute(Crossroad crossroad, CrossroadAlgorithm algorithm) {
        algorithm.updateLights(crossroad);
        List<Vehicle> left = crossroad.moveVehicles();
        return new Note(left);
    }
}

