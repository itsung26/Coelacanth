package io.github.itsung26.coelacanth.math;

/**
 * A two-dimensional vector of {@code float} components.
 * <p>
 * Used throughout the renderer for 2D positions, directions, and texture
 * coordinates. The Y axis points down, matching screen space: {@link #UP} is
 * {@code (0, -1)} and {@link #DOWN} is {@code (0, 1)}.
 * <p>
 * Operations return new vectors unless they produce a scalar. This instance's
 * {@link #x} and {@link #y} are left unchanged.
 */
public class Vector2 {
    /**
     * Absolute tolerance used by approximate length checks.
     * Magnitudes within this distance of {@code 0} or {@code 1} are treated as
     * those values.
     */
    public static final float EPSILON = 1e-6f;

    /** Horizontal component. */
    public float x;
    /** Vertical component. Positive values point down. */
    public float y;

    /** The zero vector, {@code (0, 0)}. */
    public static final Vector2 ZERO = new Vector2(0, 0);
    /** Both components set to one, {@code (1, 1)}. */
    public static final Vector2 ONE = new Vector2(1, 1);
    /** Unit vector pointing left, {@code (-1, 0)}. */
    public static final Vector2 LEFT = new Vector2(-1, 0);
    /** Unit vector pointing right, {@code (1, 0)}. */
    public static final Vector2 RIGHT = new Vector2(1, 0);
    /** Unit vector pointing up in screen space, {@code (0, -1)}. */
    public static final Vector2 UP = new Vector2(0, -1);
    /** Unit vector pointing down in screen space, {@code (0, 1)}. */
    public static final Vector2 DOWN = new Vector2(0, 1);

    /**
     * Constructs a vector with the given components.
     *
     * @param x horizontal component
     * @param y vertical component
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a vector by copying the integer components of {@code from}.
     *
     * @param from integer vector to convert
     */
    public Vector2(Vector2i from) {

    }

    /**
     * Constructs a zero vector, {@code (0, 0)}.
     */
    public Vector2() {
        this(0, 0);
    }

    public Vector2 add(Vector2 to) {
        return new Vector2(x + to.x, y + to.y);
    }

    // this - with, not with - this
    public Vector2 subtract(Vector2 with) {
        return new Vector2(x - with.x, y - with.y);
    }

    public Vector2 multiply(Vector2 with) {
        return new Vector2(x * with.x, y * with.y);
    }

    public Vector2 pow(float power) {
        return new Vector2((float) Math.pow(x, power), (float) Math.pow(y, power));
    }

    /**
     * Returns the Euclidean length of this vector,
     * {@code sqrt(x² + y²)}.
     *
     * @return the magnitude; {@code 0} for the zero vector
     */
    public float magnitude() {
        return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * Returns a new vector with each component replaced by its absolute value.
     *
     * @return a vector whose components are all non-negative
     */
    public Vector2 abs() {
        return new Vector2(Math.abs(x), Math.abs(y));
    }

    /**
     * Returns the dot product of this vector and {@code with}.
     * Equal to {@code |this| · |with| · cos(θ)}, where {@code θ} is the angle
     * between them.
     *
     * @param with the other vector
     * @return the scalar dot product
     */
    public float dot(Vector2 with) {
        return (x * with.x) + (y * with.y);
    }

    /**
     * Returns the 2D analog of the cross product of this vector and {@code with}.
     * This is the signed magnitude of the 3D cross product if both vectors were
     * treated as lying in the XY plane: {@code x * with.y - y * with.x}.
     * The sign is positive when {@code with} is counterclockwise from this
     * vector.
     *
     * @param with the other vector
     * @return the signed scalar cross product
     */
    public float cross(Vector2 with) {
        return (x * with.y) - (y * with.x);
    }

    /**
     * Returns a new vector pointing in the same direction with length {@code 1}.
     * If this vector's length is within {@link #EPSILON} of zero, returns
     * {@code (0, 0)} instead of dividing by zero.
     *
     * @return a unit-length copy, or the zero vector if this vector is
     *         approximately zero
     */
    public Vector2 normalized() {
        float mag = magnitude();
        if (mag < EPSILON) {
            return new Vector2(0, 0);
        }

        return new Vector2(x / mag, y / mag);
    }

    /**
     * Returns whether this vector has approximately unit length:
     * {@code |magnitude() - 1| < EPSILON}.
     * <p>
     * This accepts vectors produced by {@link #normalized()} despite rounding
     * error. The tradeoff is that the result may be incorrect when the magnitude
     * is very close to zero.
     *
     * @return {@code true} if {@link #magnitude()} is within {@link #EPSILON} of
     *         {@code 1}
     */
    public boolean isNormalized() {
        return Math.abs(magnitude() - 1f) < EPSILON;
    }

    /**
     * Returns a new vector with each component rounded toward positive infinity.
     *
     * @return a vector of ceiled components
     */
    public Vector2 ceil() {
        return new Vector2((float) Math.ceil(x), (float) Math.ceil(y));
    }

    /**
     * Returns a new vector with each component rounded toward negative infinity.
     *
     * @return a vector of floored components
     */
    public Vector2 floor() {
        return new Vector2((float) Math.floor(x), (float) Math.floor(y));
    }

    public Vector2 clamp(Vector2 min, Vector2 max) {
        return new Vector2(Math.clamp(x, min.x, max.x), Math.clamp(y, min.y, max.y));
    }

    public Vector2 clampf(float min, float max) {
        return new Vector2(Math.clamp(x, min, max), Math.clamp(y, min, max));
    }

    public float distanceTo(Vector2 to) {
        return (float) Math.sqrt((Math.pow(to.x - x, 2)) + (Math.pow(to.y - y, 2)));
    }

    /**
     * Moves this vector towards to by amount delta.
     * @param to
     * @param delta
     * @return
     */
    public Vector2 moveTowards(Vector2 to, float delta) {
        return new Vector2(moveTowardsf(x, to.x, delta), moveTowardsf(y, to.y, delta));
    }

    public Vector2 lerp(Vector2 to, float weight) {
        return new Vector2(lerpf(x, to.x, weight), lerpf(y, to.y, weight));
    }

    //#region PRIVATE HELPERS=======================================================================
    private static float lerpf(float a, float b, float weight) {
        return a + (b - a) * weight;
    }

    private static float moveTowardsf(float a, float b, float delta) {
        if (Math.abs(b - a) <= delta) {
            return b;
        }

        return a + Math.copySign(delta, b - a);
    }
    //#endregion==================================================================================

    //#region BOILERPLATE===========================================================================
    /**
     * Compares this vector to {@code obj} by bit-exact {@code float} equality of
     * both components. {@code NaN} equals {@code NaN}, and {@code +0} is distinct
     * from {@code -0}.
     *
     * @param obj the object to compare
     * @return {@code true} if {@code obj} is a {@code Vector2} with the same
     *         component bits
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector2 other = (Vector2) obj;
        if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
            return false;
        if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
            return false;
        return true;
    }

    /**
     * Returns a string of the form {@code (x, y)}.
     *
     * @return the component pair in parentheses
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    //#endregion================================================================================
}
