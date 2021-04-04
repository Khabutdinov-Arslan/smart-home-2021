package ru.sbt.mipt.oop.remotes;

import rc.RemoteControl;
import ru.sbt.mipt.oop.remote_commands.RemoteCommand;

import java.util.HashMap;

public class SimpleRemoteControl implements RemoteControl {
    private final HashMap<String, RemoteCommand> buttonCommands;

    public SimpleRemoteControl() {
        this.buttonCommands = new HashMap<>();
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        if (buttonCommands.containsKey(buttonCode)){
            buttonCommands.get(buttonCode).execute();
        }
    }

    public void changeButtonAction(String buttonCode, RemoteCommand command){
        buttonCommands.put(buttonCode, command);
    }

}
