package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.events.actionable.DoorEventHandler;
import ru.sbt.mipt.oop.events.actionable.HallDoorEventHandler;
import ru.sbt.mipt.oop.events.actionable.LightEventHandler;
import ru.sbt.mipt.oop.events.alarm.AlarmedHandlerConfigurator;
import ru.sbt.mipt.oop.parsers.JSONSmartHomeReader;

import java.util.ArrayList;

public class Application {

    public static void main(String... args) {
        SmartHome smartHome = new JSONSmartHomeReader("smart-home-1.js").readHome();
        ArrayList<EventHandler> handlers = new ArrayList<>();
        handlers.add(AlarmedHandlerConfigurator.wrapHandler(new LightEventHandler(smartHome), smartHome.getAlarm()));
        handlers.add(AlarmedHandlerConfigurator.wrapHandler(new DoorEventHandler(smartHome), smartHome.getAlarm()));
        handlers.add(AlarmedHandlerConfigurator.wrapHandler(new HallDoorEventHandler(smartHome), smartHome.getAlarm()));
        EventLoop eventLoop = new EventLoop(smartHome, new SimpleHandlerManager(handlers));
        eventLoop.process();
    }


}
