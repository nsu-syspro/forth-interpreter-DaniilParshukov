package ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Divide implements Command {
    @Override
    public boolean apply(Context ctx){
        if (ctx.S.size() < 2){
            ctx.printer.print(" Error: pop from empty stack");
            return true;
        }
        int b = ctx.S.pop();
        int a = ctx.S.pop();
        if (b == 0){
            ctx.printer.print(" Error: division by zero");
            ctx.S.push(a);
            ctx.S.push(b);
            return true;
        }
        ctx.S.push(a / b);
        return false;
    }
}
