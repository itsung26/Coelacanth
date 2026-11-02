package io.github.itsung26.coelacanth.core;

import java.util.Arrays;

/**
 * Stores a bitmap of pixels representing a complete frame. This would typically be
 * allocated in GPU memory (VRAM) rather than CPU memory, but Coelacanth is entirely
 * CPU-based, so the framebuffer is made accessible to the runtime components of the engine.
 */
public class Framebuffer {
    /** Color buffer. */
    private int[] color;
    /** Depth buffer. */
    private float[] depth;
    /** Framebuffer width in pixels. */
    private int width;
    /** Framebuffer height in pixels. */
    private int height;

    public Framebuffer (int width, int height) {
        this.width = width;
        this.height = height;
        color = new int[width * height];
        depth = new float[width * height];

        // Pure red.
        Arrays.fill(color, 0xFFFF0000);
        // Populate the depth buffer.
        Arrays.fill(depth, 1f);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getColorBuffer() {
        return color;
    }

    public float[] getDepthBuffer() {
        return depth;
    }

}
