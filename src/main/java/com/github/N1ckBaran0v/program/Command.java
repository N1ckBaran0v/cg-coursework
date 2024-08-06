package com.github.N1ckBaran0v.program;

abstract public class Command {
    protected Context context;

    public void execute() {
        if (context == null) {
            throw new RuntimeException("Context not defined");
        }
    }

    void setContext(Context context) {
        this.context = context;
    }
}
