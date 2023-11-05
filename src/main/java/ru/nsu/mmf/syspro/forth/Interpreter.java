package ru.nsu.mmf.syspro.forth;

import java.util.List;

import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Interpreter {
    private Context ctx;

    public Interpreter(Context ctx) {
        this.ctx = ctx;
    }

    public void interpret(List<Command> commands) {
        ctx.status = STATUS.OK;
        if (commands == null) {
            ctx.printer.print("\n");
            return;
        }
        for (Command command : commands) {
            command.apply(ctx);
        }
        if (ctx.status == STATUS.OK) {
            ctx.printer.print(" ok\n");
        } else {
            ctx.printer.print("\n");
        }
    }
}
