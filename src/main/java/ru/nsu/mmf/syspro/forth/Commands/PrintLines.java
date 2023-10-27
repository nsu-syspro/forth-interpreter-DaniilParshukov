package ru.nsu.mmf.syspro.forth.Commands;

import java.util.List;

import ru.nsu.mmf.syspro.forth.Context;

public class PrintLines implements Command {
    List<String> str;

    @Override
    public void apply(Context ctx){
        for(String s:str){
            ctx.printer.print(" ");
            ctx.printer.print(s);
        }
        ctx.ok = false;
    }

    public PrintLines(List<String> str){
        this.str = str;
    }
}