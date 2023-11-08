package ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Dup implements Command {
    @Override
    public void apply(Context ctx) {
        int a = ctx.S.peek();
        ctx.S.push(a);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Dup)) {
            return false;
        }
        return true;
    }
}
