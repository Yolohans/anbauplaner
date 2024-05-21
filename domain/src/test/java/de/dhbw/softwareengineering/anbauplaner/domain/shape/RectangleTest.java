package de.dhbw.softwareengineering.anbauplaner.domain.shape;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {
    @Test
    public void testConstructorNullXLength() {
        assertThrows(NullPointerException.class, () -> {
            new Rectangle(null, 1.0, new Point(0, 0));
        });
    }

    @Test
    public void testConstructorNullYLength() {
        assertThrows(NullPointerException.class, () -> {
            new Rectangle(1.0, null, new Point(0, 0));
        });
    }

    @Test
    public void testConstructorNullPosition() {
        assertThrows(NullPointerException.class, () -> {
            new Rectangle(1.0, 1.0, null);
        });
    }

    @Test
    public void testConstructorZeroXLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle(0.0, 1.0, new Point(0, 0));
        });
    }

    @Test
    public void testConstructorZeroYLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle(1.0, 0.0, new Point(0, 0));
        });
    }

    @Test
    public void testTranslatePosition() {
        Rectangle rectangle = new Rectangle(10.0, 5.0, new Point(0.0, 0.0));
        Rectangle translatedRectangle = (Rectangle) rectangle.translatePosition(new Point(1.0, 1.0));
        assertEquals(new Point(1.0, 1.0), translatedRectangle.getPosition());
    }

    @Test
    public void testSubtractPosition() {
        Rectangle rectangle = new Rectangle(10.0, 5.0, new Point(1.0, 1.0));
        Rectangle subtractedRectangle = (Rectangle) rectangle.subtractPosition(new Point(1.0, 1.0));
        assertEquals(new Point(0.0, 0.0), subtractedRectangle.getPosition());
    }

    @Test
    public void testReplacePosition() {
        Rectangle rectangle = new Rectangle(10.0, 5.0, new Point(0.0, 0.0));
        Rectangle replacedRectangle = (Rectangle) rectangle.replacePosition(new Point(1.0, 1.0));
        assertEquals(new Point(1.0, 1.0), replacedRectangle.getPosition());
    }

    @Test
    public void testCollidesWithRectangle() {
        Rectangle rectangle1 = new Rectangle(10.0, 5.0, new Point(0.0, 0.0));
        Rectangle rectangle2 = new Rectangle(10.0, 5.0, new Point(5.0, 2.0));
        assertTrue(rectangle1.collidesWithRectangle(rectangle2));
    }
}