package ru.sbt.mipt.oop.sensor_commands;

public class PrintCommandSender implements CommandSender {
    @Override
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
