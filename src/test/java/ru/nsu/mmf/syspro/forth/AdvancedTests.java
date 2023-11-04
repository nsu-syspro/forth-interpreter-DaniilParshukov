package ru.nsu.mmf.syspro.forth;

import org.junit.Test;

import junit.framework.TestCase;

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

    private Object[] create() {
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Parser parser = new Parser(ctx);
        Interpreter interpreter = new Interpreter(ctx);
        return new Object[] { interpreter, parser, sb };
    }

    @Test
    public void if1() {
        Object[] date = create();
        String[] words = "1 if 1 . then ; 2 .".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" 1 2\n", date[2].toString());
    }

    @Test
    public void if2() {
        Object[] date = create();
        String[] words = "1 2 = if .\" 1=2\" then ;".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" ok\n", date[2].toString());
    }

    @Test
    public void if3() {
        Object[] date = create();
        String[] words = "8 4 2 0 dup 0 = if drop then ; / .".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" 2\n", date[2].toString());
    }

    @Test
    public void if4() {
        Object[] date = create();
        String[] words = "1 if then ;".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" ok\n", date[2].toString());
    }
}
