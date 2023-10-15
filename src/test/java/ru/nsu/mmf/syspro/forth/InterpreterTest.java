package ru.nsu.mmf.syspro.forth;

import org.junit.Test;

import junit.framework.TestCase;

public class InterpreterTest {

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
    public void emty(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "".split(" ");
        interpreter.interpret(words);

    }

    @Test
    public void namberOnSteck(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "1 .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" 1\n", sb.toString());
    }

    @Test
    public void unknowCommand(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = "1 . print".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" Error: unknow command (print)\n", sb.toString());
    }

    @Test
    public void popFromEmptyStack(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = ". . . .".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" Error: pop from empty stack\n", sb.toString());
    }

    @Test
    public void printLines(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = ".\" Hello, world!\"".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" Hello, world!\n", sb.toString());
    }

    @Test
    public void uncorrectPrintLines(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = ".\" No error".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" Error: Absent \"\n", sb.toString());
    }

    @Test
    public void printEmpty(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Interpreter interpreter = new Interpreter(ctx);
        String[] words = ".\" \"".split(" ");
        interpreter.interpret(words);
        TestCase.assertEquals(" \n", sb.toString());
    }
}

