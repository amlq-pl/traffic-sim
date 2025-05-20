package pl.jakubjanor.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CommandParser {
    public static Queue<Command> parseCommands(String jsonString) {
        Queue<Command> commands = new LinkedList<>();
        JSONObject root = new JSONObject(jsonString);
        JSONArray arr = root.getJSONArray("commands");

        for (int i = 0; i < arr.length(); i++) {
            JSONObject cmd = arr.getJSONObject(i);
            String type = cmd.getString("type");

            switch (type) {
                case "addVehicle" -> {
                    String vehicleId = cmd.getString("vehicleId");
                    String startRoad = cmd.getString("startRoad");
                    String endRoad = cmd.getString("endRoad");
                    commands.add(new AddVehicleCommand(vehicleId, startRoad, endRoad));
                }
                case "step" -> commands.add(new StepCommand());
                default -> throw new IllegalArgumentException("Unknown command type: " + type);
            }
        }

        return commands;
    }

    public static String writeNotesToJSON(List<Note> notes) {
        JSONArray stepStatuses = new JSONArray();

        for (Note note : notes) {
            JSONObject stepResult = new JSONObject();
            JSONArray left = new JSONArray();

            for (Vehicle v : note.getLeftVehicles()) {
                left.put(v.vehicleId());
            }
            stepResult.put("leftVehicles", left);
            stepStatuses.put(stepResult);
        }

        JSONObject result = new JSONObject();
        result.put("stepStatuses", stepStatuses);

        return result.toString(2);
    }
}
