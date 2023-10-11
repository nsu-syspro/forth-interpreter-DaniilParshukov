package ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Swap implements Command {
    @Override
    public void apply(Context ctx){
        if (ctx.S.size() < 2){
            System.out.print(" Error: pop from empty stack");
            ctx.error = true;
            return;
        }
        int a = ctx.S.pop();
        int b = ctx.S.pop();
        ctx.S.push(b);
        ctx.S.push(a);
    }
}
