package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.events.generator.RandomSensorEventGenerator;

public class EventLoop {
    public EventLoop(SmartHome smartHome, HandlerManager handlerManager){
        this.smartHome = smartHome;
        this.handlerManager = handlerManager;
    }

    public void process(){
        // создаём обработчик

        // начинаем цикл обработки событий
        RandomSensorEventGenerator generator = new RandomSensorEventGenerator();
        SensorEvent event = generator.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            handlerManager.handleEvent(event);
            event = generator.getNextSensorEvent();
        }
    }

    SmartHome smartHome;
    HandlerManager handlerManager;
}
