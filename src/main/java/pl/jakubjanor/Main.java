package pl.jakubjanor;

import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import pl.jakubjanor.model.Direction;
import pl.jakubjanor.model.Simulation;
import pl.jakubjanor.view.CarSprite;
import pl.jakubjanor.view.PathMap;

import java.util.Arrays;
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
        System.out.println(inputFile);
        String outputFile = args.get(1);
        System.out.println(outputFile);

        Simulation sim = new Simulation(inputFile, outputFile);
        sim.run();

        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: lightgreen;");

        Rectangle horizontalRoad = new Rectangle(0, 300, 800, 200); // x, y, width, height
        horizontalRoad.setFill(Color.DARKGRAY);

        Rectangle verticalRoad = new Rectangle(300, 0, 200, 800); // x, y, width, height
        verticalRoad.setFill(Color.DARKGRAY);

        Line centerH = new Line(0, 400, 800, 400);
        centerH.setStroke(Color.WHITE);
        centerH.getStrokeDashArray().addAll(10.0, 10.0);
        centerH.setStrokeWidth(2);

        Line centerV = new Line(400, 0, 400, 800);
        centerV.setStroke(Color.WHITE);
        centerV.getStrokeDashArray().addAll(10.0, 10.0);
        centerV.setStrokeWidth(2);

        root.getChildren().addAll(horizontalRoad, verticalRoad, centerH, centerV);

        Path path1 = PathMap.pathMap.get(new Pair<>(Direction.SOUTH, Direction.NORTH));
        CarSprite sprite1 = new CarSprite("car.png", 100.0, 5.0, false);
        sprite1.driveAlong(path1, 1);

        Path path2 = PathMap.pathMap.get(new Pair<>(Direction.NORTH, Direction.EAST));
        CarSprite sprite2 = new CarSprite("car.png", 100.0, 5.0, true);
        sprite2.driveAlong(path2, 1);

        root.getChildren().addAll(sprite1.getView());
        root.getChildren().addAll(sprite2.getView());

        stage.setScene(new Scene(root, 800, 800));
        stage.setTitle("Smart Crossroad Simulator");
        stage.show();
    }
}
