package com.github.N1ckBaran0v.program.render;

import com.github.N1ckBaran0v.program.geometry.Matrix4D;
import com.github.N1ckBaran0v.program.guiAdapters.AbstractImage;

class Frustum {
    private final Matrix4D transformMatrix = new Matrix4D();

    public Frustum(AbstractImage image, double focus, double visibility) {
        var near = -focus;
        var far = focus + visibility;
        var width = image.getWidth();
        var height = image.getHeight();
        transformMatrix.xx = near;
        transformMatrix.xz = width / 2.0;
        transformMatrix.yy = near;
        transformMatrix.yz = height / 2.0;;
        transformMatrix.zz = far / visibility;
        transformMatrix.zw = transformMatrix.zz * near;
        transformMatrix.wz = 1;
        transformMatrix.ww = 0;
    }

    public Matrix4D getTransformMatrix() {
        return transformMatrix;
    }
}
