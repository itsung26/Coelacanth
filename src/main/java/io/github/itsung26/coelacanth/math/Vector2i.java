package io.github.itsung26.coelacanth.math;

/**
 * A two-dimensional vector of {@code int} components.
 * <p>
 * Used throughout the renderer for discrete 2D positions such as pixel
 * coordinates and grid indices. The Y axis points down, matching screen space:
 * {@link #UP} is {@code (0, -1)} and {@link #DOWN} is {@code (0, 1)}.
 * <p>
 * Operations return new vectors unless they produce a scalar. This instance's
 * {@link #x} and {@link #y} are left unchanged.
 */
public class Vector2i {
    /** Horizontal component. */
    public int x;
    /** Vertical component. Positive values point down. */
    public int y;

    /** The zero vector, {@code (0, 0)}. */
    public static final Vector2i ZERO = new Vector2i(0, 0);
    /** Both components set to one, {@code (1, 1)}. */
    public static final Vector2i ONE = new Vector2i(1, 1);
    /** Unit vector pointing left, {@code (-1, 0)}. */
    public static final Vector2i LEFT = new Vector2i(-1, 0);
    /** Unit vector pointing right, {@code (1, 0)}. */
    public static final Vector2i RIGHT = new Vector2i(1, 0);
    /** Unit vector pointing up in screen space, {@code (0, -1)}. */
    public static final Vector2i UP = new Vector2i(0, -1);
    /** Unit vector pointing down in screen space, {@code (0, 1)}. */
    public static final Vector2i DOWN = new Vector2i(0, 1);

    /**
     * Constructs a vector with the given components.
     *
     * @param x horizontal component
     * @param y vertical component
     */
    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a vector by truncating the float components of {@code from}
     * toward zero.
     *
     * @param from float vector to convert
     */
    public Vector2i(Vector2 from) {
        this((int) from.x, (int) from.y);
    }

    /**
     * Constructs a zero vector, {@code (0, 0)}.
     */
    public Vector2i() {
        this(0, 0);
    }

    /**
     * Returns the component-wise sum of this vector and {@code to}.
     *
     * @param to the vector to add
     * @return a new vector {@code (x + to.x, y + to.y)}
     */
    public Vector2i add(Vector2i to) {
        return new Vector2i(x + to.x, y + to.y);
    }

    /**
     * Returns the component-wise difference of this vector minus {@code with}.
     * The operand order is {@code this - with}, not {@code with - this}.
     *
     * @param with the vector to subtract
     * @return a new vector {@code (x - with.x, y - with.y)}
     */
    public Vector2i subtract(Vector2i with) {
        return new Vector2i(x - with.x, y - with.y);
    }

    /**
     * Returns the component-wise (Hadamard) product of this vector and
     * {@code with}. This is not a scalar multiple and not a 2D cross product.
     *
     * @param with the vector to multiply with
     * @return a new vector {@code (x * with.x, y * with.y)}
     */
    public Vector2i multiply(Vector2i with) {
        return new Vector2i(x * with.x, y * with.y);
    }

    /**
     * Returns a new vector with each component raised to {@code power}, then
     * truncated toward zero. A negative base with a non-integer exponent yields
     * {@code 0} for that component, because {@code (int) NaN} is {@code 0}.
     *
     * @param power the exponent applied to both components
     * @return a new vector {@code ((int) x^power, (int) y^power)}
     */
    public Vector2i pow(float power) {
        return new Vector2i((int) Math.pow(x, power), (int) Math.pow(y, power));
    }

    /**
     * Returns the Euclidean length of this vector,
     * {@code sqrt(x² + y²)}.
     *
     * @return the magnitude as a {@code float}; {@code 0} for the zero vector
     */
    public float magnitude() {
        return (float) Math.sqrt((long) x * x + (long) y * y);
    }

    /**
     * Returns a new vector with each component replaced by its absolute value.
     *
     * @return a vector whose components are all non-negative
     */
    public Vector2i abs() {
        return new Vector2i(Math.abs(x), Math.abs(y));
    }

    /**
     * Returns the Euclidean distance from this vector to {@code to},
     * {@code sqrt((to.x - x)² + (to.y - y)²)}.
     *
     * @param to the target position
     * @return the distance as a {@code float}; {@code 0} when {@code to} is this
     *         point
     */
    public float distanceTo(Vector2i to) {
        long dx = (long) to.x - x;
        long dy = (long) to.y - y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    //#region BOILERPLATE===========================================================================
    /**
     * Compares this vector to {@code obj} by exact integer equality of both
     * components.
     *
     * @param obj the object to compare
     * @return {@code true} if {@code obj} is a {@code Vector2i} with the same
     *         components
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector2i other = (Vector2i) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
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
