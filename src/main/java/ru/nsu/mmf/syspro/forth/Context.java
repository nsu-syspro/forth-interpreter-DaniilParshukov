package ru.nsu.mmf.syspro.forth;

import java.util.Stack;

public class Context {
    public final Printer printer;
    public final Stack<Integer> S;
    public boolean ok;
    public boolean exit;

    public Context(Printer printer) {
        this.printer = printer;
        this.S = new Stack<Integer>();
        this.exit = false;
    }
}
