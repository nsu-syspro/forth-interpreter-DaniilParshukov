package ru.nsu.mmf.syspro.forth;

import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import ru.nsu.mmf.syspro.forth.Commands.Push;

public class CommandsTests {
    @Test
    public void push1() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(8)), new int[] {8}));
    }

    @Test
    public void push2() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(-12)), new int[] {-12}));
    }
}
