package com.github.N1ckBaran0v.program.scene;

import java.util.ArrayList;
import java.util.List;

class LandscapeCheckerVisitor implements SceneObjectVisitor {
    private boolean needRegenerate;
    private final Landscape landscape;
    private final List<Double> listX = new ArrayList<>(), listZ = new ArrayList<>();

    public LandscapeCheckerVisitor(Landscape landscape) {
        this.landscape = landscape;
    }

    @Override
    public void visit(Camera camera) {
        if (landscape.getChunks() == null) {
            return;
        }
        var position = camera.getCenter();
        listX.add(position.x);
        listZ.add(position.z);
        var x = (long) (position.x / landscape.getSideSize());
        var z = (long) (position.z / landscape.getSideSize());
        var map = landscape.getChunks();
        cycle: {
            var maxChunks = landscape.getMaxChunks();
            for (var i = x - maxChunks; i <= x + maxChunks; ++i) {
                for (var j = z - maxChunks; j <= z + maxChunks; ++j) {
                    if (!map.contains(i, j)) {
                        needRegenerate = true;
                        break cycle;
                    }
                }
            }
        }
    }

    public boolean isNeedRegenerate() {
        return needRegenerate;
    }

    public List<Double> getListX() {
        return listX;
    }

    public List<Double> getListZ() {
        return listZ;
    }
}
