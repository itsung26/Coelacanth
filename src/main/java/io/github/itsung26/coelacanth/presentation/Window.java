package io.github.itsung26.coelacanth.presentation;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.io.Closeable;
import java.io.IOException;

/**
 * Object-oriented wrap for GLFW's C-implemented window system.
 */
public class Window implements Closeable{
    private int height;
    private int width;
    private String title;
    private final long longAddress;

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

    public boolean shouldClose() {
        return glfwWindowShouldClose(longAddress);
    }

    public void pollEvents() {
        glfwPollEvents();
    }

    public void swapBuffers() {
        glfwSwapBuffers(longAddress);
    }

    public long getlongAddress() {
        return longAddress;
    }

    @Override
    public void close() throws IOException {
        glfwDestroyWindow(longAddress);
        glfwTerminate();
    }
    
}
