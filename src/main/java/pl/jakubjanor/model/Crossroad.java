package pl.jakubjanor.model;

import java.util.*;

public class Crossroad {
    private final Map<Direction, Queue<Vehicle>> roads = new HashMap<>();
    private final Map<Direction, TrafficLight> lights = new HashMap<>();

    public Crossroad() {
        for (Direction direction : Direction.values()) {
            roads.put(direction, new LinkedList<>());
            lights.put(direction, new TrafficLight(direction));
        }
    }

    public void addVehicle(Vehicle v) {
        Direction dir = v.startRoad();
        Queue<Vehicle> road = roads.get(dir);
        road.offer(v);
    }

    public Vehicle moveVehicle(Direction dir) {
        if (!roads.get(dir).isEmpty()) {
            return roads.get(dir).poll();
        } else return null;
    }

    public List<Vehicle> moveVehicles() {
        ArrayList<Vehicle> left = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            if (lights.get(direction).getState() == LightColor.GREEN) {
                Vehicle v = moveVehicle(direction);

                if (v != null) {
                    left.add(v);
                }
            }
        }
        return left;
    }

    public Map<Direction, Queue<Vehicle>> getRoads() {
        return roads;
    }

    public Map<Direction, TrafficLight> getLights() {
        return lights;
    }
}
