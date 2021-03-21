package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.SensorCommand;

public class PrintCommandSender implements CommandSender {
    @Override
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
