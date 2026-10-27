package io.github.itsung26.coelacanth.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests for {@link Vector3}.
 * Each method below covers the documented behavior of the matching
 * {@code Vector3} operation, including edge cases called out in that
 * method's Javadoc.
 */
public class Vector3Test {
    /**
     * {@link Vector3#abs()} replaces each component with its absolute value
     * and leaves the original vector unchanged.
     */
    @Test
    public void testAbs() {
        Vector3 negative = new Vector3(-3f, -4f, -5f);
        assertEquals(new Vector3(3f, 4f, 5f), negative.abs());
        assertEquals(new Vector3(-3f, -4f, -5f), negative);

        assertEquals(new Vector3(3f, 4f, 5f), new Vector3(3f, 4f, 5f).abs());
        assertEquals(new Vector3(0f, 0f, 0f), new Vector3(0f, 0f, 0f).abs());
        assertEquals(new Vector3(2f, 5f, 1f), new Vector3(-2f, 5f, -1f).abs());
    }

    /**
     * {@link Vector3#add(Vector3)} is the component-wise sum and does not
     * modify the original vector.
     */
    @Test
    public void testAdd() {
        Vector3 original = new Vector3(1f, 2f, 3f);
        assertEquals(new Vector3(4f, 6f, 8f), original.add(new Vector3(3f, 4f, 5f)));
        assertEquals(new Vector3(1f, 2f, 3f), original);
        assertEquals(original, original.add(new Vector3(0f, 0f, 0f)));
        assertEquals(new Vector3(-1f, 1f, 0f), new Vector3(2f, -3f, 1f).add(new Vector3(-3f, 4f, -1f)));
    }

    /**
     * {@link Vector3#ceil()} rounds each component toward positive infinity.
     */
    @Test
    public void testCeil() {
        assertEquals(new Vector3(2f, 4f, 1f), new Vector3(1.1f, 3.9f, 0.1f).ceil());
        assertEquals(new Vector3(-1f, -2f, -3f), new Vector3(-1.5f, -2.1f, -3.2f).ceil());
        assertEquals(new Vector3(2f, -3f, 4f), new Vector3(2f, -3f, 4f).ceil());
    }

