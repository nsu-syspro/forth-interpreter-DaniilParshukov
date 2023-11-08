package ru.nsu.mmf.syspro.forth;

import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import ru.nsu.mmf.syspro.forth.Commands.Command;
import ru.nsu.mmf.syspro.forth.Commands.Push;
import ru.nsu.mmf.syspro.forth.Commands.AdvancedCommands.If;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Divide;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Minus;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Multiply;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Plus;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Cr;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Drop;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Dup;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Emit;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Exit;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Over;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Print;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Rot;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Swap;

public class ParserTests {
    @Test(expected = RuntimeException.class)
    public void parseError1() {
        (new Parser()).parse("if 1 2 + . 1 0 /".split(" "));
    }

    @Test(expected = RuntimeException.class)
    public void parseError2() {
        (new Parser()).parse("1 2 max".split(" "));
    }

    @Test(expected = RuntimeException.class)
    public void parseError3() {
        (new Parser()).parse(".\" string with no end".split(" "));
    }

    @Test
    public void parser0() {
        TestCase.assertTrue((new Parser()).parse("1".split(" ")).get(0).equals(new Push(1)));
    }

    @Test
    public void parser1() {
        List<Command> simple = List.of(new Plus(), new Minus(), new Multiply(), new Divide());
        List<Command> commands = (new Parser()).parse("+ - * /".split(" "));
        for (int i = 0; i < commands.size(); i++) {
            TestCase.assertTrue(commands.get(i).equals(simple.get(i)));
        }
    }

    @Test
    public void parser2() {
        List<Command> simple = List.of(new Cr(), new Drop(), new Dup(), new Emit(), new Exit(), new Over(), new Print(), new Rot(), new Swap());
        List<Command> commands = (new Parser()).parse("cr drop dup emit exit over . rot swap".split(" "));
        for (int i = 0; i < commands.size(); i++) {
            TestCase.assertTrue(commands.get(i).equals(simple.get(i)));
        }
    }

    @Test
    public void parser3() {
        List<Command> simple = List.of(new If(List.of(new Push(5), new Print())));
        List<Command> commands = (new Parser()).parse("if 5 . then ;".split(" "));
        for (int i = 0; i < commands.size(); i++) {
            TestCase.assertTrue(commands.get(i).equals(simple.get(i)));
        }
    }
}
