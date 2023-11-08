package ru.nsu.mmf.syspro.forth;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import junit.framework.TestCase;
import ru.nsu.mmf.syspro.forth.Commands.Command;
import ru.nsu.mmf.syspro.forth.Commands.Push;
import ru.nsu.mmf.syspro.forth.Commands.AdvancedCommands.If;

public class AdvancedTests {
    private static class TestPrinter implements Printer {
        StringBuilder sb;

        @Override
        public void print(String line) {
            sb.append(line);
        }

        public TestPrinter(StringBuilder sb) {
            this.sb = sb;
        }
    }

    private Stack<Integer> stackAfterInterpret(Command[] cmds) {
        Context ctx = new Context(new TestPrinter(new StringBuilder()));
        Interpreter interpreter = new Interpreter(ctx);
        List<Command> list = new ArrayList<>();
        for (Command cmd : cmds) {
            list.add(cmd);
        }
        interpreter.interpret(list);
        return ctx.S;
    }

    @Test
    public void if1() {
        List<Command> list = new ArrayList<>();
        list.add(new Push(2));
        Command[] cmds = { new Push(1), new If(list) };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 1);
        TestCase.assertEquals((int) s.pop(), 2);
    }

    @Test
    public void if2() {
        List<Command> list = new ArrayList<>();
        list.add(new Push(2));
        Command[] cmds = { new Push(0), new If(list) };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 0);
    }

    @Test
    public void if3() {
        List<Command> list = new ArrayList<>();
        list.add(new Push(2));
        list.add(new Push(3));
        list.add(new Push(4));
        Command[] cmds = { new Push(142), new If(list) };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 3);
        TestCase.assertEquals((int) s.pop(), 4);
        TestCase.assertEquals((int) s.pop(), 3);
        TestCase.assertEquals((int) s.pop(), 2);
    }

    @Test
    public void if4() {
        Command[] cmds = { new Push(1), new If(new ArrayList<>()) };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 0);
    }

    @Test(expected = EmptyStackException.class)
    public void if5() {
        Command[] cmds = { new If(new ArrayList<>()) };
        stackAfterInterpret(cmds);
    }
}