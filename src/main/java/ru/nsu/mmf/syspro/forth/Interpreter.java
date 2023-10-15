package ru.nsu.mmf.syspro.forth;

import java.util.ArrayList;
import java.util.List;

import ru.nsu.mmf.syspro.forth.Commands.Command;
import ru.nsu.mmf.syspro.forth.Commands.PrintLines;
import ru.nsu.mmf.syspro.forth.Commands.Push;
import ru.nsu.mmf.syspro.forth.Commands.AdvancedСommands.If;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Divide;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Minus;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Modulo;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Multiply;
import ru.nsu.mmf.syspro.forth.Commands.ArithmeticOperations.Plus;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Cr;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Drop;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Dup;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Emit;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Exit;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Over;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Print;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Rot;
import ru.nsu.mmf.syspro.forth.Commands.BuiltInCommands.Swap;
import ru.nsu.mmf.syspro.forth.Commands.LogicalOperations.Equals;
import ru.nsu.mmf.syspro.forth.Commands.LogicalOperations.Less;
import ru.nsu.mmf.syspro.forth.Commands.LogicalOperations.More;

public class Interpreter {
    private int pos;
    private Context ctx;

    public Interpreter(Context ctx){
        this.ctx = ctx;
    }

    public void interpret(String[] words){
        pos = 0;
        ctx.ok = true;
        ctx.error = false;
        List<Command> commands = getCommands(words, null, null);
        if(commands == null){
            ctx.printer.print("\n");
            return;
        }
        for(int i = 0; i < commands.size(); i++){
            commands.get(i).apply(ctx);
            if(ctx.error){
                break;
            }
        }
        if(ctx.ok & !ctx.error){
            ctx.printer.print(" ok\n");
        }else{
            ctx.printer.print("\n");
        }
    }

    private List<Command> getCommands(String[] words, String stopAt1, String stopAt2){
        List<Command> commands = new ArrayList<>();
        for(; pos < words.length; pos++){
            if(words[pos].equals(stopAt1)){
                if(pos + 1 < words.length){
                    if(words[pos + 1].equals(stopAt2)){
                        pos++;
                        return commands;
                    }
                }
            }
            Command cmd = getCmd(words);
            if(cmd == null){
                return null;
            }else{
                commands.add(cmd);
            }
        }
        if(stopAt1 != null){
            System.out.printf(" Error: miss stopAt: %s %s", stopAt1, stopAt2);
            return null;
        }
        return commands;
    }


    private Command getCmd(String[] words){
        try {
            int num = Integer.parseInt(words[pos]);
            return new Push(num);
        }catch (NumberFormatException e){
            return switch(words[pos]){
                case "+" -> new Plus();
                case "-" -> new Minus();
                case "*" -> new Multiply();
                case "/" -> new Divide();
                case "mod" -> new Modulo();
                case "dup" -> new Dup();
                case "over" -> new Over();
                case "drop" -> new Drop();
                case "." -> new Print();
                case "swap" -> new Swap();
                case "rot" -> new Rot();
                case "emit" -> new Emit();
                case "cr" -> new Cr();
                case "exit" -> new Exit();
                case ">" -> new More();
                case "<" -> new Less();
                case "=" -> new Equals();
                case ".\"" -> {
                    List<String> str = SgetLinesToPrint(words);
                    if(str == null){
                        yield null;
                    }
                    yield new PrintLines(str);
                }
                case "if" -> {
                    //if THEN_BRANCH then ;
                    pos++;
                    List<Command> commands = getCommands(words, "then", ";");
                    if(commands == null){
                        yield null;
                    }
                    yield new If(commands);
                }
                default -> {
                    ctx.printer.print(" Error: unknow command (" + words[pos] + ")");
                    yield null;
                }
            };
        }
    }

    private List<String> SgetLinesToPrint(String[] words){
        List<String> str = new ArrayList<>();
        while(++pos < words.length){
            if(words[pos].charAt(words[pos].length() - 1) == '\"'){
                str.add(words[pos].substring(0, words[pos].length() - 1));
                return str;
            }else{
                str.add(words[pos]);
            }
            ctx.ok = false;
        }
        ctx.printer.print(" Error: Absent \"");
        return null;
    }
}
