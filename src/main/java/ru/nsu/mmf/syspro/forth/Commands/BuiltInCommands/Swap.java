package ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Swap implements Command {
    @Override
    public void apply(Context ctx) {
        int a = ctx.S.pop();
        int b = ctx.S.pop();
        ctx.S.push(a);
        ctx.S.push(b);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Swap)) {
            return false;
        }
        return true;
    }
}
