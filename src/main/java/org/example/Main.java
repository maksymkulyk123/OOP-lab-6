package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Завдання 1. Абстрактний клас Animal");
        Animal[] animals = {new Dog("Рекс"), new Cat("Мурчик")};
        for (Animal animal : animals) {
            System.out.println(animal.getName() + ": " + animal.makeSound());
        }

        System.out.println("\nЗавдання 2. Інтерфейс Movable");
        Movable point = new Point(2, 3);
        System.out.println("Початкова точка: " + point);
        point.setX(4);
        point.setY(5);
        System.out.println("Після зміни координат: " + point);
        point.moveTo(-1.5, 7);
        System.out.println("Після переміщення: " + point.getX() + "; " + point.getY());

        System.out.println("\nЗавдання 3. Інтерфейси GPS і Cellular");
        Smartphone phone = new Smartphone(46.4825, 30.7233);
        GPS gps = phone;
        Cellular cellular = phone;
        System.out.println("Координати: " + Arrays.toString(gps.getCoordinates()));
        cellular.makeCall();
        cellular.receiveCall();

        System.out.println("\nЗавдання 4. Comparable: від гіршого до кращого автомобіля");
        Car[] cars = {
                new Car(18000, 2023, 180),
                new Car(25000, 2021, 150),
                new Car(18000, 2020, 110),
                new Car(18000, 2023, 140)
        };
        Arrays.sort(cars);
        for (Car car : cars) {
            System.out.println(car);
        }
    }
}

