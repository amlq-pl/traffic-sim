package pl.jakubjanor.model;

public record AddVehicleCommand(String vehicleId, String startRoad, String endRoad) implements Command {

    @Override
    public String getType() {
        return "addVehicle";
    }

    @Override
    public Note execute(Crossroad crossroad, CrossroadAlgorithm algorithm) {
        Vehicle v = new Vehicle(vehicleId, Direction.fromString(startRoad),
                Direction.fromString(endRoad));
        crossroad.addVehicle(v);
        return null;
    }
}

