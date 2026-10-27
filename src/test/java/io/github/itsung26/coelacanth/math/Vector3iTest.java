package io.github.itsung26.coelacanth.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for {@link Vector3i}.
 * Each method below covers the documented behavior of the matching
 * {@code Vector3i} operation, including integer-specific cases such as
 * truncation.
 */
public class Vector3iTest {
    /**
     * {@link Vector3i#abs()} replaces each component with its absolute value
     * and leaves the original vector unchanged.
     */
    @Test
    public void testAbs() {
        Vector3i negative = new Vector3i(-3, -4, -5);
        assertEquals(new Vector3i(3, 4, 5), negative.abs());
        assertEquals(new Vector3i(-3, -4, -5), negative);

        assertEquals(new Vector3i(3, 4, 5), new Vector3i(3, 4, 5).abs());
        assertEquals(new Vector3i(0, 0, 0), new Vector3i(0, 0, 0).abs());
        assertEquals(new Vector3i(2, 5, 1), new Vector3i(-2, 5, -1).abs());
    }

    /**
     * {@link Vector3i#add(Vector3i)} is the component-wise sum and does not
     * modify the original vector.
     */
    @Test
    public void testAdd() {
        Vector3i original = new Vector3i(1, 2, 3);
        assertEquals(new Vector3i(4, 6, 8), original.add(new Vector3i(3, 4, 5)));
        assertEquals(new Vector3i(1, 2, 3), original);
        assertEquals(original, original.add(new Vector3i(0, 0, 0)));
        assertEquals(new Vector3i(-1, 1, 0), new Vector3i(2, -3, 1).add(new Vector3i(-3, 4, -1)));
    }

    /**
     * Named constants match the documented world-space directions.
     */
    @Test
    public void testConstants() {
        assertEquals(new Vector3i(0, 0, 0), Vector3i.ZERO);
        assertEquals(new Vector3i(1, 1, 1), Vector3i.ONE);
        assertEquals(new Vector3i(-1, 0, 0), Vector3i.LEFT);
        assertEquals(new Vector3i(1, 0, 0), Vector3i.RIGHT);
        assertEquals(new Vector3i(0, 1, 0), Vector3i.UP);
        assertEquals(new Vector3i(0, -1, 0), Vector3i.DOWN);
        assertEquals(new Vector3i(0, 0, -1), Vector3i.FORWARD);
        assertEquals(new Vector3i(0, 0, 1), Vector3i.BACK);
    }

    /**
     * The no-arg constructor is the zero vector. The {@link Vector3} constructor
     * truncates float components toward zero.
     */
    @Test
    public void testConstructors() {
        assertEquals(new Vector3i(0, 0, 0), new Vector3i());
        assertEquals(new Vector3i(3, -4, 5), new Vector3i(new Vector3(3.9f, -4.9f, 5.1f)));
        assertEquals(new Vector3i(0, 0, 0), new Vector3i(new Vector3(0.9f, -0.9f, 0.1f)));
    }

    /**
     * {@link Vector3i#distanceTo(Vector3i)} is the Euclidean distance as a
     * float, including a 3-unit diagonal and a point's distance to itself.
     */
    @Test
    public void testDistanceTo() {
        assertEquals(3f, new Vector3i(0, 0, 0).distanceTo(new Vector3i(1, 2, 2)), 0f);
        assertEquals(3f, new Vector3i(1, 2, 3).distanceTo(new Vector3i(2, 4, 5)), 0f);
        assertEquals(0f, new Vector3i(3, 4, 5).distanceTo(new Vector3i(3, 4, 5)), 0f);
    }

    /**
     * {@link Vector3i#equals(Object)} compares components exactly. Distinct
     * types and {@code null} are not equal.
     */
    @Test
    public void testEquals() {
        Vector3i a = new Vector3i(1, -2, 3);
        assertTrue(a.equals(a));
        assertEquals(new Vector3i(1, -2, 3), a);
        assertFalse(a.equals(new Vector3i(1, -2, 0)));
        assertFalse(a.equals(null));
        assertFalse(a.equals(new Vector3(1f, -2f, 3f)));
    }

    /**
     * {@link Vector3i#magnitude()} is {@code sqrt(x² + y² + z²)} as a float,
     * including a 3-unit diagonal and the zero vector.
     */
    @Test
    public void testMagnitude() {
        assertEquals(3f, new Vector3i(1, 2, 2).magnitude(), 0f);
        assertEquals(3f, new Vector3i(-1, 2, 2).magnitude(), 0f);
        assertEquals(0f, new Vector3i(0, 0, 0).magnitude(), 0f);
        assertEquals(1f, new Vector3i(0, 0, 1).magnitude(), 0f);
    }

    /**
     * {@link Vector3i#multiply(Vector3i)} is the component-wise product and does
     * not modify the original vector.
     */
    @Test
    public void testMultiply() {
        Vector3i original = new Vector3i(2, 3, 4);
        assertEquals(new Vector3i(8, 15, 24), original.multiply(new Vector3i(4, 5, 6)));
        assertEquals(new Vector3i(2, 3, 4), original);
        assertEquals(new Vector3i(0, 0, 0), original.multiply(new Vector3i(0, 0, 0)));
        assertEquals(new Vector3i(-4, 6, -8), new Vector3i(-2, 3, 4).multiply(new Vector3i(2, 2, -2)));
    }

    /**
     * {@link Vector3i#pow(float)} raises each component to the given power and
     * truncates toward zero.
     */
    @Test
    public void testPow() {
        assertEquals(new Vector3i(4, 9, 16), new Vector3i(2, 3, 4).pow(2f));
        assertEquals(new Vector3i(1, 1, 1), new Vector3i(2, 3, 4).pow(0f));
        assertEquals(new Vector3i(2, 3, 4), new Vector3i(4, 9, 16).pow(0.5f));
        assertEquals(new Vector3i(1, 1, 2), new Vector3i(2, 3, 4).pow(0.5f));
        assertEquals(new Vector3i(0, 2, 3), new Vector3i(-1, 4, 9).pow(0.5f));
    }

    /**
     * {@link Vector3i#subtract(Vector3i)} is {@code this - with}, not the
     * reverse, and does not modify the original vector.
     */
    @Test
    public void testSubtract() {
        Vector3i original = new Vector3i(5, 5, 5);
        assertEquals(new Vector3i(4, 3, 1), original.subtract(new Vector3i(1, 2, 4)));
        assertEquals(new Vector3i(5, 5, 5), original);
        assertEquals(new Vector3i(1, -1, 0), new Vector3i(1, 0, 1).subtract(new Vector3i(0, 1, 1)));
        assertEquals(new Vector3i(-1, 1, 0), new Vector3i(0, 1, 1).subtract(new Vector3i(1, 0, 1)));
    }

    /**
     * {@link Vector3i#toString()} formats components as {@code (x, y, z)}.
     */
    @Test
    public void testToString() {
        assertEquals("(1, 2, 3)", new Vector3i(1, 2, 3).toString());
        assertEquals("(0, 0, 0)", new Vector3i().toString());
        assertEquals("(-3, 4, 5)", new Vector3i(-3, 4, 5).toString());
    }
}
