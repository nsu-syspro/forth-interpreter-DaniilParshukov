package ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Modulo implements Command {
    @Override
    public void apply(Context ctx){
        if (ctx.S.size() < 2){
            ctx.printer.print(" Error: pop from empty stack");
            ctx.error = true;
            return;
        }
        int b = ctx.S.pop();
        int a = ctx.S.pop();
        if (b == 0){
            ctx.printer.print(" Error: division by zero");
            ctx.S.push(a);
            ctx.S.push(b);
            ctx.error = true;
            return;
        }
        ctx.S.push(a % b);
    }
}
