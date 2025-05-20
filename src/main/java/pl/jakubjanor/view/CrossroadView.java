package pl.jakubjanor.view;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import pl.jakubjanor.model.CommandRunner;
import pl.jakubjanor.model.Crossroad;
import pl.jakubjanor.model.CrossroadAlgorithm;

public class CrossroadView {
    private final AnchorPane root = new AnchorPane();
    private Crossroad crossroad;
    private CrossroadAlgorithm algorithm;
    private CommandRunner runner;

    public CrossroadView() {
        root.setStyle("-fx-background-color: lightgreen;");

        Rectangle horizontalRoad = new Rectangle(0, 300, 800, 200);
        horizontalRoad.setFill(Color.DARKGRAY);

        Rectangle verticalRoad = new Rectangle(300, 0, 200, 800);
        verticalRoad.setFill(Color.DARKGRAY);

        Line centerH = new Line(0, 400, 800, 400);
        centerH.setStroke(Color.WHITE);
        centerH.getStrokeDashArray().addAll(10.0, 10.0);
        centerH.setStrokeWidth(2);

        Line centerV = new Line(400, 0, 400, 800);
        centerV.setStroke(Color.WHITE);
        centerV.getStrokeDashArray().addAll(10.0, 10.0);
        centerV.setStrokeWidth(2);

        Button stepButton = new Button("STEP");
        stepButton.setLayoutX(650);
        stepButton.setLayoutY(750);

        stepButton.setOnAction(e -> {
            if (this.runner.hasCommandLeft()) {
                this.runner.runNextCommand();
            }
        });

        root.getChildren().addAll(horizontalRoad, verticalRoad, centerH, centerV, stepButton);
    }

    public AnchorPane getRoot() {
        return root;
    }

    public void setCrossroad(Crossroad crossroad) {
        this.crossroad = crossroad;
    }

    public void setAlgorithm(CrossroadAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void setRunner(CommandRunner runner) {
        this.runner = runner;
    }
}
