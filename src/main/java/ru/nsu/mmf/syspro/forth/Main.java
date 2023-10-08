package ru.nsu.mmf.syspro.forth;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Stack<Integer> S = new Stack<Integer>();
        boolean flag = true;
        while(flag){
            System.out.print("> ");
            String line = in.nextLine();
            boolean ok = true;
            boolean error = false;
            System.out.print("<");
            for(String token:line.split(" ")){
                try {
                    int num = Integer.parseInt(token);
                    S.push(num);
                }catch (NumberFormatException e){
                    switch(token){
                       case "+":
                            if (S.size() < 2){
                                System.out.print(" Error: pop from empty stack");
                                error = true;
                                break;
                            }
                            int b = S.pop();
                            int a = S.pop();
                            S.push(a + b);
                            break;
                        case "-":
                            if (S.size() < 2){
                                System.out.print(" Error: pop from empty stack");
                                error = true;
                                break;
                            }
                            b = S.pop();
                            a = S.pop();
                            S.push(a - b);
                            break;
                        case "*":
                            if (S.size() < 2){
                                System.out.print(" Error: pop from empty stack");
                                error = true;
                                break;
                            }
                            b = S.pop();
                            a = S.pop();
                            S.push(a * b);
                            break;
                        case "/":
                            if (S.size() < 2){
                                System.out.print(" Error: pop from empty stack");
                                error = true;
                                break;
                            }
                            b = S.pop();
                            a = S.pop();
                            if (b == 0){
                                System.out.print(" Error: division by zero");
                                error = true;
                                break;
                            }
                            S.push(a / b);
                            break;
                        case "mod":
                            if (S.size() < 2){
                                System.out.print(" Error: pop from empty stack");
                                error = true;
                                break;
                            }
                            b = S.pop();
                            a = S.pop();
                            if (b == 0){
                                System.out.print(" Error: division by zero");
                                error = true;
                                break;
                            }
                            S.push(a % b);
                            break;
                        case "dup":
                            a = S.peek();
                            S.push(a);
                            break;
                        case "over":
                            a = S.pop();
                            b = S.peek();
                            S.push(a);
                            S.push(b);
                            break;
                        case "drop":
                            if (S.empty()){
                                System.out.print(" Error: pop from empty stack");
                                error = true;
                                break;
                            }
                            S.pop();
                            break;
                        case ".":
                            if (S.empty()){
                                System.out.print(" Error: pop from empty stack");
                                error = true;
                                break;
                            }
                            System.out.printf(" %d", S.pop());
                            ok = false;
                            break;
                        case "swap":
                            if (S.size() < 2){
                                System.out.print(" Error: pop from empty stack");
                                error = true;
                                break;
                            }
                            a = S.pop();
                            b = S.pop();
                            S.push(b);
                            S.push(a);
                            break;
                        case "rot":
                            if (S.size() < 2){
                                System.out.print("  Error: pop from empty stack");
                                error = true;
                                break;
                            }
                            a = S.pop();
                            b = S.pop();
                            int c = S.pop();
                            S.push(a);
                            S.push(c);
                            S.push(b);
                            break;
                        case "emit":
                            if (S.empty()){
                                System.out.print(" Error: pop from empty stack");
                                error = true;
                               break;
                            }
                            System.out.printf(" %c", S.pop());
                            ok = false;
                            break;
                        case "cr":
                            System.out.println();
                            break;
                        case "exit":
                            in.close();
                            flag = false;
                            break;
                        case ">":
                            if (S.size() < 2){
                                System.out.print(" Error: pop from empty stack");
                                error = true;
                                break;
                            }
                            b = S.pop();
                            a = S.pop();
                            if (a > b){
                                S.push(1);
                            }else{
                                S.push(0);
                            }
                            break;
                        case "<":
                            if (S.size() < 2){
                                System.out.print(" Error: pop from empty stack");
                                error = true;
                                break;
                            }
                            b = S.pop();
                            a = S.pop();
                            if (a < b){
                                S.push(1);
                            }else{
                                S.push(0);
                            }
                            break;
                        case "=":
                            if (S.size() < 2){
                                System.out.print(" Error: pop from empty stack");
                                error = true;
                                break;
                            }
                            b = S.pop();
                            a = S.pop();
                            if (a == b){
                                S.push(1);
                            }else{
                                S.push(0);
                            }
                            break;
                        default:
                            if(token.length() > 3 & token.charAt(0) == '.' & token.charAt(1) == '\"' & token.charAt(token.length()-1) == '\"'){
                                System.out.printf(" %s", token.substring(2, token.length()-1));
                                ok = false;
                            }else{
                                System.out.print(" Error: unknown command");
                                error = true;
                            }
                    }
                }
                if(error){
                    break;
                }
            }
            if(ok & !error){
                System.out.println(" ok");
            }else{
                System.out.println();
            }
        }
    }
}