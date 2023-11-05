package ru.nsu.mmf.syspro.forth;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import junit.framework.TestCase;
import ru.nsu.mmf.syspro.forth.Commands.Command;
import ru.nsu.mmf.syspro.forth.Commands.Push;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Plus;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Drop;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Dup;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Over;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Rot;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Swap;

public class BuiltlnTests {
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
    public void drop() {
        Command[] cmds = { new Push(1), new Drop() };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 0);
    }

    @Test
    public void drop2() {
        try {
            Command[] cmds = { new Drop() };
            stackAfterInterpret(cmds);
            TestCase.fail();
        } catch (Exception e) {
            TestCase.assertEquals(e.toString(), "java.util.EmptyStackException");
        }
    }

    @Test
    public void dup() {
        Command[] cmds = { new Push(1), new Dup() };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 2);
        TestCase.assertEquals((int) s.pop(), 1);
        TestCase.assertEquals((int) s.pop(), 1);
    }

    @Test
    public void dup2() {
        try {
            Command[] cmds = { new Dup() };
            stackAfterInterpret(cmds);
            TestCase.fail();
        } catch (Exception e) {
            TestCase.assertEquals(e.toString(), "java.util.EmptyStackException");
        }
    }

    @Test
    public void over() {
        Command[] cmds = { new Push(1), new Push(2), new Over() };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 3);
        TestCase.assertEquals((int) s.pop(), 1);
        TestCase.assertEquals((int) s.pop(), 2);
        TestCase.assertEquals((int) s.pop(), 1);
    }

    @Test
    public void over2() {
        try {
            Command[] cmds = { new Push(1), new Plus() };
            stackAfterInterpret(cmds);
            TestCase.fail();
        } catch (Exception e) {
            TestCase.assertEquals(e.toString(), "java.util.EmptyStackException");
        }
    }

    @Test
    public void rot() {
        Command[] cmds = { new Push(1), new Push(2), new Push(3), new Rot() };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 3);
        TestCase.assertEquals((int) s.pop(), 2);
        TestCase.assertEquals((int) s.pop(), 1);
        TestCase.assertEquals((int) s.pop(), 3);
    }

    @Test
    public void rot2() {
        try {
            Command[] cmds = { new Push(1), new Push(2), new Rot() };
            stackAfterInterpret(cmds);
            TestCase.fail();
        } catch (Exception e) {
            TestCase.assertEquals(e.toString(), "java.util.EmptyStackException");
        }
    }

    @Test
    public void swap() {
        Command[] cmds = { new Push(1), new Push(2), new Swap() };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 2);
        TestCase.assertEquals((int) s.pop(), 1);
        TestCase.assertEquals((int) s.pop(), 2);
    }

    @Test
    public void swap2() {
        try {
            Command[] cmds = { new Push(1), new Swap() };
            stackAfterInterpret(cmds);
            TestCase.fail();
        } catch (Exception e) {
            TestCase.assertEquals(e.toString(), "java.util.EmptyStackException");
        }
    }
}
