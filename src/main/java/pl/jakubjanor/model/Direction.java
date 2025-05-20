package pl.jakubjanor.model;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    static private final Map<String, Direction> directionMap = new HashMap<>();
    static {
        directionMap.put("north", NORTH);
        directionMap.put("south", SOUTH);
        directionMap.put("east", EAST);
        directionMap.put("west", WEST);
    }

    public static Direction fromString(String s) {
        return directionMap.get(s);
    }

    public Direction opposite() {
        return values()[(this.ordinal() + 2) % 4];
    }

    public Direction left() {
        return values()[(this.ordinal() + 3) % 4];
    }

    public Direction right() {
        return values()[(this.ordinal() + 1) % 4];
    }
}
