package ru.nsu.mmf.syspro.forth;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import junit.framework.TestCase;
import ru.nsu.mmf.syspro.forth.Commands.Command;
import ru.nsu.mmf.syspro.forth.Commands.Push;
import ru.nsu.mmf.syspro.forth.Commands.LogicalOperations.Equals;
import ru.nsu.mmf.syspro.forth.Commands.LogicalOperations.Less;
import ru.nsu.mmf.syspro.forth.Commands.LogicalOperations.More;

public class LogicalTests {
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
    public void equals1() {
        Command[] cmds = { new Push(1), new Push(1), new Equals() };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 1);
        TestCase.assertEquals((int) s.pop(), 1);
    }

    @Test
    public void equals2() {
        Command[] cmds = { new Push(1), new Push(2), new Equals() };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 1);
        TestCase.assertEquals((int) s.pop(), 0);
    }

    @Test(expected = EmptyStackException.class)
    public void equals3() {
        Command[] cmds = { new Push(2), new Equals() };
        stackAfterInterpret(cmds);
    }

    @Test
    public void less1() {
        Command[] cmds = { new Push(1), new Push(2), new Less() };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 1);
        TestCase.assertEquals((int) s.pop(), 1);
    }

    @Test
    public void less2() {
        Command[] cmds = { new Push(1), new Push(1), new Less() };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 1);
        TestCase.assertEquals((int) s.pop(), 0);
    }

    @Test(expected = EmptyStackException.class)
    public void less3() {
        Command[] cmds = { new Push(1), new Less() };
        stackAfterInterpret(cmds);
    }

    @Test
    public void more1() {
        Command[] cmds = { new Push(3), new Push(2), new More() };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 1);
        TestCase.assertEquals((int) s.pop(), 1);
    }

    @Test
    public void more2() {
        Command[] cmds = { new Push(1), new Push(2), new More() };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 1);
        TestCase.assertEquals((int) s.pop(), 0);
    }

    @Test(expected = EmptyStackException.class)
    public void more3() {

        Command[] cmds = { new Push(2), new More() };
        stackAfterInterpret(cmds);
    }
}
