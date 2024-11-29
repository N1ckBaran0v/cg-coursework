package com.github.N1ckBaran0v.program;

import org.jetbrains.annotations.NotNull;

public interface Command {
    void execute(@NotNull Context context);
}
