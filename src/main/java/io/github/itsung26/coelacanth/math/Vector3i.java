package io.github.itsung26.coelacanth.math;

/**
 * A three-dimensional vector of {@code int} components.
 * <p>
 * Used throughout the renderer for discrete 3D values such as voxel
 * coordinates and grid indices. {@code y+} is up and {@code z-} is forward.
 * <p>
 * Operations return new vectors unless they produce a scalar. This instance's
 * {@link #x}, {@link #y}, and {@link #z} are left unchanged.
 */
public class Vector3i {
    /** First component. */
    public int x;
    /** Second component. */
    public int y;
    /** Third component. */
    public int z;

    /** The zero vector, {@code (0, 0, 0)}. */
    public static final Vector3i ZERO = new Vector3i(0, 0, 0);
    /** All components set to one, {@code (1, 1, 1)}. */
    public static final Vector3i ONE = new Vector3i(1, 1, 1);
    /** Unit vector pointing left, {@code (-1, 0, 0)}. */
    public static final Vector3i LEFT = new Vector3i(-1, 0, 0);
    /** Unit vector pointing right, {@code (1, 0, 0)}. */
    public static final Vector3i RIGHT = new Vector3i(1, 0, 0);
    /** Unit vector pointing up, {@code (0, 1, 0)}. */
    public static final Vector3i UP = new Vector3i(0, 1, 0);
    /** Unit vector pointing down, {@code (0, -1, 0)}. */
    public static final Vector3i DOWN = new Vector3i(0, -1, 0);
    /** Unit vector pointing forward, {@code (0, 0, -1)}. */
    public static final Vector3i FORWARD = new Vector3i(0, 0, -1);
    /** Unit vector pointing back, {@code (0, 0, 1)}. */
    public static final Vector3i BACK = new Vector3i(0, 0, 1);

    /**
     * Constructs a vector with the given components.
     *
     * @param x first component
     * @param y second component
     * @param z third component
     */
    public Vector3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructs a vector by truncating the float components of {@code from}
     * toward zero.
     *
     * @param from float vector to convert
     */
    public Vector3i(Vector3 from) {
        this((int) from.x, (int) from.y, (int) from.z);
    }

    /**
     * Constructs a zero vector, {@code (0, 0, 0)}.
     */
    public Vector3i() {
        this(0, 0, 0);
    }

    /**
     * Returns the component-wise sum of this vector and {@code to}.
     *
     * @param to the vector to add
     * @return a new vector {@code (x + to.x, y + to.y, z + to.z)}
     */
    public Vector3i add(Vector3i to) {
        return new Vector3i(x + to.x, y + to.y, z + to.z);
    }

    /**
     * Returns the component-wise difference of this vector minus {@code with}.
     * The operand order is {@code this - with}, not {@code with - this}.
     *
     * @param with the vector to subtract
     * @return a new vector {@code (x - with.x, y - with.y, z - with.z)}
     */
    public Vector3i subtract(Vector3i with) {
        return new Vector3i(x - with.x, y - with.y, z - with.z);
    }

    /**
     * Returns the component-wise (Hadamard) product of this vector and
     * {@code with}. This is not a scalar multiple and not a cross product.
     *
     * @param with the vector to multiply with
     * @return a new vector {@code (x * with.x, y * with.y, z * with.z)}
     */
    public Vector3i multiply(Vector3i with) {
        return new Vector3i(x * with.x, y * with.y, z * with.z);
    }

    /**
     * Returns a new vector with each component raised to {@code power}, then
     * truncated toward zero. A negative base with a non-integer exponent yields
     * {@code 0} for that component, because {@code (int) NaN} is {@code 0}.
     *
     * @param power the exponent applied to all three components
     * @return a new vector {@code ((int) x^power, (int) y^power, (int) z^power)}
     */
    public Vector3i pow(float power) {
        return new Vector3i((int) Math.pow(x, power), (int) Math.pow(y, power),
                (int) Math.pow(z, power));
    }

    /**
     * Returns the Euclidean length of this vector,
     * {@code sqrt(x² + y² + z²)}.
     *
     * @return the magnitude as a {@code float}; {@code 0} for the zero vector
     */
    public float magnitude() {
        return (float) Math.sqrt((long) x * x + (long) y * y + (long) z * z);
    }

    /**
     * Returns a new vector with each component replaced by its absolute value.
     *
     * @return a vector whose components are all non-negative
     */
    public Vector3i abs() {
        return new Vector3i(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    /**
     * Returns the Euclidean distance from this vector to {@code to},
     * {@code sqrt((to.x - x)² + (to.y - y)² + (to.z - z)²)}.
     *
     * @param to the target position
     * @return the distance as a {@code float}; {@code 0} when {@code to} is this
     *         point
     */
    public float distanceTo(Vector3i to) {
        long dx = (long) to.x - x;
        long dy = (long) to.y - y;
        long dz = (long) to.z - z;
        return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    //#region BOILERPLATE===========================================================================
    /**
     * Compares this vector to {@code obj} by exact integer equality of all three
     * components.
     *
     * @param obj the object to compare
     * @return {@code true} if {@code obj} is a {@code Vector3i} with the same
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
        Vector3i other = (Vector3i) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        if (z != other.z)
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
