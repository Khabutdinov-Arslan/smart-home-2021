package ru.sbt.mipt.oop.remote_commands;

import ru.sbt.mipt.oop.components.SmartHome;

public class SmartHomeRemoteCommand implements RemoteCommand{
    protected final SmartHome smartHome;

    public SmartHomeRemoteCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {}
}
