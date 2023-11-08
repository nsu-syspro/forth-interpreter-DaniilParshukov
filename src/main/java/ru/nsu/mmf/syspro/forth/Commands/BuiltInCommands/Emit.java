package ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.STATUS;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Emit implements Command {
    @Override
    public void apply(Context ctx) {
        int n = ctx.S.pop();
        if (n < 32 | n > 126) {
            throw new IllegalArgumentException("no ascii symbol");
        }
        char c = (char) n;
        ctx.printer.print(" " + c);
        ctx.status = STATUS.DEFAULT;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Emit)) {
            return false;
        }
        return true;
    }
}
