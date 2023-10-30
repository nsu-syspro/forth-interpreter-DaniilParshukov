package ru.nsu.mmf.syspro.forth;

import org.junit.Test;

import junit.framework.TestCase;

public class CommandsTests {
    
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
        Interpreter interpreter = new Interpreter(ctx);
        return new Object[] { interpreter, parser, sb };
    }

    

    @Test
    public void namberOnSteck() {
        Object[] date = create();
        String[] words = "1 .".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).pars(words));
        TestCase.assertEquals(" 1\n", date[2].toString());
    }

    @Test
    public void unknowCommand() {
        Object[] date = create();
        String[] words = "1 . print".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).pars(words));
        TestCase.assertEquals(" Error: unknow command (print)\n", date[2].toString());
    }

    @Test
    public void printLines() {
        Object[] date = create();
        String[] words = ".\" Hello, world!\"".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).pars(words));
        TestCase.assertEquals(" Hello, world!\n", date[2].toString());
    }

    @Test
    public void uncorrectPrintLines() {
        Object[] date = create();
        String[] words = ".\" No error".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).pars(words));
        TestCase.assertEquals(" Error: Absent \"\n", date[2].toString());
    }

    @Test
    public void printEmpty() {
        Object[] date = create();
        String[] words = ".\" \"".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).pars(words));
        TestCase.assertEquals(" \n", date[2].toString());
    }

    @Test
    public void commands1() {
        Object[] date = create();
        String[] words = ".\" (-20+12)/(2*6-10) =\" -20 12 + dup . .\" /\" 2 6 * 10 - dup . .\" =\" / .".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).pars(words));
        TestCase.assertEquals(" (-20+12)/(2*6-10) = -8 / 2 = -4\n", date[2].toString());
    }

    @Test
    public void commands2() {
        Object[] date = create();
        String[] words = ".\" max(3*7, 11*2) =\" 3 7 * dup 11 2 * dup rot > if drop then ; .".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).pars(words));
        TestCase.assertEquals(" max(3*7, 11*2) = 22\n", date[2].toString());
    }
}
