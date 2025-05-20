package pl.jakubjanor.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class CrossroadAlgorithm {
    private final Map<Direction, Integer> stepScore = new HashMap<>();

    public CrossroadAlgorithm() {
        for (Direction direction : Direction.values()) {
            stepScore.put(direction, 0);
        }
    }

    public void updateLights(Crossroad crossroad) {
        Map<Direction, Integer> tempScore = new HashMap<>();
        for (Direction direction : Direction.values()) {
            Queue<Vehicle> road = crossroad.getRoads().get(direction);
            int cnt = road.size();
            tempScore.put(direction, cnt);
        }
        int vertSum = tempScore.get(Direction.NORTH) + tempScore.get(Direction.SOUTH);
        int vertSumAmplified = vertSum + stepScore.get(Direction.NORTH) + stepScore.get(Direction.SOUTH);

        int horSum = tempScore.get(Direction.WEST) + tempScore.get(Direction.EAST);
        int horSumAmplified = horSum + stepScore.get(Direction.WEST) + stepScore.get(Direction.EAST);

        if (vertSum == 0 && crossroad.getLights().get(Direction.NORTH).getState() == LightColor.GREEN) {
            switchLights(crossroad);
        }

        if (horSum == 0 && crossroad.getLights().get(Direction.EAST).getState() == LightColor.GREEN) {
            switchLights(crossroad);
        }

        if (crossroad.getLights().get(Direction.NORTH).getState() == LightColor.GREEN
                && horSum > vertSum || crossroad.getLights().get(Direction.EAST).getState() == LightColor.GREEN
                && horSum < vertSum) {
            switchLights(crossroad);
        } else {
            for (Direction dir : Direction.values()) {
                if (stepScore.get(dir) != 0) {
                    stepScore.replace(dir, stepScore.get(dir) - 1);
                }
            }
        }

    }

    public void switchGreenVertical(Crossroad crossroad) {
        crossroad.getLights().get(Direction.NORTH).setState(LightColor.GREEN);
        stepScore.replace(Direction.NORTH, 5);
        crossroad.getLights().get(Direction.SOUTH).setState(LightColor.GREEN);
        stepScore.replace(Direction.SOUTH, 5);
    }

    public void switchGreenHorizontal(Crossroad crossroad) {
        crossroad.getLights().get(Direction.EAST).setState(LightColor.GREEN);
        stepScore.replace(Direction.EAST, 5);
        crossroad.getLights().get(Direction.WEST).setState(LightColor.GREEN);
        stepScore.replace(Direction.WEST, 5);
    }

    public void switchLights(Crossroad crossroad) {
        for (Direction dir : Direction.values()) {
            if (crossroad.getLights().get(dir).getState() == LightColor.GREEN) {
                stepScore.replace(dir, 0);
                crossroad.getLights().get(dir).setState(LightColor.RED);
            } else {
                stepScore.replace(dir, 5);
                crossroad.getLights().get(dir).setState(LightColor.GREEN);
            }
        }
    }

    public void turnOnLights(Crossroad crossroad) {
        switchGreenVertical(crossroad);
    }
}