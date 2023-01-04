package budgetapp.commands.impl.sort;

import budgetapp.budget.TransactionList;
import budgetapp.commands.ICommand;
import budgetapp.commands.CommandFactory;
import budgetapp.console.Console;
import budgetapp.linkedlist.LinkedList;

import java.io.IOException;

public class SortCommand implements ICommand {
    Console c = new Console();

    @Override
    public void execute(TransactionList tl) throws IOException {
        LinkedList<ICommand> sortCommands = CommandFactory.returnSortCommands();
        sortCommands.printAll();
        int choice = c.readInt("\nSelect an option: ", 0, sortCommands.size());
        sortCommands.get(choice).execute(tl);
    }

    @Override
    public String toString() {
        return "Sort transactions";
    }
}
