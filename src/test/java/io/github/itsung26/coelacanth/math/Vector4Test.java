package io.github.itsung26.coelacanth.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests for {@link Vector4}.
 * Each method below covers the documented behavior of the matching
 * {@code Vector4} operation, including edge cases called out in that
 * method's Javadoc.
 */
public class Vector4Test {
    /**
     * {@link Vector4#abs()} replaces each component with its absolute value
     * and leaves the original vector unchanged.
     */
    @Test
    public void testAbs() {
        Vector4 negative = new Vector4(-3f, -4f, -5f, -6f);
        assertEquals(new Vector4(3f, 4f, 5f, 6f), negative.abs());
        assertEquals(new Vector4(-3f, -4f, -5f, -6f), negative);

        assertEquals(new Vector4(3f, 4f, 5f, 6f), new Vector4(3f, 4f, 5f, 6f).abs());
        assertEquals(new Vector4(0f, 0f, 0f, 0f), new Vector4(0f, 0f, 0f, 0f).abs());
        assertEquals(new Vector4(2f, 5f, 1f, 7f), new Vector4(-2f, 5f, -1f, 7f).abs());
    }

    /**
     * {@link Vector4#add(Vector4)} is the component-wise sum and does not
     * modify the original vector.
     */
    @Test
    public void testAdd() {
        Vector4 original = new Vector4(1f, 2f, 3f, 4f);
        assertEquals(new Vector4(5f, 7f, 9f, 11f), original.add(new Vector4(4f, 5f, 6f, 7f)));
        assertEquals(new Vector4(1f, 2f, 3f, 4f), original);
        assertEquals(original, original.add(new Vector4(0f, 0f, 0f, 0f)));
        assertEquals(new Vector4(-1f, 1f, 0f, 2f),
                new Vector4(2f, -3f, 1f, 0f).add(new Vector4(-3f, 4f, -1f, 2f)));
    }

    /**
     * {@link Vector4#ceil()} rounds each component toward positive infinity.
     */
    @Test
    public void testCeil() {
        assertEquals(new Vector4(2f, 4f, 1f, 6f), new Vector4(1.1f, 3.9f, 0.1f, 5.2f).ceil());
        assertEquals(new Vector4(-1f, -2f, -3f, -3f), new Vector4(-1.5f, -2.1f, -3.2f, -3.9f).ceil());
        assertEquals(new Vector4(2f, -3f, 4f, 5f), new Vector4(2f, -3f, 4f, 5f).ceil());
    }

