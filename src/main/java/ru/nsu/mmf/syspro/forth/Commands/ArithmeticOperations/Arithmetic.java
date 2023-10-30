package ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations;

import ru.nsu.mmf.syspro.forth.Context;

public class Arithmetic {
    public static boolean сalculate(Context ctx, char operator){
        if (ctx.S.size() < 2){
            ctx.printer.print(" Error: pop from empty stack");
            return true;
        }
        int b = ctx.S.pop();
        int a = ctx.S.pop();
        if ((b == 0) & ((operator == '/') | (operator == '%'))){
            ctx.printer.print(" Error: division by zero");
            ctx.S.push(a);
            ctx.S.push(b);
            return true;
        }
        switch (operator) {
            case '+':
                ctx.S.push(a + b);
                break;
            case '-':
                ctx.S.push(a - b);
                break;
            case '*':
                ctx.S.push(a * b);
                break;
            case '/':
                ctx.S.push(a / b);
                break;
            case '%':
                ctx.S.push(a % b);
                break;
            default:
                ctx.printer.print(" Attempt to calculate a non-arithmetic operation");
                return true;
        }
        return false;
    }
}
