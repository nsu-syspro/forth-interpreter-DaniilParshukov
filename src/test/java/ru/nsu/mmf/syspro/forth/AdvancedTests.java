package ru.nsu.mmf.syspro.forth;

import org.junit.Test;

import junit.framework.TestCase;

public class AdvancedTests {
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
    public void if1(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "1 if 1 . then ; 2 .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 1 2\n", sb.toString());
    }

    @Test
    public void if2(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "1 2 = if .\" 1=2\" then ;".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" ok\n", sb.toString());
    }

    @Test
    public void if3(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "8 4 2 0 dup 0 = if drop then ; / .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 2\n", sb.toString());
    }

    @Test
    public void if4(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "1 if then ;".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" ok\n", sb.toString());
    }
}
