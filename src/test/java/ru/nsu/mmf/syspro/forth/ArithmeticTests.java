package ru.nsu.mmf.syspro.forth;

import java.util.EmptyStackException;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import ru.nsu.mmf.syspro.forth.Commands.Push;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Divide;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Minus;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Modulo;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Multiply;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Plus;

public class ArithmeticTests {
    @Test
    public void plus1() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Push(2), new Plus()), new int[] {3}));
    }

    @Test(expected = EmptyStackException.class)
    public void plus2() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Plus()), new int[] {}));
    }

    @Test
    public void divide() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(8), new Push(2), new Divide()), new int[] {4}));
    }

    @Test(expected = ArithmeticException.class)
    public void divideZero() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(8), new Push(0), new Divide()), new int[] {}));
    }

    @Test
    public void divide2() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(8), new Push(3), new Divide()), new int[] {2}));
    }

    @Test
    public void divide3() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(0), new Push(142), new Divide() ), new int[] {0}));
    }

    @Test
    public void minus1() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(3), new Push(5), new Minus()), new int[] {-2}));
    }

    @Test(expected = EmptyStackException.class)
    public void minus2() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Divide()), new int[] {}));
    }

    @Test
    public void modulo1() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(8), new Push(3), new Modulo()), new int[] {2}));
    }

    @Test(expected = ArithmeticException.class)
    public void moduloZero() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Push(0), new Divide()), new int[] {}));
    }

    @Test(expected = EmptyStackException.class)
    public void modulo2() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Plus()), new int[] {}));
    }

    @Test
    public void multiply1() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(8), new Push(3), new Multiply()), new int[] {24}));
    }

    @Test(expected = EmptyStackException.class)
    public void multiply2() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Multiply()), new int[] {0}));
    }
}