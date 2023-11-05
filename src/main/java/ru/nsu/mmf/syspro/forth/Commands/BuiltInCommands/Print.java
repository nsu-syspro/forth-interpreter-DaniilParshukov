package ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.STATUS;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Print implements Command {
    @Override
    public boolean apply(Context ctx) {
        ctx.printer.print(" " + ctx.S.pop().toString());
        ctx.status = STATUS.DEFAULT;
        return false;
    }
}
