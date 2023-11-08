package ru.nsu.mmf.syspro.forth.Commands;

import ru.nsu.mmf.syspro.forth.Context;

public class Push implements Command {
    private final int num;

    @Override
    public void apply(Context ctx) {
        ctx.S.push(num);
    }

    public Push(int num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Push) {
            return num == ((Push) obj).num;
        }
        return false;
    }
}
