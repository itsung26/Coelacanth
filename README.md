# Coelacanth

Coelacanth (SEE-luh-kanth) is a CPU-based realtime rendering engine written in java.

## Library Dependencies

- **LWJGL** (`org.lwjgl:lwjgl`) is the native binding runtime. It loads platform libraries and is required by the GLFW and OpenGL modules.
- **GLFW** (`org.lwjgl:lwjgl-glfw`) creates the OS window and handles input. Coelacanth uses it to display the rendered framebuffer; it does not rasterize or perform any rendering operations.
- **OpenGL** (`org.lwjgl:lwjgl-opengl`) presents that framebuffer to the window. The engine does all the rendering, OpenGL is used merely to display to a window.
- **JUnit 4** (`junit:junit`) is the primary unit testing tool that runs tests under `src/test/java`.

## Native bindings

LWJGL is not a Java windowing or rendering framework. It is a thin C-to-Java binding: GLFW and OpenGL stay native C APIs, and LWJGL exposes them with as little wrapping as possible.

That makes the Java side look like C. Functions are static and procedural (`GLFW.glfwCreateWindow(...)`, `GL11.glTexSubImage2D(...)`) rather than methods on objects. There is no `GLFWWindow` type; a window is an opaque `long`, which is the native `GLFWwindow *` pointer. You pass that handle into later calls (`glfwSwapBuffers(window)`, `glfwDestroyWindow(window)`). Lifetime is explicit: creating a handle does not allocate a Java object the GC will clean up, and dropping a `long` does not destroy the native resource.

Coelacanth keeps this style at the LWJGL boundary and wraps it in ordinary Java types (`Window`, `Framebuffer`) so the rest of the engine can stay object-oriented.

## Coordinate system conventions

### 2D

Screen space (`Vector2`) has its origin at the top-left of the framebuffer. `x+` is right and `y+` is down.

```
      x+
 O ------->
 |
 |
 v
 y+
```

### 3D

Coelacanth uses a right-handed coordinate system; World space (`Vector3`) uses `y+` up and `z-` forward. `x+` is right; `z+` points out of the page.

```
      y+
      |
      |
      O -------> x+
     /
    /
   z+
```
