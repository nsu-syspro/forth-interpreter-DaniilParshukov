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

    private Object[] create(){
        StringBuilder sb = new StringBuilder();
        Printer printer = new TestPrinter(sb);
        Context ctx = new Context(printer);
        Parser parser = new Parser(ctx);
        Interpreter interpreter = new Interpreter(ctx);
        return new Object[] {interpreter, parser, sb};
    }

    @Test
    public void Plus(){
        Object[] date = create();
        String[] words = "1 2 + .".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).pars(words));
        TestCase.assertEquals(" 3\n", date[2].toString());
    }

    @Test
    public void Divide(){
        Object[] date = create();
        String[] words = "4 2 / .".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).pars(words));
        TestCase.assertEquals(" 2\n", date[2].toString());
    }

    @Test
    public void DivideZero(){
        Object[] date = create();
        String[] words = "4 0 / .".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).pars(words));
        TestCase.assertEquals(" Error: division by zero\n", date[2].toString());
    }

    @Test
    public void Divide2(){
        Object[] date = create();
        String[] words = "8 3 / .".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).pars(words));
        TestCase.assertEquals(" 2\n", date[2].toString());
    }

    @Test
    public void Minus(){
        Object[] date = create();
        String[] words = "3 5 - .".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).pars(words));
        TestCase.assertEquals(" -2\n", date[2].toString());
    }

    @Test
    public void Modulo(){
        Object[] date = create();
        String[] words = "8 3 mod .".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).pars(words));
        TestCase.assertEquals(" 2\n", date[2].toString());
    }

    @Test
    public void ModuloZero(){
        Object[] date = create();
        String[] words = "8 0 mod .".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).pars(words));
        TestCase.assertEquals(" Error: division by zero\n", date[2].toString());
    }

    @Test
    public void Multiply(){
        Object[] date = create();
        String[] words = "8 3 * .".split(" ");
        ((Interpreter)date[0]).interpret(((Parser)date[1]).pars(words));
        TestCase.assertEquals(" 24\n", date[2].toString());
    }
}

