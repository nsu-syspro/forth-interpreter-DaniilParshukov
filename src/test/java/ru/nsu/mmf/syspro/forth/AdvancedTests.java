package ru.nsu.mmf.syspro.forth;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import ru.nsu.mmf.syspro.forth.Commands.Push;
import ru.nsu.mmf.syspro.forth.Commands.AdvancedCommands.If;

public class AdvancedTests {
    @Test
    public void if1() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new If(List.of(new Push(2)))), new int[] {2}));
    }

    @Test
    public void if2() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(0), new If(List.of(new Push(2)))), new int[] {}));
    }

    @Test
    public void if3() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(0), new Push(142), new If(List.of(new Push(3), new Push(4)))), new int[] {4, 3, 0}));
    }

    @Test
    public void if4() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new Push(1), new If(new ArrayList<>())), new int[] {}));
    }

    @Test(expected = EmptyStackException.class)
    public void if5() {
        TestCase.assertTrue(TestInt.testInterpret(List.of(new If(List.of(new Push(2)))), new int[] {}));
    }
}