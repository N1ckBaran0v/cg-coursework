package com.github.N1ckBaran0v.program.scene;

class SphereCreator implements SceneObjectCreator {
    private final SphereBuilder builder = new SphereBuilder();

    @Override
    public SceneObject create() {
        return new ConvexObject(builder.createCenter(), builder.createPolygons(), builder.createDots());
    }
}
