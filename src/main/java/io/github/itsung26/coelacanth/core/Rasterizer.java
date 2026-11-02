package io.github.itsung26.coelacanth.core;

import io.github.itsung26.coelacanth.math.Vector3;

public class Rasterizer {
    // Simple triange in NDC.
    private Vector3[] triangle = {
        new Vector3(-0.5f, -0.5f, 0.0f),
        new Vector3(0.5f, -0.5f, 0.0f),
        new Vector3(0.0f, 0.5f, 0.0f)
    };  

    /**
     * Rasterizes a simple triangle into colorBuffer.
     * @param colorBuffer
     *      The buffer to write the pixels into.
     */
    public static void drawSingleTriangle(int[] colorBuffer) {
        
    }
}
