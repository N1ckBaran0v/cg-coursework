package com.github.N1ckBaran0v.program.scene;

class CameraCreator implements SceneObjectCreator {
    @Override
    public SceneObject create() {
        return new Camera();
    }
}
