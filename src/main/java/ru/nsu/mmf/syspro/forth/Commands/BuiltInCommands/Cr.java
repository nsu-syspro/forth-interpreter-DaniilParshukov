package ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.STATUS;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Cr implements Command {
    @Override
    public void apply(Context ctx) {
        ctx.printer.print("\n");
        ctx.status = STATUS.DEFAULT;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cr)) {
            return false;
        }
        return true;
    }
}
