package ru.sbt.mipt.oop.events.alarm;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.alarm.handlers.*;

public class AlarmedHandlerConfigurator {
    public static AlarmHandler wrapHandler(EventHandler handler, Alarm alarm){
        AlarmHandler alarmedEventHandler = new AlarmActivateHandler(handler, alarm);
        alarmedEventHandler
                .setNextHandler(new AlarmDeactivateHandler(handler, alarm))
                .setNextHandler(new AlarmFireHandler(handler, alarm, new AlarmNotifier()))
                .setNextHandler(new AlarmNormalHandler(handler, alarm));
        return alarmedEventHandler;
    }
}
