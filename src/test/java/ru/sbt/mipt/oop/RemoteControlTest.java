package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rc.RemoteControl;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.remote_commands.ActivateAlarmRemoteCommand;
import ru.sbt.mipt.oop.remote_commands.RemoteCommand;
import ru.sbt.mipt.oop.remotes.SimpleRemoteControl;
import ru.sbt.mipt.oop.remotes.SimpleRemoteControlRegistry;

import java.util.HashMap;

public class RemoteControlTest {
    SimpleRemoteControlRegistry registry;

    @Before
    public void init(){
        this.registry = new SimpleRemoteControlRegistry();
    }

    @Test
    public void addRemoteTest(){
        RemoteControl remote = new SimpleRemoteControl(new HashMap<>());
        registry.registerRemoteControl(remote, "main");

        Assert.assertNotNull(registry.getRemoteControl("main"));
    }

    @Test
    public void setExecCommandTest(){
        Alarm alarm = new Alarm();
        RemoteCommand command = new ActivateAlarmRemoteCommand(alarm);
        HashMap<String, RemoteCommand> buttonCommands = new HashMap<>();
        buttonCommands.put("A", command);
        registry.registerRemoteControl(new SimpleRemoteControl(buttonCommands), "main");

        registry.onButtonPressed("A", "main");

        Assert.assertTrue(alarm.isActivated());
    }
}
