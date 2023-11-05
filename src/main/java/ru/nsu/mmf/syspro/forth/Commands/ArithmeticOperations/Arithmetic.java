package ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Arithmetic implements Command {
    public boolean apply(Context ctx) {
        int b = ctx.S.pop();
        int a = ctx.S.pop();
        return сalculate(ctx, a, b);
    }

    public boolean сalculate(Context ctx, int a, int b){
        return false;
    }
}
