package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Objects;

public class EventHandlersTest {
    SmartHome smartHome;

    @Before
    public void init(){
        this.smartHome = new JSONSmartHomeReader("smart-home-1.js").readHome();
    }

    @Test
    public void HandleDoorOpenTest(){
        smartHome.execute(new DoorEventHandler(smartHome).
                handleEvent(new SensorEvent(SensorEventType.DOOR_OPEN, "1")));
        Assert.assertTrue(Objects.requireNonNull(SmartHomeHelpers.getDoorById(smartHome, "1")).getOpen());
    }

    @Test
    public void HandleDoorCloseTest(){
        smartHome.execute(new DoorEventHandler(smartHome).
                handleEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, "1")));
        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getDoorById(smartHome, "1")).getOpen());
    }

    @Test
    public void HandleHallDoorOpenTest(){
        smartHome.execute(new HallDoorEventHandler(smartHome).
                handleEvent(new SensorEvent(SensorEventType.DOOR_OPEN, "4")));
        Assert.assertTrue(Objects.requireNonNull(SmartHomeHelpers.getDoorById(smartHome, "4")).getOpen());
    }

    @Test
    public void HandleHallDoorCloseTest(){
        smartHome.execute(new LightEventHandler(smartHome).
                handleEvent(new SensorEvent(SensorEventType.LIGHT_ON, "6")));
        smartHome.execute(new HallDoorEventHandler(smartHome).
                handleEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, "4")));
        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getDoorById(smartHome, "4")).getOpen());
        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "6")).isOn());
    }

    @Test
    public void HandleLightOnTest(){
        smartHome.execute(new LightEventHandler(smartHome).
                handleEvent(new SensorEvent(SensorEventType.LIGHT_ON, "6")));
        Assert.assertTrue(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "6")).isOn());
    }

    @Test
    public void HandleLightOffTest(){
        smartHome.execute(new LightEventHandler(smartHome).
                handleEvent(new SensorEvent(SensorEventType.LIGHT_OFF, "6")));
        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "6")).isOn());
    }
}
