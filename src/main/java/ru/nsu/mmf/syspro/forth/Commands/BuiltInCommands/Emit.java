package ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.STATUS;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Emit implements Command {
    @Override
    public boolean apply(Context ctx) {
        int n = ctx.S.pop();
        if (n < 32 | n > 126) {
            ctx.printer.print(" Error: no ascii symbol");
            ctx.S.push(n);
            return true;
        }
        char c = (char) n;
        ctx.printer.print(" " + c);
        ctx.status = STATUS.DEFAULT;
        return false;
    }
}
