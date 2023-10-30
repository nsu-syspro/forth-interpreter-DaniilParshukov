package ru.nsu.mmf.syspro.forth;

import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import ru.nsu.mmf.syspro.forth.Commands.Command;

public class ParserTests {
    
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
        Parser parser = new Parser(ctx);
        return new Object[] { parser, sb };
    }

    @Test
    public void ParserError1(){
        Object[] date = create();
        String[] words = "if 1 2 + . 1 0 /".split(" ");
        ((Parser)date[0]).pars(words);
        TestCase.assertEquals(" Error: miss stopAt: then ;", date[1].toString());
    }

    @Test
    public void ParserError2(){
        Object[] date = create();
        String[] words = "1 2 max".split(" ");
        ((Parser)date[0]).pars(words);
        TestCase.assertEquals(" Error: unknow command (max)", date[1].toString());
    }

    @Test
    public void ParserError3(){
        Object[] date = create();
        String[] words = ".\" string with no end".split(" ");
        ((Parser)date[0]).pars(words);
        TestCase.assertEquals(" Error: Absent \"", date[1].toString());
    }

    @Test
    public void Parser1(){
        Object[] date = create();
        String[] words = "+ - * /".split(" ");
        List<Command> commands = ((Parser)date[0]).pars(words);
        String[] sample = {"Plus", "Minus", "Multiply", "Divide"};
        for(Integer i = 0; i < commands.size(); i++){
            TestCase.assertEquals(commands.get(i).getClass().getName(), "ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations." + sample[i]);
        }
    }

    @Test
    public void Parser2(){
        Object[] date = create();
        String[] words = "cr drop dup emit exit over . rot swap".split(" ");
        List<Command> commands = ((Parser)date[0]).pars(words);
        String[] sample = {"Cr", "Drop", "Dup", "Emit", "Exit", "Over", "Print", "Rot", "Swap"};
        for(Integer i = 0; i < commands.size(); i++){
            TestCase.assertEquals(commands.get(i).getClass().getName(), "ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands." + sample[i]);
        }
    }

    @Test
    public void Parser3(){
        Object[] date = create();
        String[] words = "< = >".split(" ");
        List<Command> commands = ((Parser)date[0]).pars(words);
        String[] sample = {"Less", "Equals", "More"};
        for(Integer i = 0; i < commands.size(); i++){
            TestCase.assertEquals(commands.get(i).getClass().getName(), "ru.nsu.mmf.syspro.forth.Commands.LogicalOperations." + sample[i]);
        }
    }
}
