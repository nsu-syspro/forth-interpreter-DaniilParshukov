package ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Rot implements Command {
    @Override
    public void apply(Context ctx) {
        int a = ctx.S.pop();
        int b = ctx.S.pop();
        int c = ctx.S.pop();
        ctx.S.push(a);
        ctx.S.push(c);
        ctx.S.push(b);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rot)) {
            return false;
        }
        return true;
    }
}
