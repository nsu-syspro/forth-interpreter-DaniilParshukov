package ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Print implements Command {
    @Override
    public void apply(Context ctx){
        if (ctx.S.empty()){
            ctx.printer.print(" Error: pop from empty stack");
            ctx.error = true;
            return;
        }
        ctx.printer.print(" " + ctx.S.pop().toString());
        ctx.ok = false;
    }
}
