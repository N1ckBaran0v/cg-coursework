package com.github.N1ckBaran0v.program;

abstract public class Command {
    protected Context context;

    abstract void execute();

    void setContext(Context context) {
        this.context = context;
    }
}
