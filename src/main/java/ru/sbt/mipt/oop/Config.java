package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.events.CCSensorEventAdaptor;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.events.actionable.DoorEventHandler;
import ru.sbt.mipt.oop.events.actionable.HallDoorEventHandler;
import ru.sbt.mipt.oop.events.actionable.LightEventHandler;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.events.alarm.AlarmedHandlerConfigurator;
import ru.sbt.mipt.oop.events.types.*;
import ru.sbt.mipt.oop.parsers.JSONSmartHomeReader;
import ru.sbt.mipt.oop.remote_commands.*;
import ru.sbt.mipt.oop.remotes.SimpleRemoteControl;
import ru.sbt.mipt.oop.remotes.SimpleRemoteControlRegistry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
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
    HashMap<String, SensorEventType> eventTypeHashMap(){
        HashMap<String, SensorEventType> convertor = new HashMap<>();
        convertor.put("LightIsOn", new SensorEventLightOn());
        convertor.put("LightIsOff", new SensorEventLightOff());
        convertor.put("DoorIsOpen", new SensorEventDoorOpen());
        convertor.put("DoorIsClosed", new SensorEventDoorClose());
        convertor.put("DoorIsLocked", new SensorEventAlarmActivate(1234));
        convertor.put("DoorIsUnlocked", new SensorEventAlarmDeactivate(1234));
        return convertor;
    }

    @Autowired
    HashMap<String, SensorEventType> eventAdapterConfig;

    @Bean
    EventHandler doorEventHandler(Alarm alarm, SmartHome smartHome){
        return new CCSensorEventAdaptor(
                AlarmedHandlerConfigurator.wrapHandler(new DoorEventHandler(smartHome), alarm), eventAdapterConfig);
    }

    @Bean
    EventHandler hallDoorEventHandler(Alarm alarm, SmartHome smartHome){
        return new CCSensorEventAdaptor(
                AlarmedHandlerConfigurator.wrapHandler(new HallDoorEventHandler(smartHome), alarm), eventAdapterConfig);
    }

    @Bean
    EventHandler lightEventHandler(Alarm alarm, SmartHome smartHome){
        return new CCSensorEventAdaptor(
                AlarmedHandlerConfigurator.wrapHandler(new LightEventHandler(smartHome), alarm), eventAdapterConfig);
    }

    @Bean
    SensorEventsManager sensorEventsManager(Collection<EventHandler> handlers){
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        for (EventHandler handler:handlers){
            sensorEventsManager.registerEventHandler(handler);
        }
        return sensorEventsManager;
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry(){
        return new SimpleRemoteControlRegistry();
    }

    @Bean
    RemoteControl defaultRemoteControl(Alarm alarm, SmartHome smartHome){
        HashMap<String, RemoteCommand> buttonCommands = new HashMap<>();
        buttonCommands.put("A", new TurnOnLightsRemoteCommand(smartHome));
        buttonCommands.put("B", new TurnOffLightsRemoteCommand(smartHome));
        buttonCommands.put("C", new TurnOnHallLightsRemoteCommand(smartHome));
        buttonCommands.put("D", new CloseHallDoorRemoteCommand(smartHome));
        buttonCommands.put("1", new FireAlarmRemoteCommand(alarm));
        buttonCommands.put("2", new ActivateAlarmRemoteCommand(alarm));
        return new SimpleRemoteControl(buttonCommands);
    }

}
