package pl.jakubjanor.model;

public class TrafficLight {
    private LightColor state;

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

    public LightColor getState() {
        return state;
    }
}
