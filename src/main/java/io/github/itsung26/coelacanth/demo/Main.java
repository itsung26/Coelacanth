package io.github.itsung26.coelacanth.demo;

import static org.lwjgl.system.MemoryUtil.NULL;

import java.io.IOException;

import io.github.itsung26.coelacanth.presentation.Window;

public class Main {
    public static void main(String[] args) {

        try (Window root = new Window(500, 500, "Root")) {
            while (!root.shouldClose()) {
                root.pollEvents();

                // TODO: Issue draw call.

                root.swapBuffers();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
