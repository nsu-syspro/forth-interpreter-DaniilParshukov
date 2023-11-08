package ru.nsu.mmf.syspro.forth;

import java.util.ArrayList;
import java.util.List;

import ru.nsu.mmf.syspro.forth.Commands.Command;
import ru.nsu.mmf.syspro.forth.Commands.PrintLines;
import ru.nsu.mmf.syspro.forth.Commands.Push;
import ru.nsu.mmf.syspro.forth.Commands.AdvancedCommands.If;
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

public class Parser {
    private int pos;

    public List<Command> parse(String[] words) {
        pos = 0;
        return getCommands(words, null, null);
    }
    private List<Command> getCommands(String[] words, String stopAt1, String stopAt2) {
        List<Command> commands = new ArrayList<>();
        for (; pos < words.length; pos++) {
            if (words[pos].equals(stopAt1)) {
                if (pos + 1 < words.length) {
                    if (words[pos + 1].equals(stopAt2)) {
                        pos++;
                        return commands;
                    }
                }
            }
            commands.add(getCmd(words));
        }
        if (stopAt1 != null) {
            throw new RuntimeException("Miss stopAt: " + stopAt1.toString() + " " + stopAt2.toString());
        }
        return commands;
    }

    private Command getCmd(String[] words) {
        try {
            return new Push(Integer.parseInt(words[pos]));
        } catch (NumberFormatException e) {
            return switch (words[pos]) {
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
                    List<String> str = getLinesToPrint(words);
                    yield new PrintLines(str);
                }
                case "if" -> {
                    pos++;
                    List<Command> commands = getCommands(words, "then", ";");
                    yield new If(commands);
                }
                default -> {
                    throw new RuntimeException("Unknow command (" + words[pos] + ")");
                }
            };
        }
    }

    private List<String> getLinesToPrint(String[] words) {
        List<String> str = new ArrayList<>();
        while (++pos < words.length) {
            if (words[pos].charAt(words[pos].length() - 1) == '\"') {
                str.add(words[pos].substring(0, words[pos].length() - 1));
                return str;
            } else {
                str.add(words[pos]);
            }
        }
        throw new RuntimeException("Absent \"");
    }
}
