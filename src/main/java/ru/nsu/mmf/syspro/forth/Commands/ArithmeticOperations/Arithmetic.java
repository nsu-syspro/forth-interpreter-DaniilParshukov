package ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Arithmetic implements Command {
    @Override
    public void apply(Context ctx) {
        int b = ctx.S.pop();
        int a = ctx.S.pop();
        ctx.S.push(сalculate(a, b));
    }

    public int сalculate(int a, int b){
        return 0;
    }
}
