package ru.nsu.mmf.syspro.forth.Commands;

import ru.nsu.mmf.syspro.forth.Context;

public interface Command {
    boolean apply(Context ctx);
}
