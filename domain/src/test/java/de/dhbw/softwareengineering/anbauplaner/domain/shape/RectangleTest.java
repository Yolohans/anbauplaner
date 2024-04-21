package de.dhbw.softwareengineering.anbauplaner.domain.shape;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}