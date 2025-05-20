package pl.jakubjanor.model;

import java.util.ArrayList;
import java.util.List;

public class Note {
    private final List<Vehicle> leftVehicles;

    public Note(List<Vehicle> vehicles) {
        this.leftVehicles = vehicles;
    }

    public Note() {
        this.leftVehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle v) {
        leftVehicles.add(v);
    }

    public List<Vehicle> getLeftVehicles() {
        return leftVehicles;
    }
}