    /**
     * {@link Vector3#clamp(Vector3, Vector3)} clamps each component independently
     * to the matching component of {@code min} and {@code max}.
     */
    @Test
    public void testClamp() {
        Vector3 min = new Vector3(0f, -1f, -2f);
        Vector3 max = new Vector3(2f, 1f, 0f);

        assertEquals(new Vector3(1f, 0f, -1f), new Vector3(1f, 0f, -1f).clamp(min, max));
        assertEquals(new Vector3(0f, -1f, -2f), new Vector3(-5f, -8f, -9f).clamp(min, max));
        assertEquals(new Vector3(2f, 1f, 0f), new Vector3(9f, 9f, 9f).clamp(min, max));
        assertEquals(new Vector3(0f, 1f, 0f), new Vector3(-1f, 5f, 5f).clamp(min, max));

        try {
            new Vector3(0f, 0f, 0f).clamp(new Vector3(1f, 0f, 0f), new Vector3(0f, 1f, 1f));
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    /**
     * {@link Vector3#clampf(float, float)} clamps all three components to the
     * same scalar range.
     */
    @Test
    public void testClampf() {
        assertEquals(new Vector3(1f, 1f, 1f), new Vector3(1f, 1f, 1f).clampf(0f, 2f));
        assertEquals(new Vector3(0f, 0f, 0f), new Vector3(-5f, -8f, -9f).clampf(0f, 2f));
        assertEquals(new Vector3(2f, 2f, 2f), new Vector3(9f, 9f, 9f).clampf(0f, 2f));
        assertEquals(new Vector3(0f, 2f, 1f), new Vector3(-1f, 5f, 1f).clampf(0f, 2f));

        try {
            new Vector3(0f, 0f, 0f).clampf(2f, 0f);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    /**
     * Named constants match the documented world-space directions, and the
     * axis unit vectors have length {@code 1}.
     */
    @Test
    public void testConstants() {
        assertEquals(new Vector3(0f, 0f, 0f), Vector3.ZERO);
        assertEquals(new Vector3(1f, 1f, 1f), Vector3.ONE);
        assertEquals(new Vector3(-1f, 0f, 0f), Vector3.LEFT);
        assertEquals(new Vector3(1f, 0f, 0f), Vector3.RIGHT);
        assertEquals(new Vector3(0f, 1f, 0f), Vector3.UP);
        assertEquals(new Vector3(0f, -1f, 0f), Vector3.DOWN);
        assertEquals(new Vector3(0f, 0f, -1f), Vector3.FORWARD);
        assertEquals(new Vector3(0f, 0f, 1f), Vector3.BACK);

        assertTrue(Vector3.LEFT.isNormalized());
        assertTrue(Vector3.RIGHT.isNormalized());
        assertTrue(Vector3.UP.isNormalized());
        assertTrue(Vector3.DOWN.isNormalized());
        assertTrue(Vector3.FORWARD.isNormalized());
        assertTrue(Vector3.BACK.isNormalized());
    }

    /**
     * The no-arg constructor is the zero vector. The {@link Vector3i}
     * constructor copies integer components as floats.
     */
    @Test
    public void testConstructors() {
        assertEquals(new Vector3(0f, 0f, 0f), new Vector3());
        assertEquals(new Vector3(3f, -4f, 5f), new Vector3(new Vector3i(3, -4, 5)));
    }

    /**
     * {@link Vector3#cross(Vector3)} is the right-handed cross product:
     * perpendicular to both operands, anti-commutative, and zero when the
     * vectors are parallel.
     */
    @Test
    public void testCross() {
        assertEquals(Vector3.BACK, Vector3.RIGHT.cross(Vector3.UP));
        assertEquals(Vector3.FORWARD, Vector3.UP.cross(Vector3.RIGHT));
        assertEquals(Vector3.ZERO, Vector3.RIGHT.cross(Vector3.RIGHT));
        assertEquals(new Vector3(-3f, 6f, -3f), new Vector3(2f, 3f, 4f).cross(new Vector3(5f, 6f, 7f)));
    }

    /**
     * {@link Vector3#distanceTo(Vector3)} is the Euclidean distance, including
     * a 3-unit diagonal and a point's distance to itself.
     */
    @Test
    public void testDistanceTo() {
        assertEquals(3f, new Vector3(0f, 0f, 0f).distanceTo(new Vector3(1f, 2f, 2f)), 0f);
        assertEquals(3f, new Vector3(1f, 2f, 3f).distanceTo(new Vector3(2f, 4f, 5f)), 0f);
        assertEquals(0f, new Vector3(3f, 4f, 5f).distanceTo(new Vector3(3f, 4f, 5f)), 0f);
    }

    /**
     * {@link Vector3#dot(Vector3)} is the sum of pairwise products: {@code 1}
     * for a unit vector with itself, {@code 0} for perpendicular vectors.
     */
    @Test
    public void testDot() {
        assertEquals(1f, Vector3.RIGHT.dot(Vector3.RIGHT), 0f);
        assertEquals(0f, Vector3.RIGHT.dot(Vector3.UP), 0f);
        assertEquals(0f, Vector3.RIGHT.dot(Vector3.FORWARD), 0f);
        assertEquals(2f * 5f + 3f * 6f + 4f * 7f, new Vector3(2f, 3f, 4f).dot(new Vector3(5f, 6f, 7f)), 0f);
    }

    /**
     * {@link Vector3#equals(Object)} compares component bits: same values are
     * equal, {@code NaN} equals {@code NaN}, and {@code +0} is distinct from
     * {@code -0}.
     */
    @Test
    public void testEquals() {
        Vector3 a = new Vector3(1.5f, -2.25f, 0.5f);
        assertTrue(a.equals(a));
        assertEquals(new Vector3(1.5f, -2.25f, 0.5f), a);
        assertFalse(a.equals(new Vector3(1.5f, -2.25f, 0f)));
        assertFalse(a.equals(null));
        assertFalse(a.equals("(1.5, -2.25, 0.5)"));

        assertEquals(new Vector3(Float.NaN, 0f, 0f), new Vector3(Float.NaN, 0f, 0f));
        assertFalse(new Vector3(0f, 0f, 0f).equals(new Vector3(-0f, 0f, 0f)));
    }

    /**
     * {@link Vector3#floor()} rounds each component toward negative infinity.
     */
    @Test
    public void testFloor() {
        assertEquals(new Vector3(1f, 3f, 0f), new Vector3(1.1f, 3.9f, 0.1f).floor());
        assertEquals(new Vector3(-2f, -1f, -1f), new Vector3(-1.5f, -0.1f, -0.1f).floor());
        assertEquals(new Vector3(2f, -3f, 4f), new Vector3(2f, -3f, 4f).floor());
    }

    /**
     * {@link Vector3#isNormalized()} is an epsilon check against unit length.
     * Axis unit vectors and the result of {@link Vector3#normalized()} pass;
     * zero and clearly non-unit vectors do not.
     */
    @Test
    public void testIsNormalized() {
        assertTrue(new Vector3(1f, 0f, 0f).isNormalized());
        assertTrue(new Vector3(0f, 0f, -1f).isNormalized());
        assertTrue(new Vector3(1f, 2f, 2f).normalized().isNormalized());

        assertFalse(new Vector3(0f, 0f, 0f).isNormalized());
        assertFalse(new Vector3(1f, 1f, 1f).isNormalized());
        assertFalse(new Vector3(1f, 2f, 2f).isNormalized());
        assertTrue(new Vector3(1f + Vector3.EPSILON / 2f, 0f, 0f).isNormalized());
    }

    /**
     * {@link Vector3#lerp(Vector3, float)} mixes per component. Weight {@code 0}
     * is this vector, {@code 1} is the target, and values outside {@code [0, 1]}
     * extrapolate.
     */
    @Test
    public void testLerp() {
        Vector3 from = new Vector3(0f, 10f, 0f);
        Vector3 to = new Vector3(10f, 0f, 10f);

        assertEquals(from, from.lerp(to, 0f));
        assertEquals(to, from.lerp(to, 1f));
        assertEquals(new Vector3(5f, 5f, 5f), from.lerp(to, 0.5f));
        assertEquals(new Vector3(20f, -10f, 20f), from.lerp(to, 2f));
        assertEquals(from, from.lerp(to, 0.5f).lerp(from, 1f));
    }

    /**
     * {@link Vector3#magnitude()} is {@code sqrt(x² + y² + z²)}, including a
     * 3-unit diagonal and the zero vector.
     */
    @Test
    public void testMagnitude() {
        assertEquals(3f, new Vector3(1f, 2f, 2f).magnitude(), 0f);
        assertEquals(3f, new Vector3(-1f, 2f, 2f).magnitude(), 0f);
        assertEquals(0f, new Vector3(0f, 0f, 0f).magnitude(), 0f);
        assertEquals(1f, new Vector3(0f, 0f, 1f).magnitude(), 0f);
    }

    /**
     * {@link Vector3#moveTowards(Vector3, float)} steps each component toward
     * the target by at most {@code |delta|} and snaps when close enough. A
     * negative {@code delta} still approaches, but does not snap.
     */
    @Test
    public void testMoveTowards() {
        Vector3 from = new Vector3(0f, 0f, 0f);
        Vector3 to = new Vector3(10f, 4f, 8f);

        assertEquals(new Vector3(3f, 3f, 3f), from.moveTowards(to, 3f));
        assertEquals(to, from.moveTowards(to, 10f));
        assertEquals(to, to.moveTowards(to, 1f));
        assertEquals(new Vector3(2f, 2f, 2f), from.moveTowards(to, -2f));
    }

    /**
     * {@link Vector3#multiply(Vector3)} is the component-wise product and does
     * not modify the original vector.
     */
    @Test
    public void testMultiply() {
        Vector3 original = new Vector3(2f, 3f, 4f);
        assertEquals(new Vector3(8f, 15f, 24f), original.multiply(new Vector3(4f, 5f, 6f)));
        assertEquals(new Vector3(2f, 3f, 4f), original);
        assertEquals(new Vector3(0f, 0f, 0f), original.multiply(new Vector3(0f, 0f, 0f)));
        assertEquals(new Vector3(-4f, 6f, -8f), new Vector3(-2f, 3f, 4f).multiply(new Vector3(2f, 2f, -2f)));
    }

    /**
     * {@link Vector3#normalized()} scales to unit length, returns zero when
     * the input is approximately zero, and does not modify the original.
     */
    @Test
    public void testNormalized() {
        Vector3 original = new Vector3(1f, 2f, 2f);
        Vector3 unit = original.normalized();

        assertEquals(new Vector3(1f / 3f, 2f / 3f, 2f / 3f), unit);
        assertEquals(1f, unit.magnitude(), Vector3.EPSILON);
        assertEquals(new Vector3(1f, 2f, 2f), original);

        assertEquals(new Vector3(0f, 0f, 0f), new Vector3(0f, 0f, 0f).normalized());
        assertEquals(new Vector3(0f, 0f, 0f), new Vector3(Vector3.EPSILON / 2f, 0f, 0f).normalized());
    }

    /**
     * {@link Vector3#pow(float)} raises each component to the given power.
     */
    @Test
    public void testPow() {
        assertEquals(new Vector3(4f, 9f, 16f), new Vector3(2f, 3f, 4f).pow(2f));
        assertEquals(new Vector3(1f, 1f, 1f), new Vector3(2f, 3f, 4f).pow(0f));
        assertEquals(new Vector3(2f, 3f, 4f), new Vector3(4f, 9f, 16f).pow(0.5f));
        assertEquals(new Vector3(0.5f, 0.25f, 0.125f), new Vector3(2f, 4f, 8f).pow(-1f));

        Vector3 rootedNegative = new Vector3(-1f, 4f, 9f).pow(0.5f);
        assertTrue(Float.isNaN(rootedNegative.x));
        assertEquals(2f, rootedNegative.y, 0f);
        assertEquals(3f, rootedNegative.z, 0f);
    }

    /**
     * {@link Vector3#subtract(Vector3)} is {@code this - with}, not the reverse,
     * and does not modify the original vector.
     */
    @Test
    public void testSubtract() {
        Vector3 original = new Vector3(5f, 5f, 5f);
        assertEquals(new Vector3(4f, 3f, 1f), original.subtract(new Vector3(1f, 2f, 4f)));
        assertEquals(new Vector3(5f, 5f, 5f), original);
        assertEquals(new Vector3(1f, -1f, 0f), new Vector3(1f, 0f, 1f).subtract(new Vector3(0f, 1f, 1f)));
        assertEquals(new Vector3(-1f, 1f, 0f), new Vector3(0f, 1f, 1f).subtract(new Vector3(1f, 0f, 1f)));
    }

    /**
     * {@link Vector3#toString()} formats components as {@code (x, y, z)}.
     */
    @Test
    public void testToString() {
        assertEquals("(1.0, 2.0, 3.0)", new Vector3(1f, 2f, 3f).toString());
        assertEquals("(0.0, 0.0, 0.0)", new Vector3().toString());
        assertEquals("(-3.5, 4.25, 0.5)", new Vector3(-3.5f, 4.25f, 0.5f).toString());
    }
}
