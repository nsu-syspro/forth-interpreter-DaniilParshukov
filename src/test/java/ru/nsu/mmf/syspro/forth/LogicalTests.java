package ru.nsu.mmf.syspro.forth;

import org.junit.Test;

import junit.framework.TestCase;

public class LogicalTests {
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
    public void Equals1(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "5 5 = .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 1\n", sb.toString());
    }

    @Test
    public void Equals2(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "1 2 = .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 0\n", sb.toString());
    }

    @Test
    public void Less1(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "1 2 < .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 1\n", sb.toString());
    }

    @Test
    public void Less2(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "2 2 < .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 0\n", sb.toString());
    }

    @Test
    public void More1(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "2 1 > .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 1\n", sb.toString());
    }

    @Test
    public void More2(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "2 2 > .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 0\n", sb.toString());
    }
}
