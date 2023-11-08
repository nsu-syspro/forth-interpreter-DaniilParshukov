package ru.nsu.mmf.syspro.forth.Commands.AdvancedCommands;

import java.util.List;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class If implements Command {
    private final List<Command> commands;

    @Override
    public void apply(Context ctx) {
        if (ctx.S.pop() != 0) {
            for (Command cmd : commands) {
                cmd.apply(ctx);
            }
        }
    }

    public If(List<Command> commands) {
        this.commands = commands;
    }
}
