package ru.nsu.mmf.syspro.forth.Commands;

import ru.nsu.mmf.syspro.forth.Context;

public class Push implements Command {
    int num;
    @Override
    public boolean apply(Context ctx){
        ctx.S.push(num);
        return false;
    }

    public Push(int num){
        this.num = num;
    }
}
