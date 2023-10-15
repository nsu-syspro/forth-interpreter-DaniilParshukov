package ru.nsu.mmf.syspro.forth;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Printer printer = new ConsolePrinter();
        Context ctx = new Context(printer);
        Interpreter itr = new Interpreter(ctx);
        while(!ctx.exit){
            System.out.print("> ");
            String line = in.nextLine();
            String[] words = line.split(" ");
            System.out.print("<");
            itr.interpret(words);
        }
        in.close();
    }

    private static class ConsolePrinter implements Printer{
        @Override
        public void print(String line){
            System.out.print(line);
        }
    }
}