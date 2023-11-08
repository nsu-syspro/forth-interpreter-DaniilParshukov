package ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations;

public class Divide extends Arithmetic {
    @Override
    public int сalculate(int a, int b) {
        return a / b;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Divide)) {
            return false;
        }
        return true;
    }
}
