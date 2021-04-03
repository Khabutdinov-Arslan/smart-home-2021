package ru.sbt.mipt.oop.events;

public interface AlarmState {
    void handleEvent(SensorEvent event);
}
