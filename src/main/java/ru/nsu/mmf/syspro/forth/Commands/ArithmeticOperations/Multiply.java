package ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations;

import ru.nsu.mmf.syspro.forth.Context;

public class Multiply extends Arithmetic {
    @Override
    public boolean сalculate(Context ctx, int a, int b){
        ctx.S.push(a * b);
        return false;
    }
}
