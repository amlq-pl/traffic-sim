package pl.jakubjanor.model;

import java.util.*;

public class Crossroad {
    private final Map<Direction, Queue<Vehicle>> roads = new HashMap<>();

    public Crossroad() {
        for (Direction direction : Direction.values()) {
            roads.put(direction, new LinkedList<>());
        }
    }

    public void addVehicle(Vehicle v) {
        Direction dir = v.startRoad();
        Queue<Vehicle> road = roads.get(dir);
        road.offer(v);
    }

    public List<Vehicle> moveVehicles() {
        return null;
    }

    public Vehicle exitCrossroad(Direction startDirection) {
        Queue<Vehicle> startRoad = roads.get(startDirection);
        return (startRoad.isEmpty()) ? null : startRoad.poll();
    }
}
