package io.github.itsung26.coelacanth.math;

/**
 * A three-dimensional vector of {@code float} components.
 * <p>
 * Used throughout the renderer for 3D positions, directions, and normals.
 * {@code y+} is up and {@code z-} is forward.
 * <p>
 * Operations return new vectors unless they produce a scalar. This instance's
 * {@link #x}, {@link #y}, and {@link #z} are left unchanged.
 */
public class Vector3 {
    /**
     * Absolute tolerance used by approximate length checks.
     * Magnitudes within this distance of {@code 0} or {@code 1} are treated as
     * those values.
     */
    public static final float EPSILON = 1e-6f;

    /** First component. */
    public float x;
    /** Second component. */
    public float y;
    /** Third component. */
    public float z;

    /** The zero vector, {@code (0, 0, 0)}. */
    public static final Vector3 ZERO = new Vector3(0, 0, 0);
    /** All components set to one, {@code (1, 1, 1)}. */
    public static final Vector3 ONE = new Vector3(1, 1, 1);
    /** Unit vector pointing left, {@code (-1, 0, 0)}. */
    public static final Vector3 LEFT = new Vector3(-1, 0, 0);
    /** Unit vector pointing right, {@code (1, 0, 0)}. */
    public static final Vector3 RIGHT = new Vector3(1, 0, 0);
    /** Unit vector pointing up, {@code (0, 1, 0)}. */
    public static final Vector3 UP = new Vector3(0, 1, 0);
    /** Unit vector pointing down, {@code (0, -1, 0)}. */
    public static final Vector3 DOWN = new Vector3(0, -1, 0);
    /** Unit vector pointing forward, {@code (0, 0, -1)}. */
    public static final Vector3 FORWARD = new Vector3(0, 0, -1);
    /** Unit vector pointing back, {@code (0, 0, 1)}. */
    public static final Vector3 BACK = new Vector3(0, 0, 1);

    /**
     * Constructs a vector with the given components.
     *
     * @param x first component
     * @param y second component
     * @param z third component
     */
    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructs a vector by copying the integer components of {@code from}.
     *
     * @param from integer vector to convert
     */
    public Vector3(Vector3i from) {
        this(from.x, from.y, from.z);
    }

    /**
     * Constructs a zero vector, {@code (0, 0, 0)}.
     */
    public Vector3() {
        this(0, 0, 0);
    }

    /**
     * Returns the component-wise sum of this vector and {@code to}.
     *
     * @param to the vector to add
     * @return a new vector {@code (x + to.x, y + to.y, z + to.z)}
     */
    public Vector3 add(Vector3 to) {
        return new Vector3(x + to.x, y + to.y, z + to.z);
    }

    /**
     * Returns the component-wise difference of this vector minus {@code with}.
     * The operand order is {@code this - with}, not {@code with - this}.
     *
     * @param with the vector to subtract
     * @return a new vector {@code (x - with.x, y - with.y, z - with.z)}
     */
    public Vector3 subtract(Vector3 with) {
        return new Vector3(x - with.x, y - with.y, z - with.z);
    }

    /**
     * Returns the component-wise (Hadamard) product of this vector and
     * {@code with}. This is not a scalar multiple and not a cross product.
     *
     * @param with the vector to multiply with
     * @return a new vector {@code (x * with.x, y * with.y, z * with.z)}
     */
    public Vector3 multiply(Vector3 with) {
        return new Vector3(x * with.x, y * with.y, z * with.z);
    }

    /**
     * Returns a new vector with each component raised to {@code power}.
     * A negative base with a non-integer exponent yields {@code NaN} for that
     * component, matching {@link Math#pow(double, double)}.
     *
     * @param power the exponent applied to all three components
     * @return a new vector {@code (x^power, y^power, z^power)}
     */
    public Vector3 pow(float power) {
        return new Vector3((float) Math.pow(x, power), (float) Math.pow(y, power),
                (float) Math.pow(z, power));
    }

