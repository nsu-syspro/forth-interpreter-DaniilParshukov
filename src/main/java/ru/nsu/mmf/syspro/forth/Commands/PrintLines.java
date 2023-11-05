package ru.nsu.mmf.syspro.forth.Commands;

import java.util.List;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.STATUS;

public class PrintLines implements Command {
    private final List<String> str;

    @Override
    public void apply(Context ctx) {
        for (String s : str) {
            ctx.printer.print(" ");
            ctx.printer.print(s);
        }
        ctx.status = STATUS.DEFAULT;
    }

    public PrintLines(List<String> str) {
        this.str = str;
    }
}
