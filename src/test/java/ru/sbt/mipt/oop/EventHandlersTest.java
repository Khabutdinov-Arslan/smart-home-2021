package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.events.*;
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
        new DoorEventHandler(smartHome).handleEvent(new SensorEvent(SensorEventType.DOOR_OPEN, "3"));

        Assert.assertTrue(Objects.requireNonNull(SmartHomeHelpers.getDoorById(smartHome, "3")).getOpen());
    }

    @Test
    public void closeDoorTest(){
        new DoorEventHandler(smartHome).handleEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, "1"));

        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getDoorById(smartHome, "1")).getOpen());
    }

    @Test
    public void openHallDoorTest(){
        new DoorEventHandler(smartHome).handleEvent(new SensorEvent(SensorEventType.DOOR_OPEN, "4"));
        new HallDoorEventHandler(smartHome).handleEvent(new SensorEvent(SensorEventType.DOOR_OPEN, "4"));

        Assert.assertTrue(Objects.requireNonNull(SmartHomeHelpers.getDoorById(smartHome, "4")).getOpen());
    }

    @Test
    public void closeHallDoorTest(){
        new DoorEventHandler(smartHome).handleEvent(new SensorEvent(SensorEventType.DOOR_OPEN, "4"));
        new LightEventHandler(smartHome).handleEvent(new SensorEvent(SensorEventType.LIGHT_ON, "6"));
        new HallDoorEventHandler(smartHome).handleEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, "4"));

        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getDoorById(smartHome, "4")).getOpen());
        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "6")).isOn());
        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "5")).isOn());
        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "2")).isOn());
    }

    @Test
    public void turnLightOnTest(){
        new LightEventHandler(smartHome).handleEvent(new SensorEvent(SensorEventType.LIGHT_ON, "5"));

        Assert.assertTrue(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "5")).isOn());
    }

    @Test
    public void turnLightOffTest(){
        new LightEventHandler(smartHome).handleEvent(new SensorEvent(SensorEventType.LIGHT_OFF, "6"));

        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "6")).isOn());
    }
}
