package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.events.actionable.DoorEventHandler;
import ru.sbt.mipt.oop.events.actionable.HallDoorEventHandler;
import ru.sbt.mipt.oop.events.actionable.LightEventHandler;
import ru.sbt.mipt.oop.events.alarm.Alarm;
import ru.sbt.mipt.oop.events.alarm.AlarmNotifier;
import ru.sbt.mipt.oop.events.alarm.AlarmedHandlerConfigurator;
import ru.sbt.mipt.oop.events.alarm.handlers.*;
import ru.sbt.mipt.oop.events.types.*;
import ru.sbt.mipt.oop.helpers.SmartHomeHelpers;

import java.util.*;

public class EventHandlersTest {
    SmartHome smartHome;

    @Before
    public void init(){
        Light hallLight = new Light("6", false);
        Light hallBackupLight = new Light("5", false);
        Door hallDoor = new Door(true, "4");
        Door basementDoor = new Door(false, "1");
        Room hall = new Room(Arrays.asList(hallLight, hallBackupLight),
                Arrays.asList(hallDoor, basementDoor), "hall");
        Door kitchenDoor = new Door(false, "3");
        Light kitchenLight = new Light("2", false);
        Room kitchen = new Room(Collections.singletonList(kitchenLight),
                Collections.singletonList(kitchenDoor), "kitchen");
        this.smartHome = new SmartHome(Arrays.asList(kitchen, hall));
    }

    @Test
    public void openDoorTest(){
        new DoorEventHandler(smartHome).handleEvent(new SensorEvent(new SensorEventDoorOpen(), "3"));

        Assert.assertTrue(Objects.requireNonNull(SmartHomeHelpers.getDoorById(smartHome, "3")).getOpen());
    }

    @Test
    public void closeDoorTest(){
        new DoorEventHandler(smartHome).handleEvent(new SensorEvent(new SensorEventDoorClose(), "1"));

        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getDoorById(smartHome, "1")).getOpen());
    }

    @Test
    public void openHallDoorTest(){
        new DoorEventHandler(smartHome).handleEvent(new SensorEvent(new SensorEventDoorOpen(), "4"));
        new HallDoorEventHandler(smartHome).handleEvent(new SensorEvent(new SensorEventDoorOpen(), "4"));

        Assert.assertTrue(Objects.requireNonNull(SmartHomeHelpers.getDoorById(smartHome, "4")).getOpen());
    }

    @Test
    public void closeHallDoorTest(){
        new DoorEventHandler(smartHome).handleEvent(new SensorEvent(new SensorEventDoorOpen(), "4"));
        new LightEventHandler(smartHome).handleEvent(new SensorEvent(new SensorEventLightOn(), "6"));
        new HallDoorEventHandler(smartHome).handleEvent(new SensorEvent(new SensorEventDoorClose(), "4"));

        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getDoorById(smartHome, "4")).getOpen());
        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "6")).isOn());
        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "5")).isOn());
        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "2")).isOn());
    }

    @Test
    public void turnLightOnTest(){
        new LightEventHandler(smartHome).handleEvent(new SensorEvent(new SensorEventLightOn(), "5"));

        Assert.assertTrue(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "5")).isOn());
    }

    @Test
    public void turnLightOffTest(){
        new LightEventHandler(smartHome).handleEvent(new SensorEvent(new SensorEventLightOff(), "6"));

        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "6")).isOn());
    }

    @Test
    public void triggerAlarmTest(){
        EventHandler handler = new LightEventHandler(smartHome);
        Alarm alarm = new Alarm();
        AlarmHandler alarmedEventHandler = AlarmedHandlerConfigurator.wrapHandler(handler, alarm);

        alarmedEventHandler.handleEvent(new SensorEvent(new SensorEventAlarmActivate(1234), "6"));
        alarmedEventHandler.handleEvent(new SensorEvent(new SensorEventLightOff(), "6"));

        assert(alarm.isFiring());
    }

    @Test
    public void deactivateAlarmTest(){
        EventHandler handler = new LightEventHandler(smartHome);
        Alarm alarm = new Alarm();
        AlarmHandler alarmedEventHandler = AlarmedHandlerConfigurator.wrapHandler(handler, alarm);

        alarmedEventHandler.handleEvent(new SensorEvent(new SensorEventAlarmActivate(1234), "6"));
        alarmedEventHandler.handleEvent(new SensorEvent(new SensorEventLightOff(), "6"));
        alarmedEventHandler.handleEvent(new SensorEvent(new SensorEventAlarmDeactivate(1234), "6"));
        assert(!alarm.isFiring());
    }
}
