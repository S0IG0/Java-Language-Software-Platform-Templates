package com.company.practics.practic_8.Command;

// команда представлена интерфейсом Command,
// а ее конкретная реализация ConcreteCommand содержит ссылку на получателя Receiver.
// Вызывающий Invoker запрашивает выполнение команды у объекта Command, который затем вызывает операцию получателя,
// связанную с выполнением запроса.
// Клиент создает объекты ConcreteCommand и Receiver,
// устанавливает получателя для команды, и вызывает команду через вызывающего объекта Invoker.
public class Client {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        Invoker invoker = new Invoker();

        invoker.setCommand(command);
        invoker.executeCommand();
    }
}
