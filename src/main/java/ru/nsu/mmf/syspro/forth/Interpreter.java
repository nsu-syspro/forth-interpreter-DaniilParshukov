package ru.nsu.mmf.syspro.forth;

import java.util.List;

import ru.nsu.mmf.syspro.forth.Commands.Command;

public class Interpreter {
    private Context ctx;

    public Interpreter(Context ctx){
        this.ctx = ctx;
    }

    public void interpret(List<Command> commands){
        ctx.ok = true;
        ctx.error = false;
        if(commands == null){
            ctx.printer.print("\n");
            return;
        }
        for(int i = 0; i < commands.size(); i++){
            commands.get(i).apply(ctx);
            if(ctx.error){
                break;
            }
        }
        if(ctx.ok & !ctx.error){
            ctx.printer.print(" ok\n");
        }else{
            ctx.printer.print("\n");
        }
    }
}
