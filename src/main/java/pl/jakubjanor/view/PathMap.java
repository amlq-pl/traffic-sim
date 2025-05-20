package pl.jakubjanor.view;

import javafx.scene.shape.ArcTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Pair;
import pl.jakubjanor.model.Direction;

import java.util.HashMap;
import java.util.Map;

public class PathMap {
    public static Map<Pair<Direction, Direction>, Path> pathMap = new HashMap<>();
    private static final Map<Direction, Pair<Double, Double>> startPos = new HashMap<>();
    private static final Map<Direction, Pair<Double, Double>> endPos = new HashMap<>();

    static {
        startPos.put(Direction.SOUTH, new Pair<>(450.0, 550.0));
        startPos.put(Direction.EAST, new Pair<>(550.0, 350.0));
        startPos.put(Direction.NORTH, new Pair<>(350.0, 250.0));
        startPos.put(Direction.WEST, new Pair<>(250.0, 450.0));

        endPos.put(Direction.SOUTH, new Pair<>(350.0, 550.0));
        endPos.put(Direction.EAST, new Pair<>(550.0, 450.0));
        endPos.put(Direction.NORTH, new Pair<>(450.0, 250.0));
        endPos.put(Direction.WEST, new Pair<>(250.0, 350.0));

        for (Direction start : Direction.values()) {
            for (Direction end : Direction.values()) {
                if (start == end) continue;

                Pair<Direction, Direction> key = new Pair<>(start, end);
                double startX = startPos.get(start).getKey();
                double startY = startPos.get(start).getValue();
                double endX = endPos.get(end).getKey();
                double endY = endPos.get(end).getValue();

                if (end == start.left()) {
                    pathMap.put(key, createTurn(startX, startY, endX, endY, true));
                } else if (end == start.opposite()) {
                    pathMap.put(key, createStraight(startX, startY, endX, endY));
                } else if (end == start.right()) {
                    pathMap.put(key, createTurn(startX, startY, endX, endY, false));
                }
            }
        }
    }

    private static Path createTurn(double startX, double startY, double endX, double endY, boolean leftTurn) {
        Path path = new Path();
        path.getElements().add(new MoveTo(startX, startY));
        ArcTo arcTo = new ArcTo();
        arcTo.setX(endX);
        arcTo.setY(endY);
        double base = 100.0;
        if (!leftTurn) base *= 2;
        arcTo.setRadiusX(base);
        arcTo.setRadiusY(base);
        arcTo.setSweepFlag(leftTurn);
        path.getElements().add(arcTo);
        return path;
    }

    private static Path createStraight(double startX, double startY, double endX, double endY) {
        Path path = new Path();
        path.getElements().add(new MoveTo(startX, startY));
        path.getElements().add(new LineTo(endX, endY));
        return path;
    }
}
