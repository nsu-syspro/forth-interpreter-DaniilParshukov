package ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Print implements Command {
    @Override
    public void apply(Context ctx){
        if (ctx.S.empty()){
            System.out.print(" Error: pop from empty stack");
            ctx.error = true;
            return;
        }
        System.out.printf(" %d", ctx.S.pop());
        ctx.ok = false;
    }
}
