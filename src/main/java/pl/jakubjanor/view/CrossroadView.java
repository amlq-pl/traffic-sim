package pl.jakubjanor.view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class CrossroadView {
    private Pane root = new Pane();
    private Map<String, Circle> trafficLights = new HashMap<>();
    private Map<String, ImageView> vehicles = new HashMap<>();

    private void drawStaticCrossroad() {
        // Background, roads, intersections
        Rectangle roadNS = new Rectangle(350, 0, 100, 800);
        roadNS.setFill(Color.DARKGRAY);
        root.getChildren().add(roadNS);

        Rectangle roadEW = new Rectangle(0, 350, 800, 100);
        roadEW.setFill(Color.DARKGRAY);
        root.getChildren().add(roadEW);

        // Add lights, just as example
        Circle northLight = new Circle(390, 100, 10, Color.RED);
        trafficLights.put("north", northLight);
        root.getChildren().add(northLight);

        // same for south, east, west...
    }
}
