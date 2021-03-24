package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.parsers.JSONSmartHomeReader;

import java.util.ArrayList;

public class Application {

    public static void main(String... args) {
        SmartHome smartHome = new JSONSmartHomeReader("smart-home-1.js").readHome();
        ArrayList<EventHandler> handlers = new ArrayList<>();
        handlers.add(new LightEventHandler(smartHome));
        handlers.add(new DoorEventHandler(smartHome));
        handlers.add(new HallDoorEventHandler(smartHome));
        EventLoop eventLoop = new EventLoop(smartHome, new SimpleHandlerManager(handlers));
        eventLoop.process();
    }


}
