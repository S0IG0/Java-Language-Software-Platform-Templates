package com.company.practics.practic_8.Command;

// ConcreteCommand (конкретная команда)
public class ConcreteCommand implements Command {
    private final Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    public void execute() {
        receiver.action();
    }
}
