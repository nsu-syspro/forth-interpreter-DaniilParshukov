package ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Cr implements Command {
    @Override
    public boolean apply(Context ctx) {
        ctx.printer.print("\n");
        ctx.ok = false;
        return false;
    }
}
