package pl.jakubjanor;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.jakubjanor.model.Simulation;
import pl.jakubjanor.view.CrossroadView;
import java.util.List;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        List<String> args = getParameters().getRaw();

        if (args.size() != 2) {
            System.out.println("Expected 2 arguments: inputFile outputFile");
            System.exit(1);
        }

        String inputFile = args.get(0);
        String outputFile = args.get(1);

        CrossroadView root = new CrossroadView();

        Simulation runner = new Simulation(inputFile, outputFile);
        runner.run();

        Simulation sim = new Simulation(inputFile, outputFile);
        sim.simulate(root);

        stage.setScene(new Scene(root.getRoot(), 800, 800));
        stage.setTitle("Smart Crossroad Simulator");
        stage.show();
    }
}
