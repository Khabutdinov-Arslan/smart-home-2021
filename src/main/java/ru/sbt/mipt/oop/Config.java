package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.events.CCSensorEventAdaptor;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.actionable.DoorEventHandler;
import ru.sbt.mipt.oop.events.actionable.HallDoorEventHandler;
import ru.sbt.mipt.oop.events.actionable.LightEventHandler;
import ru.sbt.mipt.oop.events.alarm.Alarm;
import ru.sbt.mipt.oop.events.alarm.AlarmedHandlerConfigurator;
import ru.sbt.mipt.oop.parsers.JSONSmartHomeReader;

import java.util.Collection;

public class Config {
    @Bean
    Alarm alarm(){
        return new Alarm();
    }

    @Bean
    SmartHome smartHome(){
        return new JSONSmartHomeReader("smart-home-1.js").readHome();
    }

    @Bean
    EventHandler doorEventHandler(Alarm alarm, SmartHome smartHome){
        return new CCSensorEventAdaptor(AlarmedHandlerConfigurator.wrapHandler(new DoorEventHandler(smartHome), alarm));
    }

    @Bean
    EventHandler hallDoorEventHandler(Alarm alarm, SmartHome smartHome){
        return new CCSensorEventAdaptor(AlarmedHandlerConfigurator.wrapHandler(new HallDoorEventHandler(smartHome), alarm));
    }

    @Bean
    EventHandler lightEventHandler(Alarm alarm, SmartHome smartHome){
        return new CCSensorEventAdaptor(AlarmedHandlerConfigurator.wrapHandler(new LightEventHandler(smartHome), alarm));
    }

    @Bean
    SensorEventsManager sensorEventsManager(Collection<EventHandler> handlers){
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        for (EventHandler handler:handlers){
            sensorEventsManager.registerEventHandler(handler);
        }
        return sensorEventsManager;
    }
}
