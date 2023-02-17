package main.java.com.company.practics.practic_8.Visitor;

// ConcreteCommand (конкретная команда)
public class ConcreteCommand implements Command {
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    public void execute() {
        receiver.action();
    }
}