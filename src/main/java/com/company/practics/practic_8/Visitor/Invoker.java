package com.company.practics.practic_8.Visitor;

// Invoker (вызывающий)
public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}
