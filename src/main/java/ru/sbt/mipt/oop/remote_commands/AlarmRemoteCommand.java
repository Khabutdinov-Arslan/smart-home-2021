package ru.sbt.mipt.oop.remote_commands;

import ru.sbt.mipt.oop.alarm.Alarm;

public class AlarmRemoteCommand implements RemoteCommand{
    protected final Alarm alarm;

    AlarmRemoteCommand(Alarm alarm){
        this.alarm = alarm;
    }

    @Override
    public void execute() {}
}
