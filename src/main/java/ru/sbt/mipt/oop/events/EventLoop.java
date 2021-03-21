package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.components.SmartHome;

import java.util.ArrayList;

public class EventLoop {
    public EventLoop(SmartHome smartHome, ArrayList<EventHandler> handlers){
        this.smartHome = smartHome;
        this.handlers = handlers;
    }

    public void process(){
        // создаём обработчик

        // начинаем цикл обработки событий
        RandomSensorEventGenerator generator = new RandomSensorEventGenerator();
        SensorEvent event = generator.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventHandler handler:handlers){
                handler.handleEvent(event);
            }
            event = generator.getNextSensorEvent();
        }
    }

    SmartHome smartHome;
    ArrayList<EventHandler> handlers;
}
