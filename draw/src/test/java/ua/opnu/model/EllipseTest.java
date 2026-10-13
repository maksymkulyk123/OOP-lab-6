package ua.opnu.model;

import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import static org.junit.jupiter.api.Assertions.*;

class EllipseTest {
    @Test
    void factoryCreatesAllShapes() {
        assertTrue(DrawShape.newInstance(DrawShape.SHAPE_RECTANGLE) instanceof Rectangle);
        assertTrue(DrawShape.newInstance(DrawShape.SHAPE_ROUNDED_RECT) instanceof RoundedRectangle);
        assertTrue(DrawShape.newInstance(DrawShape.SHAPE_ELLIPSE) instanceof Ellipse);
        assertNull(DrawShape.newInstance(-1));
    }

    @Test
    void ellipseCanBeDraggedInAnyDirection() {
        Point[] starts = {new Point(10, 20), new Point(90, 20), new Point(10, 80), new Point(90, 80)};
        Point[] ends = {new Point(90, 80), new Point(10, 80), new Point(90, 20), new Point(10, 20)};
        for (int i = 0; i < starts.length; i++) {
            DrawShape ellipse = new Ellipse(starts[i], ends[i]);
            Shape shape = ellipse.getShape();
            assertTrue(shape instanceof Ellipse2D);
            assertEquals(10, shape.getBounds2D().getX(), 0.000001);
            assertEquals(20, shape.getBounds2D().getY(), 0.000001);
            assertEquals(80, shape.getBounds2D().getWidth(), 0.000001);
            assertEquals(60, shape.getBounds2D().getHeight(), 0.000001);
        }
    }
}
