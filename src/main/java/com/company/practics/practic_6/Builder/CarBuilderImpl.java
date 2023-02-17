package com.company.practics.practic_6.Builder;

public class CarBuilderImpl implements CarBuilder{
    private String make;
    private String model;
    private int year;
    private String color;

    @Override
    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public Car getResult() {
        return new Car(make, model, year, color);
    }

}
