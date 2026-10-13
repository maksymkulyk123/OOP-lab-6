package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class SmartphoneTest {
    @Test
    void coordinatesCanBeReadAndChanged() {
        Smartphone phone = new Smartphone();
        GPS gps = phone;
        assertArrayEquals(new double[]{0, 0}, gps.getCoordinates(), 0.000001);
        phone.setCoordinates(46.4825, 30.7233);
        assertArrayEquals(new double[]{46.4825, 30.7233}, gps.getCoordinates(), 0.000001);
        double[] coordinates = gps.getCoordinates();
        coordinates[0] = 0;
        assertEquals(46.4825, gps.getCoordinates()[0], 0.000001);
    }

    @Test
    void bothCallMethodsWorkThroughInterface() {
        Cellular phone = new Smartphone(1, 2);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;
        try (PrintStream stream = new PrintStream(output, true, StandardCharsets.UTF_8)) {
            System.setOut(stream);
            phone.makeCall();
            phone.receiveCall();
        } finally {
            System.setOut(original);
        }
        String text = output.toString(StandardCharsets.UTF_8);
        assertTrue(text.contains("Здійснюю дзвінок"));
        assertTrue(text.contains("Приймаю вхідний дзвінок"));
    }
}
