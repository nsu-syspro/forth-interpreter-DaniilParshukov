package ru.nsu.mmf.syspro.forth;

import java.util.List;

import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Interpreter {
    private Context ctx;

    public Interpreter(Context ctx) {
        this.ctx = ctx;
    }

    public void interpret(List<Command> commands) {
        ctx.ok = true;
        if (commands == null) {
            ctx.printer.print("\n");
            return;
        }
        for (Command command : commands) {
            if (command.apply(ctx)) {
                ctx.ok = false;
                break;
            }
        }
        if (ctx.ok) {
            ctx.printer.print(" ok\n");
        } else {
            ctx.printer.print("\n");
        }
    }
}
