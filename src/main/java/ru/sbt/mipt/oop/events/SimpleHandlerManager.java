package ru.sbt.mipt.oop.events;

import java.util.ArrayList;

public class SimpleHandlerManager implements HandlerManager{
    public SimpleHandlerManager(ArrayList<EventHandler> handlers){
        this.handlers = handlers;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        for (EventHandler handler:handlers){
            handler.handleEvent(event);
        }
    }

    private ArrayList<EventHandler> handlers;
}
