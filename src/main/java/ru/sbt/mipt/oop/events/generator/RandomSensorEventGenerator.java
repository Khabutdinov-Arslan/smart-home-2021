package ru.sbt.mipt.oop.events.generator;

import ru.sbt.mipt.oop.events.SensorEvent;

import ru.sbt.mipt.oop.events.types.SensorEventDoorOpen;
import ru.sbt.mipt.oop.events.types.SensorEventType;

public class RandomSensorEventGenerator implements SensorEventGenerator {
    @Override
    public SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = new SensorEventDoorOpen();
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
