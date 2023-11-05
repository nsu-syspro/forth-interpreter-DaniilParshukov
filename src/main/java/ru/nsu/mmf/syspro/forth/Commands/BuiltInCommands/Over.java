package ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Over implements Command {
    @Override
    public void apply(Context ctx) {
        int a = ctx.S.pop();
        int b = ctx.S.peek();
        ctx.S.push(a);
        ctx.S.push(b);
    }
}
