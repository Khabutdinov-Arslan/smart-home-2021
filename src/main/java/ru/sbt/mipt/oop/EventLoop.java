package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class EventLoop {
    EventLoop(SmartHome smartHome, List<EventHandler> handlers, RandomSensorEventGenerator generator){
        this.smartHome = smartHome;
        this.handlers = handlers;
        this.generator = generator;
    }

    void process(){
        // создаём обработчик

        // начинаем цикл обработки событий

        SensorEvent event = generator.getNextSensorEvent();
        while (event != null) {
            for (EventHandler handler:handlers){
                handler.handleEvent(event);
            }
            event = generator.getNextSensorEvent();
        }
    }

    SmartHome smartHome;
    List<EventHandler> handlers;
    RandomSensorEventGenerator generator;
}
