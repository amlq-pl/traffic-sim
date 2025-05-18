package pl.jakubjanor.model;

import java.util.LinkedList;
import java.util.Queue;

public class Road {
    Queue<Vehicle> vehicles = new LinkedList<>();

    public int getVehicleCount() {
        return vehicles.size();
    }
}
