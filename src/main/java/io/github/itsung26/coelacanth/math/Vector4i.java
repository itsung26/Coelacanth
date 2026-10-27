package io.github.itsung26.coelacanth.math;

/**
 * A four-dimensional vector of {@code int} components.
 * <p>
 * Used throughout the renderer for discrete 4D values such as integer colors
 * and packed coordinates.
 * <p>
 * Operations return new vectors unless they produce a scalar. This instance's
 * {@link #x}, {@link #y}, {@link #z}, and {@link #w} are left unchanged.
 */
public class Vector4i {
    /** First component. */
    public int x;
    /** Second component. */
    public int y;
    /** Third component. */
    public int z;
    /** Fourth component. */
    public int w;

    /** The zero vector, {@code (0, 0, 0, 0)}. */
    public static final Vector4i ZERO = new Vector4i(0, 0, 0, 0);
    /** All components set to one, {@code (1, 1, 1, 1)}. */
    public static final Vector4i ONE = new Vector4i(1, 1, 1, 1);

    /**
     * Constructs a vector with the given components.
     *
     * @param x first component
     * @param y second component
     * @param z third component
     * @param w fourth component
     */
    public Vector4i(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Constructs a vector by truncating the float components of {@code from}
     * toward zero.
     *
     * @param from float vector to convert
     */
    public Vector4i(Vector4 from) {
        this((int) from.x, (int) from.y, (int) from.z, (int) from.w);
    }

    /**
     * Constructs a zero vector, {@code (0, 0, 0, 0)}.
     */
    public Vector4i() {
        this(0, 0, 0, 0);
    }

    /**
     * Returns the component-wise sum of this vector and {@code to}.
     *
     * @param to the vector to add
     * @return a new vector {@code (x + to.x, y + to.y, z + to.z, w + to.w)}
     */
    public Vector4i add(Vector4i to) {
        return new Vector4i(x + to.x, y + to.y, z + to.z, w + to.w);
    }

    /**
     * Returns the component-wise difference of this vector minus {@code with}.
     * The operand order is {@code this - with}, not {@code with - this}.
     *
     * @param with the vector to subtract
     * @return a new vector {@code (x - with.x, y - with.y, z - with.z, w - with.w)}
     */
    public Vector4i subtract(Vector4i with) {
        return new Vector4i(x - with.x, y - with.y, z - with.z, w - with.w);
    }

    /**
     * Returns the component-wise (Hadamard) product of this vector and
     * {@code with}. This is not a scalar multiple and not a cross product.
     *
     * @param with the vector to multiply with
     * @return a new vector {@code (x * with.x, y * with.y, z * with.z, w * with.w)}
     */
    public Vector4i multiply(Vector4i with) {
        return new Vector4i(x * with.x, y * with.y, z * with.z, w * with.w);
    }

    /**
     * Returns a new vector with each component raised to {@code power}, then
     * truncated toward zero. A negative base with a non-integer exponent yields
     * {@code 0} for that component, because {@code (int) NaN} is {@code 0}.
     *
     * @param power the exponent applied to all four components
     * @return a new vector {@code ((int) x^power, (int) y^power, (int) z^power, (int) w^power)}
     */
    public Vector4i pow(float power) {
        return new Vector4i((int) Math.pow(x, power), (int) Math.pow(y, power),
                (int) Math.pow(z, power), (int) Math.pow(w, power));
    }

    /**
     * Returns the Euclidean length of this vector,
     * {@code sqrt(x² + y² + z² + w²)}.
     *
     * @return the magnitude as a {@code float}; {@code 0} for the zero vector
     */
    public float magnitude() {
        return (float) Math.sqrt((long) x * x + (long) y * y + (long) z * z + (long) w * w);
    }

    /**
     * Returns a new vector with each component replaced by its absolute value.
     *
     * @return a vector whose components are all non-negative
     */
    public Vector4i abs() {
        return new Vector4i(Math.abs(x), Math.abs(y), Math.abs(z), Math.abs(w));
    }

    /**
     * Returns the Euclidean distance from this vector to {@code to},
     * {@code sqrt((to.x - x)² + (to.y - y)² + (to.z - z)² + (to.w - w)²)}.
     *
     * @param to the target position
     * @return the distance as a {@code float}; {@code 0} when {@code to} is this
     *         point
     */
    public float distanceTo(Vector4i to) {
        long dx = (long) to.x - x;
        long dy = (long) to.y - y;
        long dz = (long) to.z - z;
        long dw = (long) to.w - w;
        return (float) Math.sqrt(dx * dx + dy * dy + dz * dz + dw * dw);
    }

    //#region BOILERPLATE===========================================================================
    /**
     * Compares this vector to {@code obj} by exact integer equality of all four
     * components.
     *
     * @param obj the object to compare
     * @return {@code true} if {@code obj} is a {@code Vector4i} with the same
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
        Vector4i other = (Vector4i) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        if (z != other.z)
            return false;
        if (w != other.w)
            return false;
        return true;
    }

    /**
     * Returns a string of the form {@code (x, y, z, w)}.
     *
     * @return the component tuple in parentheses
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }
    //#endregion================================================================================
}
