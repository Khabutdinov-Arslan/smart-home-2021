package ru.sbt.mipt.oop.remotes;

import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.remote_commands.RemoteCommand;

import java.util.HashMap;

public class SimpleRemoteControlRegistry extends RemoteControlRegistry {
    HashMap<String, RemoteControl> remoteControls;

    public SimpleRemoteControlRegistry(){
        remoteControls = new HashMap<>();
    }

    @Override
    public void registerRemoteControl(RemoteControl remoteControl, String rcId) {
        if (!remoteControls.containsKey(rcId)) {
            remoteControls.put(rcId, remoteControl);
        }
    }

    public RemoteControl getRemoteControl(String rcId){
        return remoteControls.getOrDefault(rcId, null);
    }

    public void onButtonPressed(String buttonCode, String rcId) {
        if (remoteControls.containsKey(rcId)){
            remoteControls.get(rcId).onButtonPressed(buttonCode);
        }
    }

    public void changeButtonAction(String buttonCode, String rcId, RemoteCommand command){
        if (remoteControls.containsKey(rcId) && remoteControls.get(rcId) instanceof SimpleRemoteControl){
            ((SimpleRemoteControl) remoteControls.get(rcId)).changeButtonAction(buttonCode, command);
        }
    }
}
