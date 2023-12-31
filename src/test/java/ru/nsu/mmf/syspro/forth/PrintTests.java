package ru.nsu.mmf.syspro.forth;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import ru.nsu.mmf.syspro.forth.Commands.Command;
import ru.nsu.mmf.syspro.forth.Commands.PrintLines;
import ru.nsu.mmf.syspro.forth.Commands.Push;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Cr;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Emit;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Print;

public class PrintTests {
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

    private StringBuilder stringBuilderInterpret(Command[] cmds) {
        StringBuilder sb = new StringBuilder();
        Context ctx = new Context(new TestPrinter(sb));
        Interpreter interpreter = new Interpreter(ctx);
        List<Command> list = new ArrayList<>();
        for (Command cmd : cmds) {
            list.add(cmd);
        }
        interpreter.interpret(list);
        return sb;
    }

    @Test
    public void cr() {
        Command[] cmds = { new Cr() };
        StringBuilder sb = stringBuilderInterpret(cmds);
        TestCase.assertEquals("\n\n", sb.toString());
    }

    @Test
    public void emit1() {
        Command[] cmds = { new Push(65), new Emit() };
        StringBuilder sb = stringBuilderInterpret(cmds);
        TestCase.assertEquals(" A\n", sb.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void emit2() {
        Command[] cmds = { new Push(-3), new Emit() };
        stringBuilderInterpret(cmds);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emit3() {
        Command[] cmds = { new Push(1000), new Emit() };
        stringBuilderInterpret(cmds);
    }

    @Test(expected = EmptyStackException.class)
    public void emit4() {
        Command[] cmds = { new Emit() };
        stringBuilderInterpret(cmds);
    }

    @Test
    public void print1() {
        Command[] cmds = { new Push(65), new Print() };
        StringBuilder sb = stringBuilderInterpret(cmds);
        TestCase.assertEquals(" 65\n", sb.toString());
    }

    @Test(expected = EmptyStackException.class)
    public void print2() {
        Command[] cmds = { new Print() };
        stringBuilderInterpret(cmds);
    }

    @Test
    public void printLines() {
        StringBuilder sb = stringBuilderInterpret(new Command[] { new PrintLines(List.of("Print", "Line")) });
        TestCase.assertEquals(" Print Line\n", sb.toString());
    }
}
