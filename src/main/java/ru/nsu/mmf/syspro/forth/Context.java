package ru.nsu.mmf.syspro.forth;

import java.util.Stack;

public class Context {
    public final Stack<Integer> S;
    public boolean error;
    public boolean ok;
    public boolean exit;


    public Context(){
        this.S = new Stack<Integer>();
        this.exit = false;
    }
}
