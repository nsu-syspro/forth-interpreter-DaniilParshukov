package ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Rot implements Command {
    @Override
    public boolean apply(Context ctx){
        if (ctx.S.size() < 3){
            ctx.printer.print(" Error: pop from empty stack");
            return true;
        }
        int a = ctx.S.pop();
        int b = ctx.S.pop();
        int c = ctx.S.pop();
        ctx.S.push(a);
        ctx.S.push(c);
        ctx.S.push(b);
        return false;
    }
}
