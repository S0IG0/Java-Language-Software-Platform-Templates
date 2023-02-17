package com.company.practics.practic_6.Builder;

public interface CarBuilder {
    void setMake(String make);

    void setModel(String model);

    void setYear(int year);

    void setColor(String color);

    Car getResult();
}
