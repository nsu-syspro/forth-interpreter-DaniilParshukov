package ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations;

public class Modulo extends Arithmetic {
    @Override
    public int сalculate(int a, int b){
        return a % b;
    }
}