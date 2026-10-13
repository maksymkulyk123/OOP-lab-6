package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    @Test
    void priceHasPriority() {
        Car cheaper = new Car(15000, 2010, 80);
        Car expensive = new Car(20000, 2025, 300);
        assertTrue(cheaper.compareTo(expensive) > 0);
        assertTrue(expensive.compareTo(cheaper) < 0);
    }

    @Test
    void newerCarWinsWhenPricesMatch() {
        Car newer = new Car(18000, 2023, 100);
        Car older = new Car(18000, 2020, 250);
        assertTrue(newer.compareTo(older) > 0);
        assertTrue(older.compareTo(newer) < 0);
    }

    @Test
    void powerBreaksTheRemainingTie() {
        Car powerful = new Car(18000, 2023, 180);
        Car weak = new Car(18000, 2023, 140);
        assertTrue(powerful.compareTo(weak) > 0);
        assertEquals(0, powerful.compareTo(new Car(18000, 2023, 180)));
    }

    @Test
    void arrayIsSortedByAllThreeCriteria() {
        Car first = new Car(25000, 2021, 150);
        Car second = new Car(18000, 2020, 110);
        Car third = new Car(18000, 2023, 140);
        Car fourth = new Car(18000, 2023, 180);
        Car[] cars = {fourth, second, first, third};
        Arrays.sort(cars);
        assertArrayEquals(new Car[]{first, second, third, fourth}, cars);
    }

    @Test
    void subtractionDoesNotOverflow() {
        Car lowPrice = new Car(Integer.MIN_VALUE, 2020, 100);
        Car highPrice = new Car(Integer.MAX_VALUE, 2020, 100);
        assertTrue(lowPrice.compareTo(highPrice) > 0);
        assertTrue(highPrice.compareTo(lowPrice) < 0);
        assertTrue(new Car(1, Integer.MAX_VALUE, 100).compareTo(new Car(1, Integer.MIN_VALUE, 100)) > 0);
        assertTrue(new Car(1, 2020, Integer.MAX_VALUE).compareTo(new Car(1, 2020, Integer.MIN_VALUE)) > 0);
    }

    @Test
    void fieldsCanBeChanged() {
        Car car = new Car(20000, 2020, 100);
        car.setPrice(18000);
        car.setYear(2021);
        car.setHorsePower(120);
        assertEquals(18000, car.getPrice());
        assertEquals(2021, car.getYear());
        assertEquals(120, car.getHorsePower());
        assertEquals(0, car.compareTo(new Car(18000, 2021, 120)));
        assertThrows(ClassCastException.class, () -> car.compareTo("car"));
    }
}
