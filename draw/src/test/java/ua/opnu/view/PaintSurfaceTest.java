package ua.opnu.view;

import org.junit.jupiter.api.Test;
import ua.opnu.model.DrawShape;

import javax.swing.SwingUtilities;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PaintSurfaceTest {
    @Test
    void clearRemovesPixelsFromPreviouslyPaintedCanvas() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            PaintSurface surface = new PaintSurface();
            surface.setSize(400, 400);
            BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            try {
                surface.paint(graphics);
                int[] empty = image.getRGB(0, 0, 400, 400, null, 0, 400);
                surface.setShapeType(DrawShape.SHAPE_ELLIPSE);
                surface.dispatchEvent(mouse(surface, MouseEvent.MOUSE_PRESSED, 40, 50));
                surface.dispatchEvent(mouse(surface, MouseEvent.MOUSE_RELEASED, 180, 150));
                surface.paint(graphics);
                surface.clear();
                surface.paint(graphics);
                assertArrayEquals(empty, image.getRGB(0, 0, 400, 400, null, 0, 400));
            } finally {
                graphics.dispose();
            }
        });
    }

    @Test
    void drawEllipseAndClear() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            PaintSurface surface = new PaintSurface();
            surface.setSize(400, 400);
            int[] empty = pixels(surface);
            surface.setShapeType(DrawShape.SHAPE_ELLIPSE);
            surface.dispatchEvent(mouse(surface, MouseEvent.MOUSE_PRESSED, 40, 50));
            surface.dispatchEvent(mouse(surface, MouseEvent.MOUSE_RELEASED, 180, 150));
            assertFalse(Arrays.equals(empty, pixels(surface)));
            surface.clear();
            assertArrayEquals(empty, pixels(surface));
            surface.clear();
            assertArrayEquals(empty, pixels(surface));
        });
    }

    @Test
    void clearAlsoCancelsUnfinishedShape() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            PaintSurface surface = new PaintSurface();
            surface.setSize(400, 400);
            int[] empty = pixels(surface);
            surface.dispatchEvent(mouse(surface, MouseEvent.MOUSE_PRESSED, 40, 50));
            surface.clear();
            surface.dispatchEvent(mouse(surface, MouseEvent.MOUSE_RELEASED, 180, 150));
            assertArrayEquals(empty, pixels(surface));
            surface.setShapeType(-1);
            surface.dispatchEvent(mouse(surface, MouseEvent.MOUSE_PRESSED, 40, 50));
            surface.dispatchEvent(mouse(surface, MouseEvent.MOUSE_RELEASED, 180, 150));
            assertArrayEquals(empty, pixels(surface));
        });
    }

    private static MouseEvent mouse(PaintSurface surface, int id, int x, int y) {
        return new MouseEvent(surface, id, 0, 0, x, y, 1, false, MouseEvent.BUTTON1);
    }

    private static int[] pixels(PaintSurface surface) {
        BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        surface.paint(graphics);
        graphics.dispose();
        return image.getRGB(0, 0, 400, 400, null, 0, 400);
    }
}
