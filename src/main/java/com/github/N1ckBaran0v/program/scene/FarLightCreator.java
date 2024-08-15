package com.github.N1ckBaran0v.program.scene;

class FarLightCreator implements SceneObjectCreator{
    @Override
    public SceneObject create() {
        return new FarLight();
    }
}
