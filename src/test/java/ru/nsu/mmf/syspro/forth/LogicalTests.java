package ru.nsu.mmf.syspro.forth;

import java.util.EmptyStackException;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import ru.nsu.mmf.syspro.forth.Commands.Push;
import ru.nsu.mmf.syspro.forth.Commands.LogicalOperations.Equals;
import ru.nsu.mmf.syspro.forth.Commands.LogicalOperations.Less;
import ru.nsu.mmf.syspro.forth.Commands.LogicalOperations.More;

public class LogicalTests {
    @Test
    public void equals1() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Push(1), new Equals()), new int[] {1}));
    }

    @Test
    public void equals2() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Push(2), new Equals()), new int[] {0}));
    }

    @Test(expected = EmptyStackException.class)
    public void equals3() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(2), new Equals()), new int[] {}));
    }

    @Test
    public void less1() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Push(2), new Less()), new int[] {1}));
    }

    @Test
    public void less2() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Push(1), new Less()), new int[] {0}));
    }

    @Test(expected = EmptyStackException.class)
    public void less3() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Less()), new int[] {}));
    }

    @Test
    public void more1() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(3), new Push(2), new More() ), new int[] {1}));
    }

    @Test
    public void more2() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Push(2), new More()), new int[] {0}));
    }

    @Test(expected = EmptyStackException.class)
    public void more3() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(2), new More()), new int[] {}));
    }
}
