/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package budgetapp;

import java.io.IOException;
import budgetapp.commands.CommandFactory;
import budgetapp.commands.CommandInvoker;
import budgetapp.commands.ICommand;
import budgetapp.console.Console;
import budgetapp.linkedlist.LinkedList;

public class App {
    public static void main(String[] args) throws IOException {
        Console console = new Console();
        LinkedList<ICommand> commands = CommandFactory.returnCommands();
        CommandInvoker invoker = new CommandInvoker(console);
        invoker.cli(commands);
    }
}
