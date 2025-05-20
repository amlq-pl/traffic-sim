package pl.jakubjanor.model;

public interface Command {
    String getType();
    Note execute(Crossroad crossroad, CrossroadAlgorithm algorithm);
}
