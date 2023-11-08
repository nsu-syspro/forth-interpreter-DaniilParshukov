package ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations;

public class Minus extends Arithmetic {
    @Override
    public int сalculate(int a, int b) {
        return a - b;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Minus)) {
            return false;
        }
        return true;
    }
}
