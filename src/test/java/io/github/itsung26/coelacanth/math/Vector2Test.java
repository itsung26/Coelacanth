package io.github.itsung26.coelacanth.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for {@link Vector2}.
 * Each method below covers the documented behavior of the matching
 * {@code Vector2} operation, including edge cases called out in that
 * method's Javadoc.
 */
public class Vector2Test {
    /**
     * {@link Vector2#abs()} replaces each component with its absolute value
     * and leaves the original vector unchanged.
     */
    @Test
    public void testAbs() {
        Vector2 negative = new Vector2(-3f, -4f);
        assertEquals(new Vector2(3f, 4f), negative.abs());
        assertEquals(new Vector2(-3f, -4f), negative);

        assertEquals(new Vector2(3f, 4f), new Vector2(3f, 4f).abs());
        assertEquals(new Vector2(0f, 0f), new Vector2(0f, 0f).abs());
        assertEquals(new Vector2(2f, 5f), new Vector2(-2f, 5f).abs());
    }

    /**
     * {@link Vector2#ceil()} rounds each component toward positive infinity.
     */
    @Test
    public void testCeil() {
        assertEquals(new Vector2(2f, 4f), new Vector2(1.1f, 3.9f).ceil());
        assertEquals(new Vector2(-1f, -2f), new Vector2(-1.5f, -2.1f).ceil());
        assertEquals(new Vector2(2f, -3f), new Vector2(2f, -3f).ceil());
    }

    /**
     * {@link Vector2#cross(Vector2)} is the signed 2D analog of the cross
     * product: positive when {@code with} is counterclockwise from this
     * vector, negative when clockwise, and zero when the vectors are parallel.
     */
    @Test
    public void testCross() {
        Vector2 right = new Vector2(1f, 0f);
        Vector2 down = new Vector2(0f, 1f);

        assertEquals(1f, right.cross(down), 0f);
        assertEquals(-1f, down.cross(right), 0f);
        assertEquals(0f, right.cross(right), 0f);
        assertEquals(2f * 8f - 3f * 5f, new Vector2(2f, 3f).cross(new Vector2(5f, 8f)), 0f);
    }

    /**
     * {@link Vector2#dot(Vector2)} is the sum of pairwise products: {@code 1}
     * for a unit vector with itself, {@code 0} for perpendicular vectors.
     */
    @Test
    public void testDot() {
        Vector2 right = new Vector2(1f, 0f);
        Vector2 down = new Vector2(0f, 1f);

        assertEquals(1f, right.dot(right), 0f);
        assertEquals(0f, right.dot(down), 0f);
        assertEquals(2f * 4f + 3f * 5f, new Vector2(2f, 3f).dot(new Vector2(4f, 5f)), 0f);
    }

    /**
     * {@link Vector2#equals(Object)} compares component bits: same values are
     * equal, {@code NaN} equals {@code NaN}, and {@code +0} is distinct from
     * {@code -0}.
     */
    @Test
    public void testEquals() {
        Vector2 a = new Vector2(1.5f, -2.25f);
        assertTrue(a.equals(a));
        assertEquals(new Vector2(1.5f, -2.25f), a);
        assertFalse(a.equals(new Vector2(1.5f, 0f)));
        assertFalse(a.equals(null));
        assertFalse(a.equals("(1.5, -2.25)"));

        assertEquals(new Vector2(Float.NaN, 0f), new Vector2(Float.NaN, 0f));
        assertFalse(new Vector2(0f, 0f).equals(new Vector2(-0f, 0f)));
    }

    /**
     * {@link Vector2#floor()} rounds each component toward negative infinity.
     */
    @Test
    public void testFloor() {
        assertEquals(new Vector2(1f, 3f), new Vector2(1.1f, 3.9f).floor());
        assertEquals(new Vector2(-2f, -1f), new Vector2(-1.5f, -0.1f).floor());
        assertEquals(new Vector2(2f, -3f), new Vector2(2f, -3f).floor());
    }

    /**
     * {@link Vector2#isNormalized()} is an epsilon check against unit length.
     * Axis unit vectors and the result of {@link Vector2#normalized()} pass;
     * zero and clearly non-unit vectors do not.
     */
    @Test
    public void testIsNormalized() {
        assertTrue(new Vector2(1f, 0f).isNormalized());
        assertTrue(new Vector2(0f, -1f).isNormalized());
        assertTrue(new Vector2(3f, 4f).normalized().isNormalized());

        assertFalse(new Vector2(0f, 0f).isNormalized());
        assertFalse(new Vector2(1f, 1f).isNormalized());
        assertFalse(new Vector2(3f, 4f).isNormalized());
        assertTrue(new Vector2(1f + Vector2.EPSILON / 2f, 0f).isNormalized());
    }

    /**
     * {@link Vector2#magnitude()} is {@code sqrt(x² + y²)}, including the
     * 3-4-5 triangle and the zero vector.
     */
    @Test
    public void testMagnitude() {
        assertEquals(5f, new Vector2(3f, 4f).magnitude(), 0f);
        assertEquals(5f, new Vector2(-3f, 4f).magnitude(), 0f);
        assertEquals(0f, new Vector2(0f, 0f).magnitude(), 0f);
        assertEquals(1f, new Vector2(0f, 1f).magnitude(), 0f);
    }

    /**
     * {@link Vector2#normalized()} scales to unit length, returns zero when
     * the input is approximately zero, and does not modify the original.
     */
    @Test
    public void testNormalized() {
        Vector2 original = new Vector2(3f, 4f);
        Vector2 unit = original.normalized();

        assertEquals(new Vector2(0.6f, 0.8f), unit);
        assertEquals(1f, unit.magnitude(), Vector2.EPSILON);
        assertEquals(new Vector2(3f, 4f), original);

        assertEquals(new Vector2(0f, 0f), new Vector2(0f, 0f).normalized());
        assertEquals(new Vector2(0f, 0f), new Vector2(Vector2.EPSILON / 2f, 0f).normalized());
    }

    /**
     * {@link Vector2#toString()} formats components as {@code (x, y)}.
     */
    @Test
    public void testToString() {
        assertEquals("(1.0, 2.0)", new Vector2(1f, 2f).toString());
        assertEquals("(0.0, 0.0)", new Vector2().toString());
        assertEquals("(-3.5, 4.25)", new Vector2(-3.5f, 4.25f).toString());
    }
}
