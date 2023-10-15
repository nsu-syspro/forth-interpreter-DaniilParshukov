package ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Over implements Command {
    @Override
    public void apply(Context ctx){
        if (ctx.S.size() < 2){
            ctx.printer.print(" Error: pop from empty stack");
            ctx.error = true;
            return;
        }
        int a = ctx.S.pop();
        int b = ctx.S.peek();
        ctx.S.push(a);
        ctx.S.push(b);
    }
}
