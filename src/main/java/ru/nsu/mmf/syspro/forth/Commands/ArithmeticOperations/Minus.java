package ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Minus implements Command {
    @Override
    public void apply(Context ctx){
        if (ctx.S.size() < 2){
            ctx.printer.print(" Error: pop from empty stack");
            ctx.error = true;
            return;
        }
        int b = ctx.S.pop();
        int a = ctx.S.pop();
        ctx.S.push(a - b);
    }
}
