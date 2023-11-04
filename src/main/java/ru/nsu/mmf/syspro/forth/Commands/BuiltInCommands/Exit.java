package ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Exit implements Command {
    @Override
    public boolean apply(Context ctx) {
        ctx.exit = true;
        return true;
    }
}
