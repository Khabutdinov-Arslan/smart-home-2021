package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.types.SensorEventAlarmActivate;
import ru.sbt.mipt.oop.helpers.SmartHomeHelpers;
import ru.sbt.mipt.oop.remote_commands.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class RemoteCommandsTest {
    SmartHome smartHome;
    Alarm alarm;

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
        this.alarm = new Alarm();
    }

    @Test
    public void turnOnLightsTest(){
        RemoteCommand command = new TurnOnLightsRemoteCommand(smartHome);
        command.execute();

        Assert.assertTrue(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "6")).isOn());
        Assert.assertTrue(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "5")).isOn());
        Assert.assertTrue(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "2")).isOn());
    }

    @Test
    public void turnOnHallLightsTest(){
        RemoteCommand command = new TurnOnHallLightsRemoteCommand(smartHome);
        command.execute();

        Assert.assertTrue(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "6")).isOn());
        Assert.assertTrue(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "5")).isOn());
        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "2")).isOn());
    }

    @Test
    public void turnOffLightsTest(){
        RemoteCommand command = new TurnOffLightsRemoteCommand(smartHome);
        command.execute();
        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "6")).isOn());
        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "5")).isOn());
        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getLightById(smartHome, "2")).isOn());
    }

    @Test
    public void hallDoorCloseTest(){
        RemoteCommand command = new CloseHallDoorRemoteCommand(smartHome);
        command.execute();

        Assert.assertFalse(Objects.requireNonNull(SmartHomeHelpers.getDoorById(smartHome, "4")).getOpen());
    }


    @Test
    public void alarmActivateTest(){
        RemoteCommand command = new ActivateAlarmRemoteCommand(alarm);
        command.execute();

        Assert.assertTrue(alarm.isActivated());
    }

    @Test
    public void alarmFireTest(){
        RemoteCommand command = new FireAlarmRemoteCommand(alarm);
        alarm.activate(1234);
        command.execute();

        Assert.assertTrue(alarm.isFiring());
    }
}
