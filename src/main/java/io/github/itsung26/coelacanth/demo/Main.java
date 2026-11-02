package io.github.itsung26.coelacanth.demo;

import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.opengl.GL.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

import io.github.itsung26.coelacanth.core.Framebuffer;
import io.github.itsung26.coelacanth.presentation.Window;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {

        try (Window root = new Window(500, 500, "Root")) {

            createCapabilities();

            Framebuffer fb = new Framebuffer(root.getSize().x, root.getSize().y);

            int blitTex = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, blitTex);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            glPixelStorei(GL_UNPACK_ALIGNMENT, 4);
            // Allocate GPU memory for a new texture.
            glTexImage2D(
                GL_TEXTURE_2D, 0, GL_RGBA8,
                fb.getWidth(), fb.getHeight(), 0,
                GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV,
                (int[]) null
            );

            // Render loop.
            while (!root.shouldClose()) {
                root.pollEvents();

                // Wipe the canvas to black.
                glClearColor(0f, 0f, 0f, 1f);
                glClear(GL_COLOR_BUFFER_BIT);

                // TODO: Rasterize mesh data and write into Framebuffer.

                glBindTexture(GL_TEXTURE_2D, blitTex);
                glTexSubImage2D(
                    GL_TEXTURE_2D, 0, 0, 0,
                    fb.getWidth(), fb.getHeight(),
                    GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV,
                    fb.getColorBuffer() // actual pixels
                );
                                
                glEnable(GL_TEXTURE_2D);
                glBindTexture(GL_TEXTURE_2D, blitTex);

                // Draw a face quad to render to.
                glBegin(GL_QUADS);
                // NDC: (-1,-1) bottom-left, y+ up.
                // CPU row 0 is top of the screen but the bottom of a GL texture, so flip V.
                glTexCoord2f(0f, 0f); glVertex2f(-1f,  1f); // top-left
                glTexCoord2f(1f, 0f); glVertex2f( 1f,  1f); // top-right
                glTexCoord2f(1f, 1f); glVertex2f( 1f, -1f); // bottom-right
                glTexCoord2f(0f, 1f); glVertex2f(-1f, -1f); // bottom-left
                glEnd();

                glBindTexture(GL_TEXTURE_2D, 0);
                // Finally, show the back buffer containing the drawn texture.
                root.swapBuffers();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
