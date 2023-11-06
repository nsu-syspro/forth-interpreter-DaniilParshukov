package ru.nsu.mmf.syspro.forth;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import junit.framework.TestCase;
import ru.nsu.mmf.syspro.forth.Commands.Command;
import ru.nsu.mmf.syspro.forth.Commands.Push;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Divide;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Minus;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Modulo;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Multiply;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Plus;

public class ArithmeticTests {
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
    public void plus1() {
        Command[] cmds = { new Push(1), new Push(2), new Plus() };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 1);
        TestCase.assertEquals((int) s.pop(), 3);
    }

    @Test(expected = EmptyStackException.class)
    public void plus2() {
        Command[] cmds = { new Push(1), new Plus() };
        stackAfterInterpret(cmds);
    }

    @Test
    public void divide() {
        Command[] cmds = { new Push(8), new Push(2), new Divide() };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 1);
        TestCase.assertEquals((int) s.pop(), 4);
    }

    @Test(expected = ArithmeticException.class)
    public void divideZero() {
        Command[] cmds = { new Push(8), new Push(0), new Divide() };
        stackAfterInterpret(cmds);
    }

    @Test
    public void divide2() {
        Command[] cmds = { new Push(8), new Push(3), new Divide() };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 1);
        TestCase.assertEquals((int) s.pop(), 2);
    }

    @Test
    public void divide3() {
        Command[] cmds = { new Push(0), new Push(142), new Divide() };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 1);
        TestCase.assertEquals((int) s.pop(), 0);
    }

    @Test
    public void minus1() {
        Command[] cmds = { new Push(3), new Push(5), new Minus() };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 1);
        TestCase.assertEquals((int) s.pop(), -2);
    }

    @Test(expected = EmptyStackException.class)
    public void minus2() {
        Command[] cmds = { new Push(1), new Divide() };
        stackAfterInterpret(cmds);
    }

    @Test
    public void modulo1() {
        Command[] cmds = { new Push(8), new Push(3), new Modulo() };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 1);
        TestCase.assertEquals((int) s.pop(), 2);
    }

    @Test(expected = ArithmeticException.class)
    public void moduloZero() {
        Command[] cmds = { new Push(1), new Push(0), new Divide() };
        stackAfterInterpret(cmds);
    }

    @Test(expected = EmptyStackException.class)
    public void modulo2() {
        Command[] cmds = { new Push(1), new Plus() };
        stackAfterInterpret(cmds);
    }

    @Test
    public void multiply1() {
        Command[] cmds = { new Push(8), new Push(3), new Multiply() };
        Stack<Integer> s = stackAfterInterpret(cmds);
        TestCase.assertEquals(s.size(), 1);
        TestCase.assertEquals((int) s.pop(), 24);
    }

    @Test(expected = EmptyStackException.class)
    public void multiply2() {
        Command[] cmds = { new Push(1), new Multiply() };
        stackAfterInterpret(cmds);
    }
}