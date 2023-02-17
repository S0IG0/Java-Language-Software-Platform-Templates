package com.company.practics.practic_6.Builder;
// `Car` - это класс, объекты которого мы будем строить.
// `CarBuilder` - это интерфейс, определяющий методы для установки свойств машины.
// `CarBuilderImpl` - это реализация `CarBuilder`,
// в которой мы устанавливаем значения свойств и возвращаем готовый объект `Car`.
public class Main {

    public static void main(String[] args) {
        CarBuilder carBuilder = new CarBuilderImpl(){{
            setColor("red");
            setModel("S");
            setMake("X");
            setYear(2012);
        }};

        Car[] oldCars = new Car[]{
                carBuilder.getResult(),
                carBuilder.getResult(),
                carBuilder.getResult(),
                carBuilder.getResult(),
        };
        // 5 years late...
        carBuilder.setYear(2017);
        Car[] newCars = new Car[]{
                carBuilder.getResult(),
                carBuilder.getResult(),
                carBuilder.getResult(),
                carBuilder.getResult(),
        };

        for (Car car : oldCars) {
            System.out.println(car);
        }

        for (Car car : newCars) {
            System.out.println(car);
        }
    }
}
