package pl.jakubjanor.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class CrossroadAlgorithm {
    private final int BASE_GREEN_SCORE = 5;
    private boolean verticalGreen;
    private int greenScore;

    public CrossroadAlgorithm(Crossroad crossroad) {
        turnOnLights(crossroad);

    }

    public void updateLights(Crossroad crossroad) {
        Map<Direction, Integer> tempScore = new HashMap<>();
        for (Direction direction : Direction.values()) {
            Queue<Vehicle> road = crossroad.getRoads().get(direction);
            int cnt = road.size();
            tempScore.put(direction, cnt);
        }
        int vertSum = tempScore.get(Direction.NORTH) + tempScore.get(Direction.SOUTH);
        int horSum = tempScore.get(Direction.WEST) + tempScore.get(Direction.EAST);

        if (verticalGreen) {
            if (vertSum == 0 || greenScore + vertSum < horSum)
                switchToHorizontal(crossroad);
            else greenScore -= 1;
        } else {
            if (horSum == 0 || greenScore + horSum < vertSum)
                switchToVertical(crossroad);
            else greenScore -= 1;
        }
    }

    private void switchToVertical(Crossroad crossroad) {
        crossroad.getLights().get(Direction.EAST).setState(LightColor.RED);
        crossroad.getLights().get(Direction.WEST).setState(LightColor.RED);

        crossroad.getLights().get(Direction.NORTH).setState(LightColor.GREEN);
        crossroad.getLights().get(Direction.SOUTH).setState(LightColor.GREEN);

        verticalGreen = true;
        greenScore = BASE_GREEN_SCORE;
    }

    private void switchToHorizontal(Crossroad crossroad) {
        crossroad.getLights().get(Direction.NORTH).setState(LightColor.RED);
        crossroad.getLights().get(Direction.SOUTH).setState(LightColor.RED);

        crossroad.getLights().get(Direction.EAST).setState(LightColor.GREEN);
        crossroad.getLights().get(Direction.WEST).setState(LightColor.GREEN);

        verticalGreen = false;
        greenScore = BASE_GREEN_SCORE;
    }

    public void turnOnLights(Crossroad crossroad) {
        switchToVertical(crossroad);
    }
}