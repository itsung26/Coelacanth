package io.github.itsung26.coelacanth.core;

/**
 * Stores a bitmap of pixels representing a complete frame. This would typically be
 * allocated in GPU memory (VRAM) rather than CPU memory, but Coelacanth is entirely
 * CPU-based, so the framebuffer is made accessible to the runtime components of the engine.
 */
public class Framebuffer {
    /** Color buffer. */
    int[] color;
    /** Depth buffer. */
    float[] depth;
    /** Framebuffer width in pixels. */
    int width;
    /** Framebuffer height in pixels. */
    int height;

    public Framebuffer (int width, int height) {
        this.width = width;
        this.height = height;
        color = new int[]
    }
}
