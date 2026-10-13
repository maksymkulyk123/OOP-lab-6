package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTest {
    @Test
    void constructors() {
        Point empty = new Point();
        assertEquals(0, empty.getX(), 0.000001);
        assertEquals(0, empty.getY(), 0.000001);
        Point point = new Point(2.5, -3);
        assertEquals(2.5, point.getX(), 0.000001);
        assertEquals(-3, point.getY(), 0.000001);
    }

    @Test
    void moveThroughInterface() {
        Movable point = new Point(1, 2);
        point.setX(4);
        point.setY(5);
        assertEquals(4, point.getX(), 0.000001);
        assertEquals(5, point.getY(), 0.000001);
        point.moveTo(-1.5, 7.25);
        assertEquals(-1.5, point.getX(), 0.000001);
        assertEquals(7.25, point.getY(), 0.000001);
    }
}
