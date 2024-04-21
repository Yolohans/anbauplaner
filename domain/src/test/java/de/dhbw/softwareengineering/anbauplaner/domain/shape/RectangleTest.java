package de.dhbw.softwareengineering.anbauplaner.domain.shape;

import org.junit.Test;

class RectangleTest {
    @Test(expected = NullPointerException.class)
    public void testConstructorNullXLength() {
        new Rectangle(null, 1.0, new Point(0, 0));
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullYLength() {
        new Rectangle(1.0, null, new Point(0, 0));
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullPosition() {
        new Rectangle(1.0, 1.0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorZeroXLength() {
        new Rectangle(0.0, 1.0, new Point(0, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorZeroYLength() {
        new Rectangle(1.0, 0.0, new Point(0, 0));
    }
}