    /**
     * {@link Vector4#clamp(Vector4, Vector4)} clamps each component independently
     * to the matching component of {@code min} and {@code max}.
     */
    @Test
    public void testClamp() {
        Vector4 min = new Vector4(0f, -1f, -2f, 0f);
        Vector4 max = new Vector4(2f, 1f, 0f, 3f);

        assertEquals(new Vector4(1f, 0f, -1f, 2f), new Vector4(1f, 0f, -1f, 2f).clamp(min, max));
        assertEquals(new Vector4(0f, -1f, -2f, 0f), new Vector4(-5f, -8f, -9f, -1f).clamp(min, max));
        assertEquals(new Vector4(2f, 1f, 0f, 3f), new Vector4(9f, 9f, 9f, 9f).clamp(min, max));
        assertEquals(new Vector4(0f, 1f, 0f, 3f), new Vector4(-1f, 5f, 5f, 8f).clamp(min, max));

        try {
            new Vector4(0f, 0f, 0f, 0f).clamp(new Vector4(1f, 0f, 0f, 0f), new Vector4(0f, 1f, 1f, 1f));
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    /**
     * {@link Vector4#clampf(float, float)} clamps all four components to the
     * same scalar range.
     */
    @Test
    public void testClampf() {
        assertEquals(new Vector4(1f, 1f, 1f, 1f), new Vector4(1f, 1f, 1f, 1f).clampf(0f, 2f));
        assertEquals(new Vector4(0f, 0f, 0f, 0f), new Vector4(-5f, -8f, -9f, -1f).clampf(0f, 2f));
        assertEquals(new Vector4(2f, 2f, 2f, 2f), new Vector4(9f, 9f, 9f, 9f).clampf(0f, 2f));
        assertEquals(new Vector4(0f, 2f, 1f, 2f), new Vector4(-1f, 5f, 1f, 8f).clampf(0f, 2f));

        try {
            new Vector4(0f, 0f, 0f, 0f).clampf(2f, 0f);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    /**
     * Named constants match the documented zero and one vectors.
     */
    @Test
    public void testConstants() {
        assertEquals(new Vector4(0f, 0f, 0f, 0f), Vector4.ZERO);
        assertEquals(new Vector4(1f, 1f, 1f, 1f), Vector4.ONE);
    }

    /**
     * The no-arg constructor is the zero vector. The {@link Vector4i}
     * constructor copies integer components as floats.
     */
    @Test
    public void testConstructors() {
        assertEquals(new Vector4(0f, 0f, 0f, 0f), new Vector4());
        assertEquals(new Vector4(3f, -4f, 5f, 6f), new Vector4(new Vector4i(3, -4, 5, 6)));
    }

    /**
     * {@link Vector4#distanceTo(Vector4)} is the Euclidean distance, including
     * a 5-unit diagonal and a point's distance to itself.
     */
    @Test
    public void testDistanceTo() {
        assertEquals(5f, new Vector4(0f, 0f, 0f, 0f).distanceTo(new Vector4(1f, 2f, 2f, 4f)), 0f);
        assertEquals(5f, new Vector4(1f, 2f, 3f, 4f).distanceTo(new Vector4(2f, 4f, 5f, 8f)), 0f);
        assertEquals(0f, new Vector4(3f, 4f, 5f, 6f).distanceTo(new Vector4(3f, 4f, 5f, 6f)), 0f);
    }

    /**
     * {@link Vector4#dot(Vector4)} is the sum of pairwise products: {@code 1}
     * for a unit vector with itself, {@code 0} for perpendicular vectors.
     */
    @Test
    public void testDot() {
        Vector4 x = new Vector4(1f, 0f, 0f, 0f);
        Vector4 y = new Vector4(0f, 1f, 0f, 0f);

        assertEquals(1f, x.dot(x), 0f);
        assertEquals(0f, x.dot(y), 0f);
        assertEquals(2f * 6f + 3f * 7f + 4f * 8f + 5f * 9f,
                new Vector4(2f, 3f, 4f, 5f).dot(new Vector4(6f, 7f, 8f, 9f)), 0f);
    }

    /**
     * {@link Vector4#equals(Object)} compares component bits: same values are
     * equal, {@code NaN} equals {@code NaN}, and {@code +0} is distinct from
     * {@code -0}.
     */
    @Test
    public void testEquals() {
        Vector4 a = new Vector4(1.5f, -2.25f, 0.5f, 4f);
        assertTrue(a.equals(a));
        assertEquals(new Vector4(1.5f, -2.25f, 0.5f, 4f), a);
        assertFalse(a.equals(new Vector4(1.5f, -2.25f, 0.5f, 0f)));
        assertFalse(a.equals(null));
        assertFalse(a.equals("(1.5, -2.25, 0.5, 4.0)"));

        assertEquals(new Vector4(Float.NaN, 0f, 0f, 0f), new Vector4(Float.NaN, 0f, 0f, 0f));
        assertFalse(new Vector4(0f, 0f, 0f, 0f).equals(new Vector4(-0f, 0f, 0f, 0f)));
    }

    /**
     * {@link Vector4#floor()} rounds each component toward negative infinity.
     */
    @Test
    public void testFloor() {
        assertEquals(new Vector4(1f, 3f, 0f, 5f), new Vector4(1.1f, 3.9f, 0.1f, 5.2f).floor());
        assertEquals(new Vector4(-2f, -1f, -1f, -4f), new Vector4(-1.5f, -0.1f, -0.1f, -3.1f).floor());
        assertEquals(new Vector4(2f, -3f, 4f, 5f), new Vector4(2f, -3f, 4f, 5f).floor());
    }

    /**
     * {@link Vector4#isNormalized()} is an epsilon check against unit length.
     * Axis unit vectors and the result of {@link Vector4#normalized()} pass;
     * zero and clearly non-unit vectors do not.
     */
    @Test
    public void testIsNormalized() {
        assertTrue(new Vector4(1f, 0f, 0f, 0f).isNormalized());
        assertTrue(new Vector4(0f, 0f, 0f, -1f).isNormalized());
        assertTrue(new Vector4(1f, 2f, 2f, 4f).normalized().isNormalized());

        assertFalse(new Vector4(0f, 0f, 0f, 0f).isNormalized());
        assertFalse(new Vector4(1f, 1f, 1f, 1f).isNormalized());
        assertFalse(new Vector4(1f, 2f, 2f, 4f).isNormalized());
        assertTrue(new Vector4(1f + Vector4.EPSILON / 2f, 0f, 0f, 0f).isNormalized());
    }

    /**
     * {@link Vector4#lerp(Vector4, float)} mixes per component. Weight {@code 0}
     * is this vector, {@code 1} is the target, and values outside {@code [0, 1]}
     * extrapolate.
     */
    @Test
    public void testLerp() {
        Vector4 from = new Vector4(0f, 10f, 0f, 2f);
        Vector4 to = new Vector4(10f, 0f, 10f, 6f);

        assertEquals(from, from.lerp(to, 0f));
        assertEquals(to, from.lerp(to, 1f));
        assertEquals(new Vector4(5f, 5f, 5f, 4f), from.lerp(to, 0.5f));
        assertEquals(new Vector4(20f, -10f, 20f, 10f), from.lerp(to, 2f));
        assertEquals(from, from.lerp(to, 0.5f).lerp(from, 1f));
    }

    /**
     * {@link Vector4#magnitude()} is {@code sqrt(x² + y² + z² + w²)}, including
     * a 5-unit diagonal and the zero vector.
     */
    @Test
    public void testMagnitude() {
        assertEquals(5f, new Vector4(1f, 2f, 2f, 4f).magnitude(), 0f);
        assertEquals(5f, new Vector4(-1f, 2f, 2f, 4f).magnitude(), 0f);
        assertEquals(0f, new Vector4(0f, 0f, 0f, 0f).magnitude(), 0f);
        assertEquals(1f, new Vector4(0f, 0f, 0f, 1f).magnitude(), 0f);
    }

    /**
     * {@link Vector4#moveTowards(Vector4, float)} steps each component toward
     * the target by at most {@code |delta|} and snaps when close enough. A
     * negative {@code delta} still approaches, but does not snap.
     */
    @Test
    public void testMoveTowards() {
        Vector4 from = new Vector4(0f, 0f, 0f, 0f);
        Vector4 to = new Vector4(10f, 4f, 8f, 2f);

        assertEquals(new Vector4(3f, 3f, 3f, 2f), from.moveTowards(to, 3f));
        assertEquals(to, from.moveTowards(to, 10f));
        assertEquals(to, to.moveTowards(to, 1f));
        assertEquals(new Vector4(2f, 2f, 2f, 2f), from.moveTowards(to, -2f));
    }

    /**
     * {@link Vector4#multiply(Vector4)} is the component-wise product and does
     * not modify the original vector.
     */
    @Test
    public void testMultiply() {
        Vector4 original = new Vector4(2f, 3f, 4f, 5f);
        assertEquals(new Vector4(8f, 15f, 24f, 35f), original.multiply(new Vector4(4f, 5f, 6f, 7f)));
        assertEquals(new Vector4(2f, 3f, 4f, 5f), original);
        assertEquals(new Vector4(0f, 0f, 0f, 0f), original.multiply(new Vector4(0f, 0f, 0f, 0f)));
        assertEquals(new Vector4(-4f, 6f, -8f, 10f),
                new Vector4(-2f, 3f, 4f, 5f).multiply(new Vector4(2f, 2f, -2f, 2f)));
    }

    /**
     * {@link Vector4#normalized()} scales to unit length, returns zero when
     * the input is approximately zero, and does not modify the original.
     */
    @Test
    public void testNormalized() {
        Vector4 original = new Vector4(1f, 2f, 2f, 4f);
        Vector4 unit = original.normalized();

        assertEquals(new Vector4(0.2f, 0.4f, 0.4f, 0.8f), unit);
        assertEquals(1f, unit.magnitude(), Vector4.EPSILON);
        assertEquals(new Vector4(1f, 2f, 2f, 4f), original);

        assertEquals(new Vector4(0f, 0f, 0f, 0f), new Vector4(0f, 0f, 0f, 0f).normalized());
        assertEquals(new Vector4(0f, 0f, 0f, 0f), new Vector4(Vector4.EPSILON / 2f, 0f, 0f, 0f).normalized());
    }

    /**
     * {@link Vector4#pow(float)} raises each component to the given power.
     */
    @Test
    public void testPow() {
        assertEquals(new Vector4(4f, 9f, 16f, 25f), new Vector4(2f, 3f, 4f, 5f).pow(2f));
        assertEquals(new Vector4(1f, 1f, 1f, 1f), new Vector4(2f, 3f, 4f, 5f).pow(0f));
        assertEquals(new Vector4(2f, 3f, 4f, 5f), new Vector4(4f, 9f, 16f, 25f).pow(0.5f));
        assertEquals(new Vector4(0.5f, 0.25f, 0.125f, 0.1f), new Vector4(2f, 4f, 8f, 10f).pow(-1f));

        Vector4 rootedNegative = new Vector4(-1f, 4f, 9f, 16f).pow(0.5f);
        assertTrue(Float.isNaN(rootedNegative.x));
        assertEquals(2f, rootedNegative.y, 0f);
        assertEquals(3f, rootedNegative.z, 0f);
        assertEquals(4f, rootedNegative.w, 0f);
    }

    /**
     * {@link Vector4#subtract(Vector4)} is {@code this - with}, not the reverse,
     * and does not modify the original vector.
     */
    @Test
    public void testSubtract() {
        Vector4 original = new Vector4(5f, 5f, 5f, 5f);
        assertEquals(new Vector4(4f, 3f, 1f, 0f), original.subtract(new Vector4(1f, 2f, 4f, 5f)));
        assertEquals(new Vector4(5f, 5f, 5f, 5f), original);
        assertEquals(new Vector4(1f, -1f, 0f, 2f),
                new Vector4(1f, 0f, 1f, 3f).subtract(new Vector4(0f, 1f, 1f, 1f)));
        assertEquals(new Vector4(-1f, 1f, 0f, -2f),
                new Vector4(0f, 1f, 1f, 1f).subtract(new Vector4(1f, 0f, 1f, 3f)));
    }

    /**
     * {@link Vector4#toString()} formats components as {@code (x, y, z, w)}.
     */
    @Test
    public void testToString() {
        assertEquals("(1.0, 2.0, 3.0, 4.0)", new Vector4(1f, 2f, 3f, 4f).toString());
        assertEquals("(0.0, 0.0, 0.0, 0.0)", new Vector4().toString());
        assertEquals("(-3.5, 4.25, 0.5, 1.0)", new Vector4(-3.5f, 4.25f, 0.5f, 1f).toString());
    }
}
