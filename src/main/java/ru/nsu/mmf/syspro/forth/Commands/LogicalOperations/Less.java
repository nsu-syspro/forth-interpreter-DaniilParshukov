package ru.nsu.mmf.syspro.forth.Commands.LogicalOperations;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Less implements Command {
    @Override
    public void apply(Context ctx) {
        int b = ctx.S.pop();
        int a = ctx.S.pop();
        if (a < b) {
            ctx.S.push(1);
        } else {
            ctx.S.push(0);
        }
    }
}
