package ru.nsu.mmf.syspro.forth.Commands.LogicalOperations;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class More implements Command {
    @Override
    public void apply(Context ctx){
        if (ctx.S.size() < 2){
            System.out.print(" Error: pop from empty stack");
            ctx.error = true;
            return;
        }
        int b = ctx.S.pop();
        int a = ctx.S.pop();
        if (a > b){
            ctx.S.push(1);
        }else{
            ctx.S.push(0);
        }
    }
}
