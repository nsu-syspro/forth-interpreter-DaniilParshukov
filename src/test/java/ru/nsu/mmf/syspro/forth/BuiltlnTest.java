package ru.nsu.mmf.syspro.forth;

import org.junit.Test;

import junit.framework.TestCase;

public class BuiltlnTest {
    private static class TestPrinter implements Printer{
        StringBuilder sb;
        @Override
        public void print(String line){
            sb.append(line);
        }

        public TestPrinter(StringBuilder sb){
            this.sb = sb;
        }
    }

    @Test
    public void cr(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "cr".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals("\n\n", sb.toString());
    }

    @Test
    public void drop(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "1 2 drop .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 1\n", sb.toString());
    }

    @Test
    public void dup(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "1 dup . .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 1 1\n", sb.toString());
    }

    @Test
    public void emit(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "65 emit".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" A\n", sb.toString());
    }

    @Test
    public void emit2(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "-3 emit".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" Error: no ascii symbol\n", sb.toString());
    }

    @Test
    public void emit3(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "1000 emit".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" Error: no ascii symbol\n", sb.toString());
    }

    @Test
    public void over(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "1 2 over . .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 1 2\n", sb.toString());
    }

    @Test
    public void print(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "1 0 -0 -1 65 . . . . .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 65 -1 0 0 1\n", sb.toString());
    }

    @Test
    public void rot(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "1 2 3 rot . . .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 2 1 3\n", sb.toString());
    }

    @Test
    public void swap(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "1 2 swap . .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 1 2\n", sb.toString());
    }
}
