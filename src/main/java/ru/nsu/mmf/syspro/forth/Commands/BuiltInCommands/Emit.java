package ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Emit implements Command {
    @Override
    public void apply(Context ctx){
        if (ctx.S.empty()){
            ctx.printer.print(" Error: pop from empty stack");
            ctx.error = true;
            return;
        }
        int n = ctx.S.pop();
        if (n < 32 | n > 126){
            ctx.printer.print(" Error: no ascii symbol");
            ctx.S.push(n);
            ctx.error = true;
            return;
        }
        char c = (char) n;
        ctx.printer.print(" " + c);
        ctx.ok = false;
    }
}
