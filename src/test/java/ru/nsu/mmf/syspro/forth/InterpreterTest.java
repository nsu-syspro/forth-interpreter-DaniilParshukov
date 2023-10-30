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

    private Object[] create(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Parser parser = new Parser(ctx);
        Interpreter interpreter = new Interpreter(ctx);
        return new Object[] {interpreter, parser, sb};
    }

    @Test
    public void namberOnSteck(){
        Object[] date = create();
        String[] words = "1 .".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).pars(words));
        TestCase.assertEquals(" 1\n", date[2].toString());
    }

    @Test
    public void unknowCommand(){
        Object[] date = create();
        String[] words = "1 . print".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).pars(words));
        TestCase.assertEquals(" Error: unknow command (print)\n", date[2].toString());
    }

    @Test
    public void popFromEmptyStack(){
        Object[] date = create();
        String[] words = ". . . .".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).pars(words));
        TestCase.assertEquals(" Error: pop from empty stack\n", date[2].toString());
    }

    @Test
    public void printLines(){
        Object[] date = create();
        String[] words = ".\" Hello, world!\"".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).pars(words));
        TestCase.assertEquals(" Hello, world!\n", date[2].toString());
    }

    @Test
    public void uncorrectPrintLines(){
        Object[] date = create();
        String[] words = ".\" No error".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).pars(words));
        TestCase.assertEquals(" Error: Absent \"\n", date[2].toString());
    }

    @Test
    public void printEmpty(){
        Object[] date = create();
        String[] words = ".\" \"".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).pars(words));
        TestCase.assertEquals(" \n", date[2].toString());
    }
}