    /**
     * Returns the Euclidean length of this vector,
     * {@code sqrt(x² + y² + z²)}.
     *
     * @return the magnitude; {@code 0} for the zero vector
     */
    public float magnitude() {
        return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    /**
     * Returns a new vector with each component replaced by its absolute value.
     *
     * @return a vector whose components are all non-negative
     */
    public Vector3 abs() {
        return new Vector3(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    /**
     * Returns the dot product of this vector and {@code with}.
     * Equal to {@code |this| · |with| · cos(θ)}, where {@code θ} is the angle
     * between them.
     *
     * @param with the other vector
     * @return the scalar dot product
     */
    public float dot(Vector3 with) {
        return (x * with.x) + (y * with.y) + (z * with.z);
    }

    /**
     * Returns the cross product of this vector and {@code with}.
     * The result is perpendicular to both operands, with magnitude
     * {@code |this| · |with| · sin(θ)}. The direction follows the right-hand
     * rule.
     *
     * @param with the other vector
     * @return a new vector {@code (y * with.z - z * with.y,
     *         z * with.x - x * with.z, x * with.y - y * with.x)}
     */
    public Vector3 cross(Vector3 with) {
        return new Vector3((y * with.z) - (z * with.y), (z * with.x) - (x * with.z),
                (x * with.y) - (y * with.x));
    }

    /**
     * Returns a new vector pointing in the same direction with length {@code 1}.
     * If this vector's length is within {@link #EPSILON} of zero, returns
     * {@code (0, 0, 0)} instead of dividing by zero.
     *
     * @return a unit-length copy, or the zero vector if this vector is
     *         approximately zero
     */
    public Vector3 normalized() {
        float mag = magnitude();
        if (mag < EPSILON) {
            return new Vector3(0, 0, 0);
        }

        return new Vector3(x / mag, y / mag, z / mag);
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
    public Vector3 ceil() {
        return new Vector3((float) Math.ceil(x), (float) Math.ceil(y), (float) Math.ceil(z));
    }

    /**
     * Returns a new vector with each component rounded toward negative infinity.
     *
     * @return a vector of floored components
     */
    public Vector3 floor() {
        return new Vector3((float) Math.floor(x), (float) Math.floor(y), (float) Math.floor(z));
    }

    /**
     * Clamps each component independently into the range given by the matching
     * component of {@code min} and {@code max}.
     *
     * @param min inclusive per-component lower bound
     * @param max inclusive per-component upper bound
     * @return a new vector with each component clamped to {@code [min, max]}
     * @throws IllegalArgumentException if a component of {@code min} is greater
     *         than the matching component of {@code max}
     */
    public Vector3 clamp(Vector3 min, Vector3 max) {
        return new Vector3(Math.clamp(x, min.x, max.x), Math.clamp(y, min.y, max.y),
                Math.clamp(z, min.z, max.z));
    }

    /**
     * Clamps all three components into the scalar range {@code [min, max]}.
     *
     * @param min inclusive lower bound for all components
     * @param max inclusive upper bound for all components
     * @return a new vector with each component clamped to {@code [min, max]}
     * @throws IllegalArgumentException if {@code min} is greater than {@code max}
     */
    public Vector3 clampf(float min, float max) {
        return new Vector3(Math.clamp(x, min, max), Math.clamp(y, min, max), Math.clamp(z, min, max));
    }

    /**
     * Returns the Euclidean distance from this vector to {@code to},
     * {@code sqrt((to.x - x)² + (to.y - y)² + (to.z - z)²)}.
     *
     * @param to the target position
     * @return the distance; {@code 0} when {@code to} is this point
     */
    public float distanceTo(Vector3 to) {
        return (float) Math.sqrt((Math.pow(to.x - x, 2)) + (Math.pow(to.y - y, 2))
                + (Math.pow(to.z - z, 2)));
    }

    /**
     * Moves this vector toward {@code to} by at most {@code |delta|} on each
     * axis. If the remaining distance along an axis is within {@code delta},
     * that component snaps to the target. The step direction always points at
     * {@code to}; a negative {@code delta} still approaches, but will not snap.
     *
     * @param to the target position
     * @param delta the maximum step per component
     * @return a new vector stepped toward {@code to}
     */
    public Vector3 moveTowards(Vector3 to, float delta) {
        return new Vector3(moveTowardsf(x, to.x, delta), moveTowardsf(y, to.y, delta),
                moveTowardsf(z, to.z, delta));
    }

    /**
     * Linearly interpolates between this vector and {@code to} per component.
     * {@code weight} is not clamped: {@code 0} returns this vector, {@code 1}
     * returns {@code to}, and values outside {@code [0, 1]} extrapolate.
     *
     * @param to the interpolation target
     * @param weight the mix factor
     * @return a new vector {@code this + (to - this) * weight}
     */
    public Vector3 lerp(Vector3 to, float weight) {
        return new Vector3(lerpf(x, to.x, weight), lerpf(y, to.y, weight), lerpf(z, to.z, weight));
    }

    //#region PRIVATE HELPERS=======================================================================
    /** Returns {@code a + (b - a) * weight}. */
    private static float lerpf(float a, float b, float weight) {
        return a + (b - a) * weight;
    }

    /**
     * Steps {@code a} toward {@code b} by at most {@code delta}. Returns
     * {@code b} when the remaining distance is within {@code delta}.
     */
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
     * all three components. {@code NaN} equals {@code NaN}, and {@code +0} is
     * distinct from {@code -0}.
     *
     * @param obj the object to compare
     * @return {@code true} if {@code obj} is a {@code Vector3} with the same
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
        Vector3 other = (Vector3) obj;
        if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
            return false;
        if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
            return false;
        if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z))
            return false;
        return true;
    }

    /**
     * Returns a string of the form {@code (x, y, z)}.
     *
     * @return the component tuple in parentheses
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
    //#endregion================================================================================
}
