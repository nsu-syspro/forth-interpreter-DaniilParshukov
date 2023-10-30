package ru.nsu.mmf.syspro.forth.Commands.AdvancedСommands;

import java.util.List;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class If implements Command {
    List<Command> commands;
    @Override
    public void apply(Context ctx){
        if (ctx.S.size() < 1){
            System.out.print(" Error: pop from empty stack");
            ctx.error = true;
            return;
        }
        if (ctx.S.pop() != 0){
            for(Command cmd:commands){
                cmd.apply(ctx);
                if (ctx.error){
                    break;
                }
            }
        }
    }

    public If(List<Command> commands){
        this.commands = commands;
    }
}
