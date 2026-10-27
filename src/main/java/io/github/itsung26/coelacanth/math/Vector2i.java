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
    public final Vector2i ZERO = new Vector2i(0, 0);
    /** Both components set to one, {@code (1, 1)}. */
    public final Vector2i ONE = new Vector2i(1, 1);
    /** Unit vector pointing left, {@code (-1, 0)}. */
    public final Vector2i LEFT = new Vector2i(-1, 0);
    /** Unit vector pointing right, {@code (1, 0)}. */
    public final Vector2i RIGHT = new Vector2i(1, 0);
    /** Unit vector pointing up in screen space, {@code (0, -1)}. */
    public final Vector2i UP = new Vector2i(0, -1);
    /** Unit vector pointing down in screen space, {@code (0, 1)}. */
    public final Vector2i DOWN = new Vector2i(0, 1);

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
     * Returns the dot product of this vector and {@code with}.
     * Equal to {@code |this| · |with| · cos(θ)}, where {@code θ} is the angle
     * between them.
     *
     * @param with the other vector
     * @return the scalar dot product
     */
    public int dot(Vector2i with) {
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
    public int cross(Vector2i with) {
        return (x * with.y) - (y * with.x);
    }

    /**
     * Returns a new {@link Vector2} pointing in the same direction with length
     * {@code 1}. Integer components cannot represent a unit vector unless this
     * vector is already axis-aligned, so the result is a float vector.
     * If this vector's length is within {@link Vector2#EPSILON} of zero, returns
     * {@code (0, 0)} instead of dividing by zero.
     *
     * @return a unit-length {@link Vector2}, or the zero vector if this vector
     *         is approximately zero
     */
    public Vector2 normalized() {
        float mag = magnitude();
        if (mag < Vector2.EPSILON) {
            return new Vector2(0, 0);
        }

        return new Vector2(x / mag, y / mag);
    }

    /**
     * Returns whether this vector has unit length.
     * The only integer unit vectors are the axis directions
     * {@code (±1, 0)} and {@code (0, ±1)}, so the check is exact:
     * {@code x² + y² == 1}.
     *
     * @return {@code true} if {@link #magnitude()} is exactly {@code 1}
     */
    public boolean isNormalized() {
        return (long) x * x + (long) y * y == 1L;
    }

    /**
     * Returns a new vector with each component rounded toward positive infinity.
     * Integer components are already integral, so this is a copy of this vector.
     *
     * @return a vector of ceiled components
     */
    public Vector2i ceil() {
        return new Vector2i(x, y);
    }

    /**
     * Returns a new vector with each component rounded toward negative infinity.
     * Integer components are already integral, so this is a copy of this vector.
     *
     * @return a vector of floored components
     */
    public Vector2i floor() {
        return new Vector2i(x, y);
    }

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
}
