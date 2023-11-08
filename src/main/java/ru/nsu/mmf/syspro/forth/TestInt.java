package ru.nsu.mmf.syspro.forth;

import java.util.ArrayList;
import java.util.List;

import ru.nsu.mmf.syspro.forth.Commands.Command;

public class TestInt {
    private static class TestPrinter implements Printer {
        @Override
        public void print(String line) {}
        public TestPrinter() {}
    }

    public static boolean testInterpret(List<Command> cmds, int[] arr) {
        Context ctx = new Context(new TestPrinter());
        Interpreter interpreter = new Interpreter(ctx);
        List<Command> list = new ArrayList<>();
        for (Command cmd : cmds) {
            list.add(cmd);
        }
        interpreter.interpret(list);
        if (ctx.S.size() != arr.length){
            return false;
        }
        for (int a : arr) {
            if (((int) ctx.S.pop()) != a){
                return false;
            }
        }
        return true;
    }
}
