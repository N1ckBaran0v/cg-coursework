package com.github.N1ckBaran0v.program.artist;

import com.github.N1ckBaran0v.program.geometry.Matrix4D;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractImage;

class Frustum {
    private final Matrix4D transformMatrix = new Matrix4D();

    public Frustum(AbstractImage image, double focus, double visibility) {
        var near = focus;
        var far = focus + visibility;
        var width = image.getWidth();
        var height = image.getHeight();
        var left =  -width / 2.0;
        var right = left + width - 1;
        var top = height / 2.0;
        var bottom = top - height + 1;
        var rl = right - left;
        var tb = top - bottom;
        var tn = 2 * near;
        var fn = -visibility;
        transformMatrix.xx = tn / rl;
        transformMatrix.xz = (right + left) / rl;
        transformMatrix.yy = tn / tb;
        transformMatrix.yz = (top + bottom) / tb;
        transformMatrix.zz = (far + near) / fn;
        transformMatrix.zw = 2 * far * near / fn;
        transformMatrix.wz = -1;
        transformMatrix.ww = 0;
    }

    public Matrix4D getTransformMatrix() {
        return transformMatrix;
    }
}
