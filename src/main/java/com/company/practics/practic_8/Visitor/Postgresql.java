package com.company.practics.practic_8.Visitor;

public class Postgresql extends Database{
    public final String host;
    public Postgresql(String type, String name, String host) {
        super(type, name);
        this.host = host;
    }

    @Override
    public void beWritten(Developer developer) {
        developer.create(this);
    }

    @Override
    public String toString() {
        return "Postgresql{" +
                "host='" + host + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
