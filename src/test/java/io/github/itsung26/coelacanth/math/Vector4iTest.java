package io.github.itsung26.coelacanth.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for {@link Vector4i}.
 * Each method below covers the documented behavior of the matching
 * {@code Vector4i} operation, including integer-specific cases such as
 * truncation.
 */
public class Vector4iTest {
    /**
     * {@link Vector4i#abs()} replaces each component with its absolute value
     * and leaves the original vector unchanged.
     */
    @Test
    public void testAbs() {
        Vector4i negative = new Vector4i(-3, -4, -5, -6);
        assertEquals(new Vector4i(3, 4, 5, 6), negative.abs());
        assertEquals(new Vector4i(-3, -4, -5, -6), negative);

        assertEquals(new Vector4i(3, 4, 5, 6), new Vector4i(3, 4, 5, 6).abs());
        assertEquals(new Vector4i(0, 0, 0, 0), new Vector4i(0, 0, 0, 0).abs());
        assertEquals(new Vector4i(2, 5, 1, 7), new Vector4i(-2, 5, -1, 7).abs());
    }

    /**
     * {@link Vector4i#add(Vector4i)} is the component-wise sum and does not
     * modify the original vector.
     */
    @Test
    public void testAdd() {
        Vector4i original = new Vector4i(1, 2, 3, 4);
        assertEquals(new Vector4i(5, 7, 9, 11), original.add(new Vector4i(4, 5, 6, 7)));
        assertEquals(new Vector4i(1, 2, 3, 4), original);
        assertEquals(original, original.add(new Vector4i(0, 0, 0, 0)));
        assertEquals(new Vector4i(-1, 1, 0, 2), new Vector4i(2, -3, 1, 0).add(new Vector4i(-3, 4, -1, 2)));
    }

    /**
     * Named constants match the documented zero and one vectors.
     */
    @Test
    public void testConstants() {
        assertEquals(new Vector4i(0, 0, 0, 0), Vector4i.ZERO);
        assertEquals(new Vector4i(1, 1, 1, 1), Vector4i.ONE);
    }

    /**
     * The no-arg constructor is the zero vector. The {@link Vector4} constructor
     * truncates float components toward zero.
     */
    @Test
    public void testConstructors() {
        assertEquals(new Vector4i(0, 0, 0, 0), new Vector4i());
        assertEquals(new Vector4i(3, -4, 5, 6), new Vector4i(new Vector4(3.9f, -4.9f, 5.1f, 6.9f)));
        assertEquals(new Vector4i(0, 0, 0, 0), new Vector4i(new Vector4(0.9f, -0.9f, 0.1f, -0.1f)));
    }

    /**
     * {@link Vector4i#distanceTo(Vector4i)} is the Euclidean distance as a
     * float, including a 5-unit diagonal and a point's distance to itself.
     */
    @Test
    public void testDistanceTo() {
        assertEquals(5f, new Vector4i(0, 0, 0, 0).distanceTo(new Vector4i(1, 2, 2, 4)), 0f);
        assertEquals(5f, new Vector4i(1, 2, 3, 4).distanceTo(new Vector4i(2, 4, 5, 8)), 0f);
        assertEquals(0f, new Vector4i(3, 4, 5, 6).distanceTo(new Vector4i(3, 4, 5, 6)), 0f);
    }

    /**
     * {@link Vector4i#equals(Object)} compares components exactly. Distinct
     * types and {@code null} are not equal.
     */
    @Test
    public void testEquals() {
        Vector4i a = new Vector4i(1, -2, 3, 4);
        assertTrue(a.equals(a));
        assertEquals(new Vector4i(1, -2, 3, 4), a);
        assertFalse(a.equals(new Vector4i(1, -2, 3, 0)));
        assertFalse(a.equals(null));
        assertFalse(a.equals(new Vector4(1f, -2f, 3f, 4f)));
    }

    /**
     * {@link Vector4i#magnitude()} is {@code sqrt(x² + y² + z² + w²)} as a
     * float, including a 5-unit diagonal and the zero vector.
     */
    @Test
    public void testMagnitude() {
        assertEquals(5f, new Vector4i(1, 2, 2, 4).magnitude(), 0f);
        assertEquals(5f, new Vector4i(-1, 2, 2, 4).magnitude(), 0f);
        assertEquals(0f, new Vector4i(0, 0, 0, 0).magnitude(), 0f);
        assertEquals(1f, new Vector4i(0, 0, 0, 1).magnitude(), 0f);
    }

    /**
     * {@link Vector4i#multiply(Vector4i)} is the component-wise product and does
     * not modify the original vector.
     */
    @Test
    public void testMultiply() {
        Vector4i original = new Vector4i(2, 3, 4, 5);
        assertEquals(new Vector4i(8, 15, 24, 35), original.multiply(new Vector4i(4, 5, 6, 7)));
        assertEquals(new Vector4i(2, 3, 4, 5), original);
        assertEquals(new Vector4i(0, 0, 0, 0), original.multiply(new Vector4i(0, 0, 0, 0)));
        assertEquals(new Vector4i(-4, 6, -8, 10), new Vector4i(-2, 3, 4, 5).multiply(new Vector4i(2, 2, -2, 2)));
    }

    /**
     * {@link Vector4i#pow(float)} raises each component to the given power and
     * truncates toward zero.
     */
    @Test
    public void testPow() {
        assertEquals(new Vector4i(4, 9, 16, 25), new Vector4i(2, 3, 4, 5).pow(2f));
        assertEquals(new Vector4i(1, 1, 1, 1), new Vector4i(2, 3, 4, 5).pow(0f));
        assertEquals(new Vector4i(2, 3, 4, 5), new Vector4i(4, 9, 16, 25).pow(0.5f));
        assertEquals(new Vector4i(1, 1, 2, 2), new Vector4i(2, 3, 4, 5).pow(0.5f));
        assertEquals(new Vector4i(0, 2, 3, 4), new Vector4i(-1, 4, 9, 16).pow(0.5f));
    }

    /**
     * {@link Vector4i#subtract(Vector4i)} is {@code this - with}, not the
     * reverse, and does not modify the original vector.
     */
    @Test
    public void testSubtract() {
        Vector4i original = new Vector4i(5, 5, 5, 5);
        assertEquals(new Vector4i(4, 3, 1, 0), original.subtract(new Vector4i(1, 2, 4, 5)));
        assertEquals(new Vector4i(5, 5, 5, 5), original);
        assertEquals(new Vector4i(1, -1, 0, 2), new Vector4i(1, 0, 1, 3).subtract(new Vector4i(0, 1, 1, 1)));
        assertEquals(new Vector4i(-1, 1, 0, -2), new Vector4i(0, 1, 1, 1).subtract(new Vector4i(1, 0, 1, 3)));
    }

    /**
     * {@link Vector4i#toString()} formats components as {@code (x, y, z, w)}.
     */
    @Test
    public void testToString() {
        assertEquals("(1, 2, 3, 4)", new Vector4i(1, 2, 3, 4).toString());
        assertEquals("(0, 0, 0, 0)", new Vector4i().toString());
        assertEquals("(-3, 4, 5, 6)", new Vector4i(-3, 4, 5, 6).toString());
    }
}
