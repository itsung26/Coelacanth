# Coelacanth

Coelacanth (SEE-luh-kanth) is a CPU-based realtime rendering engine written in java.

## Library Dependencies

- **LWJGL** (`org.lwjgl:lwjgl`) is the native binding runtime. It loads platform libraries and is required by the GLFW and OpenGL modules.
- **GLFW** (`org.lwjgl:lwjgl-glfw`) creates the OS window and handles input. Coelacanth uses it to display the rendered framebuffer; it does not rasterize or perform any rendering operations.
- **OpenGL** (`org.lwjgl:lwjgl-opengl`) presents that framebuffer to the window. Rasterization, depth, and shading stay on the CPU; OpenGL is only a blit path.
- **JUnit 4** (`junit:junit`) is the primary unit testing tool that runs tests under `src/test/java`.

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
