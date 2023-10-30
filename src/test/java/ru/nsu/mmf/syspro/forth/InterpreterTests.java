package ru.nsu.mmf.syspro.forth;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import ru.nsu.mmf.syspro.forth.Commands.Command;
import ru.nsu.mmf.syspro.forth.Commands.PrintLines;
import ru.nsu.mmf.syspro.forth.Commands.Push;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Plus;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Print;

public class InterpreterTests {

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

    private Object[] create() {
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        return new Object[] { interpreter, sb, };
    }

    @Test
    public void Interpreter1() {
        Object[] date = create();
        List<Command> commands = new ArrayList<>();
        commands.add(new Push(4));
        ((Interpreter) date[0]).interpret(commands);
        TestCase.assertEquals(" ok\n", date[1].toString());
    }

    @Test
    public void Interpreter2() {
        Object[] date = create();
        List<Command> commands = new ArrayList<>();
        commands.add(new Push(4));
        commands.add(new Push(5));
        commands.add(new Plus());
        commands.add(new Print());
        ((Interpreter) date[0]).interpret(commands);
        TestCase.assertEquals(" 9\n", date[1].toString());
    }

    @Test
    public void Interpreter3() {
        Object[] date = create();
        List<Command> commands = new ArrayList<>();
        List<String> str = new ArrayList<>();
        str.add("Print");
        str.add("Line");
        commands.add(new PrintLines(str));
        ((Interpreter) date[0]).interpret(commands);
        TestCase.assertEquals(" Print Line\n", date[1].toString());
    }
}