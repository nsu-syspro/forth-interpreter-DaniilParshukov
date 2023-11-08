package ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

abstract public class Arithmetic implements Command {
    @Override
    public void apply(Context ctx) {
        int b = ctx.S.pop();
        int a = ctx.S.pop();
        ctx.S.push(сalculate(a, b));
    }

    abstract public int сalculate(int a, int b);
}
