package pl.jakubjanor.model;

import java.util.HashMap;
import java.util.Map;

public class Crossroad {
    private final Map<Direction, Road> roads = new HashMap<>();

    public Crossroad() {
        for (Direction direction : Direction.values()) {
            roads.put(direction, new Road());
        }
    }

    public Vehicle exitCrossroad(Direction startDirection) {
        Road startRoad = roads.get(startDirection);
        return (startRoad.vehicles.isEmpty()) ? null : startRoad.vehicles.poll();
    }
}
