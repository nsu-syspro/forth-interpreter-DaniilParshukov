package ru.nsu.mmf.syspro.forth;

import java.util.EmptyStackException;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import ru.nsu.mmf.syspro.forth.Commands.Push;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Drop;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Dup;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Over;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Rot;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Swap;

public class BuiltlnTests {
    @Test
    public void drop() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Drop()), new int[] {}));
    }

    @Test(expected = EmptyStackException.class)
    public void drop2() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Drop()), new int[] {}));
    }

    @Test
    public void dup() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Dup()), new int[] {1, 1}));
    }

    @Test(expected = EmptyStackException.class)
    public void dup2() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Dup()), new int[] {}));
    }

    @Test
    public void over1() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Push(2), new Over()), new int[] {1, 2, 1}));
    }

    @Test(expected = EmptyStackException.class)
    public void over2() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Over()), new int[] {}));
    }

    @Test
    public void rot() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Push(2), new Push(3), new Rot()), new int[] {2, 1, 3}));
    }

    @Test(expected = EmptyStackException.class)
    public void rot2() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Push(2), new Rot()), new int[] {}));
    }

    @Test
    public void swap() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Push(2), new Swap()), new int[] {1, 2}));
    }

    @Test(expected = EmptyStackException.class)
    public void swap2() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new Swap()), new int[] {}));
    }
}
