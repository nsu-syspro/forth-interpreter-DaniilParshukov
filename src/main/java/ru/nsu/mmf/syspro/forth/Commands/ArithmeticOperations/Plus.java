package ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations;

public class Plus extends Arithmetic {
    @Override
    public int сalculate(int a, int b) {
        return a + b;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Plus)) {
            return false;
        }
        return true;
    }
}
