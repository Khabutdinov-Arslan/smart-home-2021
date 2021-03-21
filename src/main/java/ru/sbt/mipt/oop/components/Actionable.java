package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.events.Action;

public interface Actionable {
    void execute(Action action);
}
