package pl.jakubjanor;

import pl.jakubjanor.model.Simulation;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Number of arguments not appropriate");
        } else {
            String inputFile = args[0];
            String outputFile = args[1];

            Simulation sim = new Simulation(inputFile, outputFile);
            sim.run();
        }
    }
}