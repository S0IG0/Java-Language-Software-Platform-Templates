package com.company.practics.practic_6.AbstractFactory;
// Button и Checkbox - это интерфейсы для создания элементов управления.
// WinButton, WinCheckbox, MacButton и MacCheckbox - это конкретные реализации элементов управления.
// GUIFactory - это интерфейс для создания элементов управления в определенном стиле.
// WinFactory и MacFactory - это конкретные реализации GUIFactory.
public class Main {

    public static void main(String[] args) {
        WinFactory winFactory = new WinFactory();
        MacFactory macFactory = new MacFactory();

        for (int i = 0; i < 10; i++) {
            winFactory.createButton().paint();
            winFactory.createCheckbox().paint();

            macFactory.createButton().paint();
            macFactory.createCheckbox().paint();
        }
    }
}
