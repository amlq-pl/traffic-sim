package pl.jakubjanor.model;

public class TrafficLight {
    private LightColor state = LightColor.RED;
    private final Direction road;

    public TrafficLight(Direction road) {
        this.road = road;
    }

    public void transition() {
        switch (state) {
            case RED:
                state = LightColor.GREEN;
                break;
            case YELLOW:
                state = LightColor.RED;
                break;
            case GREEN:
                state = LightColor.YELLOW;
                break;
        }
    }

    public void setState(LightColor color) {
        this.state = color;
    }

    public Direction getRoad() {
        return road;
    }

    public LightColor getState() {
        return state;
    }
}
