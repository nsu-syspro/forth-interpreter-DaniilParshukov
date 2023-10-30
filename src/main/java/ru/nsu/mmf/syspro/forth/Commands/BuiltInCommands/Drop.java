package ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Drop implements Command {
    @Override
    public boolean apply(Context ctx){
        if (ctx.S.empty()){
            ctx.printer.print(" Error: pop from empty stack");
            return true;
        }
        ctx.S.pop();
        return false;
    }
}