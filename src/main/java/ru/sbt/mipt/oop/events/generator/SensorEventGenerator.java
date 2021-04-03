package ru.sbt.mipt.oop.events.generator;

import ru.sbt.mipt.oop.events.SensorEvent;

public interface SensorEventGenerator {
    SensorEvent getNextSensorEvent();
}
