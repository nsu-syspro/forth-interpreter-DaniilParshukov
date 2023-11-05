package ru.nsu.mmf.syspro.forth.Commands.AdvancedСommands;

import java.util.List;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class If implements Command {
    private final List<Command> commands;

    @Override
    public boolean apply(Context ctx) {
        if (ctx.S.pop() != 0) {
            boolean error = false;
            for (Command cmd : commands) {
                error = cmd.apply(ctx);
                if (error) {
                    return true;
                }
            }
        }
        return false;
    }

    public If(List<Command> commands) {
        this.commands = commands;
    }
}
