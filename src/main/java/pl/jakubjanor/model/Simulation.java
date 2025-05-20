package pl.jakubjanor.model;

import pl.jakubjanor.view.CrossroadView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Queue;

public class Simulation {
    private final CommandRunner runner;
    private final String outputFile;

    public Simulation(String inputFile, String outputFile) {
        this.outputFile = outputFile;
        String inputJSON = "";
        try {
            inputJSON = Files.readString(Paths.get(inputFile));
        } catch (IOException e) {
            System.out.println("Input file is malfunctioned");
        }

        Crossroad crossroad = new Crossroad();
        Queue<Command> commands = CommandParser.parseCommands(inputJSON);
        this.runner = new CommandRunner(commands, crossroad);
    }
    public void run() {
        while (this.runner.hasCommandLeft()) {
            this.runner.runNextCommand();
        }

        List<Note> vehicleNotes = this.runner.getVehicleNotes();
        String outputJSON = CommandParser.writeNotesToJSON(vehicleNotes);
        try {
            Files.writeString(Paths.get(this.outputFile), outputJSON);
        } catch (IOException e) {
            System.out.println("Output file is malfunctioned");
        }
    }

    public void simulate(CrossroadView view) {
        view.setRunner(this.runner);
        Crossroad crossroad = new Crossroad();
        CrossroadAlgorithm algorithm = new CrossroadAlgorithm(crossroad);
        view.setAlgorithm(algorithm);
        view.setCrossroad(crossroad);
    }
}
