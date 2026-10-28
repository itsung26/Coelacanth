package io.github.itsung26.coelacanth.demo;

import static org.lwjgl.system.MemoryUtil.NULL;

import io.github.itsung26.coelacanth.presentation.Window;

public class Main {
    public static void main(String[] args) {
        Window root = new Window(500, 500, "Root");

        while (!root.shouldClose()) {
            root.pollEvents();
            root.swapBuffers();
        }
    }
}
