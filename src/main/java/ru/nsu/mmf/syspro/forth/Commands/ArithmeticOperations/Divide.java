package ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations;

import ru.nsu.mmf.syspro.forth.Context;

public class Divide extends Arithmetic {
    @Override
    public boolean сalculate(Context ctx, int a, int b){
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
