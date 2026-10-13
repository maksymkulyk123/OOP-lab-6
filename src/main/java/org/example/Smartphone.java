package org.example;

public class Smartphone implements GPS, Cellular {
    private double latitude;
    private double longitude;

    public Smartphone() {
        this(0, 0);
    }

    public Smartphone(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setCoordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public double[] getCoordinates() {
        return new double[]{latitude, longitude};
    }

    @Override
    public void makeCall() {
        System.out.println("Здійснюю дзвінок");
    }

    @Override
    public void receiveCall() {
        System.out.println("Приймаю вхідний дзвінок");
    }
}
