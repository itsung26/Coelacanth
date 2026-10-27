package io.github.itsung26.coelacanth.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for {@link Vector2i}.
 * Each method below covers the documented behavior of the matching
 * {@code Vector2i} operation, including integer-specific cases such as
 * truncation.
 */
public class Vector2iTest {
    /**
     * {@link Vector2i#abs()} replaces each component with its absolute value
     * and leaves the original vector unchanged.
     */
    @Test
    public void testAbs() {
        Vector2i negative = new Vector2i(-3, -4);
        assertEquals(new Vector2i(3, 4), negative.abs());
        assertEquals(new Vector2i(-3, -4), negative);

        assertEquals(new Vector2i(3, 4), new Vector2i(3, 4).abs());
        assertEquals(new Vector2i(0, 0), new Vector2i(0, 0).abs());
        assertEquals(new Vector2i(2, 5), new Vector2i(-2, 5).abs());
    }

    /**
     * {@link Vector2i#add(Vector2i)} is the component-wise sum and does not
     * modify the original vector.
     */
    @Test
    public void testAdd() {
        Vector2i original = new Vector2i(1, 2);
        assertEquals(new Vector2i(4, 6), original.add(new Vector2i(3, 4)));
        assertEquals(new Vector2i(1, 2), original);
        assertEquals(original, original.add(new Vector2i(0, 0)));
        assertEquals(new Vector2i(-1, 1), new Vector2i(2, -3).add(new Vector2i(-3, 4)));
    }

    /**
     * Named constants match the documented screen-space directions.
     */
    @Test
    public void testConstants() {
        assertEquals(new Vector2i(0, 0), Vector2i.ZERO);
        assertEquals(new Vector2i(1, 1), Vector2i.ONE);
        assertEquals(new Vector2i(-1, 0), Vector2i.LEFT);
        assertEquals(new Vector2i(1, 0), Vector2i.RIGHT);
        assertEquals(new Vector2i(0, -1), Vector2i.UP);
        assertEquals(new Vector2i(0, 1), Vector2i.DOWN);
    }

    /**
     * The no-arg constructor is the zero vector. The {@link Vector2} constructor
     * truncates float components toward zero.
     */
    @Test
    public void testConstructors() {
        assertEquals(new Vector2i(0, 0), new Vector2i());
        assertEquals(new Vector2i(3, -4), new Vector2i(new Vector2(3.9f, -4.9f)));
        assertEquals(new Vector2i(0, 0), new Vector2i(new Vector2(0.9f, -0.9f)));
    }

    /**
     * {@link Vector2i#distanceTo(Vector2i)} is the Euclidean distance as a
     * float, including the 3-4-5 triangle and a point's distance to itself.
     */
    @Test
    public void testDistanceTo() {
        assertEquals(5f, new Vector2i(0, 0).distanceTo(new Vector2i(3, 4)), 0f);
        assertEquals(5f, new Vector2i(1, 2).distanceTo(new Vector2i(4, 6)), 0f);
        assertEquals(0f, new Vector2i(3, 4).distanceTo(new Vector2i(3, 4)), 0f);
    }

    /**
     * {@link Vector2i#equals(Object)} compares components exactly. Distinct
     * types and {@code null} are not equal.
     */
    @Test
    public void testEquals() {
        Vector2i a = new Vector2i(1, -2);
        assertTrue(a.equals(a));
        assertEquals(new Vector2i(1, -2), a);
        assertFalse(a.equals(new Vector2i(1, 0)));
        assertFalse(a.equals(null));
        assertFalse(a.equals(new Vector2(1f, -2f)));
    }

    /**
     * {@link Vector2i#magnitude()} is {@code sqrt(x² + y²)} as a float,
     * including the 3-4-5 triangle and the zero vector.
     */
    @Test
    public void testMagnitude() {
        assertEquals(5f, new Vector2i(3, 4).magnitude(), 0f);
        assertEquals(5f, new Vector2i(-3, 4).magnitude(), 0f);
        assertEquals(0f, new Vector2i(0, 0).magnitude(), 0f);
        assertEquals(1f, new Vector2i(0, 1).magnitude(), 0f);
    }

    /**
     * {@link Vector2i#multiply(Vector2i)} is the component-wise product and does
     * not modify the original vector.
     */
    @Test
    public void testMultiply() {
        Vector2i original = new Vector2i(2, 3);
        assertEquals(new Vector2i(8, 15), original.multiply(new Vector2i(4, 5)));
        assertEquals(new Vector2i(2, 3), original);
        assertEquals(new Vector2i(0, 0), original.multiply(new Vector2i(0, 0)));
        assertEquals(new Vector2i(-4, 6), new Vector2i(-2, 3).multiply(new Vector2i(2, 2)));
    }

    /**
     * {@link Vector2i#pow(float)} raises each component to the given power and
     * truncates toward zero.
     */
    @Test
    public void testPow() {
        assertEquals(new Vector2i(4, 9), new Vector2i(2, 3).pow(2f));
        assertEquals(new Vector2i(1, 1), new Vector2i(2, 3).pow(0f));
        assertEquals(new Vector2i(2, 3), new Vector2i(4, 9).pow(0.5f));
        assertEquals(new Vector2i(1, 1), new Vector2i(2, 3).pow(0.5f));
        assertEquals(new Vector2i(0, 2), new Vector2i(-1, 4).pow(0.5f));
    }

    /**
     * {@link Vector2i#subtract(Vector2i)} is {@code this - with}, not the
     * reverse, and does not modify the original vector.
     */
    @Test
    public void testSubtract() {
        Vector2i original = new Vector2i(5, 5);
        assertEquals(new Vector2i(4, 3), original.subtract(new Vector2i(1, 2)));
        assertEquals(new Vector2i(5, 5), original);
        assertEquals(new Vector2i(1, -1), new Vector2i(1, 0).subtract(new Vector2i(0, 1)));
        assertEquals(new Vector2i(-1, 1), new Vector2i(0, 1).subtract(new Vector2i(1, 0)));
    }

    /**
     * {@link Vector2i#toString()} formats components as {@code (x, y)}.
     */
    @Test
    public void testToString() {
        assertEquals("(1, 2)", new Vector2i(1, 2).toString());
        assertEquals("(0, 0)", new Vector2i().toString());
        assertEquals("(-3, 4)", new Vector2i(-3, 4).toString());
    }
}
