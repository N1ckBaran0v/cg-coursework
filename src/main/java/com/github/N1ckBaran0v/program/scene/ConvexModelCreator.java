package com.github.N1ckBaran0v.program.scene;

class ConvexModelCreator implements SceneObjectCreator {
    private final ConvexModelBuilder builder = new ConvexModelBuilder();

    @Override
    public SceneObject create() {
        return new ConvexObject(builder.createCenter(), builder.createPolygons(), builder.createDots());
    }
}
