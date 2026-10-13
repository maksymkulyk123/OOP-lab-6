package org.example;

public class Car implements Comparable {
    private int price;
    private int year;
    private int horsePower;

    public Car(int price, int year, int horsePower) {
        this.price = price;
        this.year = year;
        this.horsePower = horsePower;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public int compareTo(Object object) {
        Car other = (Car) object;
        // Різницю рахуємо в long, щоб вона не переповнювала int.
        long difference = (long) other.price - price;
        if (difference == 0) {
            difference = (long) year - other.year;
        }
        if (difference == 0) {
            difference = (long) horsePower - other.horsePower;
        }
        if (difference > 0) {
            return 1;
        }
        if (difference < 0) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Автомобіль: ціна " + price + ", рік " + year + ", потужність " + horsePower + " к.с.";
    }
}
