package main.java.com.company.practics.practic_8.Command;

abstract public class Database implements ProjectElement{
    protected final String type;
    protected final String name;

    protected Database(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
