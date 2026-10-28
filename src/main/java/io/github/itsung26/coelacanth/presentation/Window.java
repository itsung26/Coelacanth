package io.github.itsung26.coelacanth.presentation;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.io.Closeable;
import java.io.IOException;

import org.lwjgl.glfw.GLFWWindowCloseCallbackI;

import io.github.itsung26.coelacanth.math.Vector2i;

/**
 * Object-oriented wrap for GLFW's C-implemented window system.
 * <p>
 * GLFW exposes windows as opaque {@code long} handles (native
 * {@code GLFWwindow *} pointers). This class owns one of those handles and the
 * GLFW lifetime around it: construction initializes GLFW and creates the
 * window, and {@link #close()} destroys the window and terminates GLFW.
 * <p>
 * The window is created hidden, non-resizable, with vsync enabled
 * ({@code glfwSwapInterval(1)}). It is shown only after the OpenGL context is
 * current. Intended for use with try-with-resources, as in the demo
 * {@code Main} loop: poll events, issue a draw, then {@link #swapBuffers()}.
 */
public class Window implements Closeable{
    /** Client-area height in pixels. */
    private int height;
    /** Client-area width in pixels. */
    private int width;
    /** Text shown in the window title bar. */
    private String title;
    /** Native {@code GLFWwindow *} handle returned by {@code glfwCreateWindow}. */
    private final long longAddress;

    /**
     * Initializes GLFW and creates a visible, non-resizable window.
     * <p>
     * Parameter order is height then width, unlike GLFW's
     * {@code glfwCreateWindow(width, height, ...)}.
     *
     * @param height client-area height in pixels
     * @param width  client-area width in pixels
     * @param title  window title-bar text
     * @throws IllegalStateException if GLFW fails to initialize or the window
     *         cannot be created
     */
    public Window(int height, int width, String title) {
        this.height = height;
        this.width = width;
        this.title = title;

        if (!glfwInit()) {
            throw new IllegalStateException("Failed to initialize GLFW.");
        }

        // Clear any cached window hints.
        glfwDefaultWindowHints();
        // Do not show the window until it is fully ready.
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        longAddress = glfwCreateWindow(width, height, title, NULL, NULL);

        // Validate to ensure creation succeeded.
        if (longAddress == NULL) {
            throw new IllegalStateException("Failed to initialize window.");
        }

        glfwMakeContextCurrent(longAddress);
        glfwSwapInterval(1);
        // Now the window is fully configured and ready to be shown.
        glfwShowWindow(longAddress);
    }

    /**
     * Returns whether the user or OS has requested this window to close.
     *
     * @return {@code true} if the close flag is set
     */
    public boolean shouldClose() {
        return glfwWindowShouldClose(longAddress);
    }

    /**
     * Processes pending window and input events.
     * Must be called regularly from the main loop or the window will freeze.
     */
    public void pollEvents() {
        glfwPollEvents();
    }

    /**
     * Presents the back buffer and waits for vsync.
     * Call after drawing a frame.
     */
    public void swapBuffers() {
        glfwSwapBuffers(longAddress);
    }

    /**
     * Returns the client-area size as {@code (width, height)}.
     *
     * @return a new vector; {@code x} is width and {@code y} is height
     */
    public Vector2i getWindowSize() {
        return new Vector2i(width, height);
    }

    /**
     * Returns the native GLFW window handle.
     *
     * @return the {@code GLFWwindow *} pointer as a {@code long}
     */
    public long getlongAddress() {
        return longAddress;
    }

    /**
     * Registers a callback invoked when the user attempts to close the window
     * (for example by clicking the title-bar close button).
     *
     * @param func GLFW close callback; may be {@code null} to remove the current
     *        callback
     */
    public void setWindowCloseCallback(GLFWWindowCloseCallbackI func) {
        glfwSetWindowCloseCallback(longAddress, func);
    }

    /**
     * Destroys the native window and shuts down GLFW.
     * After this call the instance must not be used.
     *
     * @throws IOException unused; declared by {@link Closeable}
     */
    @Override
    public void close() throws IOException {
        glfwDestroyWindow(longAddress);
        glfwTerminate();
    }
    
}
