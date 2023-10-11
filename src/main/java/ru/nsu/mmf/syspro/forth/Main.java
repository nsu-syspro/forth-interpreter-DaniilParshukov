package ru.nsu.mmf.syspro.forth;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Context ctx = new Context();
        Interpreter itr = new Interpreter(ctx);
        while(!ctx.exit){
            System.out.print("> ");
            String line = in.nextLine();
            String[] words = line.split(" ");
            ctx.ok = true;
            ctx.error = false;
            System.out.print("<");
            itr.interpret(words);
        }
        in.close();
    }
}