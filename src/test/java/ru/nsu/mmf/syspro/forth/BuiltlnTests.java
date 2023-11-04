package ru.nsu.mmf.syspro.forth;

import org.junit.Test;

import junit.framework.TestCase;

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

    private Object[] create() {
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Parser parser = new Parser(ctx);
        Interpreter interpreter = new Interpreter(ctx);
        return new Object[] { interpreter, parser, sb };
    }

    @Test
    public void cr() {
        Object[] date = create();
        String[] words = "cr".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals("\n\n", date[2].toString());
    }

    @Test
    public void drop() {
        Object[] date = create();
        String[] words = "1 2 drop .".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" 1\n", date[2].toString());
    }

    @Test
    public void drop2() {
        Object[] date = create();
        String[] words = "drop".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" Error: pop from empty stack\n", date[2].toString());
    }

    @Test
    public void dup() {
        Object[] date = create();
        String[] words = "1 dup . .".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" 1 1\n", date[2].toString());
    }

    @Test
    public void dup2() {
        Object[] date = create();
        String[] words = "dup".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" Error: pop from empty stack\n", date[2].toString());
    }

    @Test
    public void emit() {
        Object[] date = create();
        String[] words = "65 emit".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" A\n", date[2].toString());
    }

    @Test
    public void emit2() {
        Object[] date = create();
        String[] words = "-3 emit".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" Error: no ascii symbol\n", date[2].toString());
    }

    @Test
    public void emit3() {
        Object[] date = create();
        String[] words = "1000 emit".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" Error: no ascii symbol\n", date[2].toString());
    }

    @Test
    public void emit4() {
        Object[] date = create();
        String[] words = "emit".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" Error: pop from empty stack\n", date[2].toString());
    }

    @Test
    public void over() {
        Object[] date = create();
        String[] words = "1 2 over . .".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" 1 2\n", date[2].toString());
    }

    @Test
    public void over2() {
        Object[] date = create();
        String[] words = "1 over . .".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" Error: pop from empty stack\n", date[2].toString());
    }

    @Test
    public void print() {
        Object[] date = create();
        String[] words = "1 0 -0 -1 65 . . . . .".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" 65 -1 0 0 1\n", date[2].toString());
    }

    @Test
    public void print2() {
        Object[] date = create();
        String[] words = ". . . . .".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" Error: pop from empty stack\n", date[2].toString());
    }

    @Test
    public void rot() {
        Object[] date = create();
        String[] words = "1 2 3 rot . . .".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" 2 1 3\n", date[2].toString());
    }

    @Test
    public void rot2() {
        Object[] date = create();
        String[] words = "1 2 rot ".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" Error: pop from empty stack\n", date[2].toString());
    }

    @Test
    public void swap() {
        Object[] date = create();
        String[] words = "1 2 swap . .".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" 1 2\n", date[2].toString());
    }

    @Test
    public void swap2() {
        Object[] date = create();
        String[] words = "1 swap".split(" ");
        ((Interpreter) date[0]).interpret(((Parser) date[1]).parse(words));
        TestCase.assertEquals(" Error: pop from empty stack\n", date[2].toString());
    }
}
