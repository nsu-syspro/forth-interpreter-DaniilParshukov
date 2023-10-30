package ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Modulo implements Command {
    @Override
    public boolean apply(Context ctx){
        return Arithmetic.сalculate(ctx, '%');
    }
}
