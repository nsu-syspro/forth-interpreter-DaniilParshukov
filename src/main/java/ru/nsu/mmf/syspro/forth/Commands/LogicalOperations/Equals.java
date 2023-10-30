package ru.nsu.mmf.syspro.forth.Commands.LogicalOperations;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Equals implements Command {
    @Override
    public boolean apply(Context ctx){
        if (ctx.S.size() < 2){
            ctx.printer.print(" Error: pop from empty stack");
            return true;
        }
        int b = ctx.S.pop();
        int a = ctx.S.pop();
        if (a == b){
            ctx.S.push(1);
        }else{
            ctx.S.push(0);
        }
        return false;
    }
}
