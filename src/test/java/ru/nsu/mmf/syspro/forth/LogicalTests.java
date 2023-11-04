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

    private Object[] create(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Parser parser = new Parser(ctx);
        Interpreter interpreter = new Interpreter(ctx);
        return new Object[] {interpreter, parser, sb};
    }

    @Test
    public void Equals1(){
        Object[] date = create();
        String[] words = "5 5 = .".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).parse(words));
        TestCase.assertEquals(" 1\n", date[2].toString());
    }

    @Test
    public void Equals2(){
        Object[] date = create();
        String[] words = "1 2 = .".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).parse(words));
        TestCase.assertEquals(" 0\n", date[2].toString());
    }

    @Test
    public void Equals3(){
        Object[] date = create();
        String[] words = "1 =".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).parse(words));
        TestCase.assertEquals(" Error: pop from empty stack\n", date[2].toString());
    }

    @Test
    public void Less1(){
        Object[] date = create();
        String[] words = "1 2 < .".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).parse(words));
        TestCase.assertEquals(" 1\n", date[2].toString());
    }

    @Test
    public void Less2(){
        Object[] date = create();
        String[] words = "2 2 < .".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).parse(words));
        TestCase.assertEquals(" 0\n", date[2].toString());
    }

    @Test
    public void Less3(){
        Object[] date = create();
        String[] words = "2 <".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).parse(words));
        TestCase.assertEquals(" Error: pop from empty stack\n", date[2].toString());
    }

    @Test
    public void More1(){
        Object[] date = create();
        String[] words = "2 1 > .".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).parse(words));
        TestCase.assertEquals(" 1\n", date[2].toString());
    }

    @Test
    public void More2(){
        Object[] date = create();
        String[] words = "2 2 > .".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).parse(words));
        TestCase.assertEquals(" 0\n", date[2].toString());
    }

    @Test
    public void More3(){
        Object[] date = create();
        String[] words = "2 >".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).parse(words));
        TestCase.assertEquals(" Error: pop from empty stack\n", date[2].toString());
    }
}
