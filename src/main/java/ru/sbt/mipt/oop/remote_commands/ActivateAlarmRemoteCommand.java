package ru.sbt.mipt.oop.remote_commands;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.events.types.SensorEventAlarmActivate;

public class ActivateAlarmRemoteCommand extends AlarmRemoteCommand {

    public ActivateAlarmRemoteCommand(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void execute(){
        alarm.handleEvent(new SensorEvent(new SensorEventAlarmActivate(1234), "0"));
    }
}
