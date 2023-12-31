package ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.STATUS;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Exit implements Command {
    @Override
    public void apply(Context ctx) {
        ctx.status = STATUS.EXIT;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Exit)) {
            return false;
        }
        return true;
    }
}
