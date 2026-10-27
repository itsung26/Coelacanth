# Coelacanth
Coelacanth is a CPU-based realtime rendering engine written in java.

## Coordinate spaces

Screen space (`Vector2`) has its origin at the top-left of the framebuffer. `x+` is right and `y+` is down.

```
      x+
 O ------->
 |
 |
 v
 y+
```

World space (`Vector3`) uses `y+` up and `z-` forward. `x+` is right; `z+` points out of the page.

```
      y+
      |
      |
      O -------> x+
     /
    /
   z+
```
