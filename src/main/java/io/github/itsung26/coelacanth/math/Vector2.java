package io.github.itsung26.coelacanth.math;

public class Vector2 {
    public float x;
    public float y;

    public final Vector2 ZERO = new Vector2(0, 0);
    public final Vector2 ONE = new Vector2(1, 1);
    public final Vector2 LEFT = new Vector2(-1, 0);
    public final Vector2 RIGHT = new Vector2(1, 0);
    public final Vector2 UP = new Vector2(0, -1);
    public final Vector2 DOWN = new Vector2(0, 1);

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2i from) {

    }

    public Vector2() {
        this(0, 0);
    }

    /**
     * @return
     *      A new vector with all components in absolute values (i.e. positive).
     */
    public Vector2 abs() {
        return new Vector2(Math.abs(x), Math.abs(y));
    }

    /**
     * @return
     *      A new vector with all components rounded up (towards positive infinity)
     */
    public Vector2 ceil() {
        return new Vector2((float)Math.ceil(x), (float)Math.ceil(y));
    }

    

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

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
