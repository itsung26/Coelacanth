package io.github.itsung26.coelacanth.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for {@link Vector2i}.
 * Each method below covers the documented behavior of the matching
 * {@code Vector2i} operation, including integer-specific cases such as
 * exact unit-length checks and identity rounding.
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
     * {@link Vector2i#ceil()} copies the vector: integer components are already
     * integral. The result is a distinct instance.
     */
    @Test
    public void testCeil() {
        Vector2i original = new Vector2i(-2, 5);
        Vector2i ceiled = original.ceil();

        assertEquals(original, ceiled);
        assertNotSame(original, ceiled);
        assertEquals(new Vector2i(0, 0), new Vector2i(0, 0).ceil());
    }

    /**
     * {@link Vector2i#cross(Vector2i)} is the signed 2D analog of the cross
     * product: positive when {@code with} is counterclockwise from this
     * vector, negative when clockwise, and zero when the vectors are parallel.
     */
    @Test
    public void testCross() {
        Vector2i right = new Vector2i(1, 0);
        Vector2i down = new Vector2i(0, 1);

        assertEquals(1, right.cross(down));
        assertEquals(-1, down.cross(right));
        assertEquals(0, right.cross(right));
        assertEquals(2 * 8 - 3 * 5, new Vector2i(2, 3).cross(new Vector2i(5, 8)));
    }

    /**
     * {@link Vector2i#dot(Vector2i)} is the sum of pairwise products: {@code 1}
     * for a unit vector with itself, {@code 0} for perpendicular vectors.
     */
    @Test
    public void testDot() {
        Vector2i right = new Vector2i(1, 0);
        Vector2i down = new Vector2i(0, 1);

        assertEquals(1, right.dot(right));
        assertEquals(0, right.dot(down));
        assertEquals(2 * 4 + 3 * 5, new Vector2i(2, 3).dot(new Vector2i(4, 5)));
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
     * {@link Vector2i#floor()} copies the vector: integer components are already
     * integral. The result is a distinct instance.
     */
    @Test
    public void testFloor() {
        Vector2i original = new Vector2i(-2, 5);
        Vector2i floored = original.floor();

        assertEquals(original, floored);
        assertNotSame(original, floored);
        assertEquals(new Vector2i(0, 0), new Vector2i(0, 0).floor());
    }

    /**
     * {@link Vector2i#isNormalized()} is exact: only the axis directions
     * {@code (±1, 0)} and {@code (0, ±1)} have unit length.
     */
    @Test
    public void testIsNormalized() {
        assertTrue(new Vector2i(1, 0).isNormalized());
        assertTrue(new Vector2i(-1, 0).isNormalized());
        assertTrue(new Vector2i(0, 1).isNormalized());
        assertTrue(new Vector2i(0, -1).isNormalized());

        assertFalse(new Vector2i(0, 0).isNormalized());
        assertFalse(new Vector2i(1, 1).isNormalized());
        assertFalse(new Vector2i(3, 4).isNormalized());
        assertFalse(new Vector2i(2, 0).isNormalized());
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
     * {@link Vector2i#normalized()} returns a unit-length {@link Vector2}.
     * The zero vector yields {@code (0, 0)}; the original integer vector is
     * not modified.
     */
    @Test
    public void testNormalized() {
        Vector2i original = new Vector2i(3, 4);
        Vector2 unit = original.normalized();

        assertEquals(new Vector2(0.6f, 0.8f), unit);
        assertEquals(1f, unit.magnitude(), Vector2.EPSILON);
        assertEquals(new Vector2i(3, 4), original);

        assertEquals(new Vector2(0f, 0f), new Vector2i(0, 0).normalized());
        assertEquals(new Vector2(1f, 0f), new Vector2i(1, 0).normalized());
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
