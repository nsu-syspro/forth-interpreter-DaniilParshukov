package ru.nsu.mmf.syspro.forth;

import org.junit.Test;

import junit.framework.TestCase;

public class ArithmeticTests {
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
    public void Plus(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "1 2 + .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 3\n", sb.toString());
    }

    @Test
    public void Divide(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "4 2 / .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 2\n", sb.toString());
    }

    @Test
    public void DivideZero(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "4 0 / .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" Error: division by zero\n", sb.toString());
    }

    @Test
    public void Divide2(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "8 3 / .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 2\n", sb.toString());
    }

    @Test
    public void Minus(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "3 5 - .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" -2\n", sb.toString());
    }

    @Test
    public void Modulo(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "8 3 mod .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 2\n", sb.toString());
    }

    @Test
    public void ModuloZero(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "8 0 mod .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" Error: division by zero\n", sb.toString());
    }

    @Test
    public void Multiply(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "8 3 * .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 24\n", sb.toString());
    }
}

