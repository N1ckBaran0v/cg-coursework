package com.github.N1ckBaran0v.program;

import com.github.N1ckBaran0v.program.guiAdapters.AbstractDrawFactory;
import com.github.N1ckBaran0v.program.guiAdapters.DrawFactoryModule;

public class Facade {
    private static ContextHolder contextHolder;

    private Facade() {
    }

    public static void execute(Command command) {
        command.setContext(contextHolder.getContext());
        command.execute();
    }

    public static void setDrawFactory(AbstractDrawFactory drawFactory) {
        DrawFactoryModule.setAbstractDrawFactory(drawFactory);
        contextHolder = DaggerContextHolder.create();
    }
}
