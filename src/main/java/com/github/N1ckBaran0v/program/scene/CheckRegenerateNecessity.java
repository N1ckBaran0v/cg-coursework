package com.github.N1ckBaran0v.program.scene;

import com.github.N1ckBaran0v.program.Command;
import com.github.N1ckBaran0v.program.Context;
import com.github.N1ckBaran0v.program.containers.HashMap2D;

class CheckRegenerateNecessity implements Command {
    @Override
    public void execute(Context context) {
        if (LandscapeHolder.getGenerator().isGenerates()) {
            return;
        }
        var scene = context.getScene();
        var landscape = LandscapeHolder.getInstance();
        var visitor = new LandscapeCheckerVisitor(landscape);
        for (var object : scene) {
            object.accept(visitor);
        }
        if (visitor.isNeedRegenerate()) {
            var thread = new RegenerateThread(LandscapeHolder.getGenerator(), visitor.getListX(), visitor.getListZ(),
                    landscape);
            LandscapeHolder.setThread(thread);
            thread.start();
        }
    }
}
