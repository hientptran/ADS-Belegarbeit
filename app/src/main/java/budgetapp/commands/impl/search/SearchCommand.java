package budgetapp.commands.impl.search;

import budgetapp.budget.TransactionList;
import budgetapp.commands.CommandFactory;
import budgetapp.commands.ICommand;
import budgetapp.console.Console;
import budgetapp.linkedlist.LinkedList;

import java.io.IOException;

public class SearchCommand implements ICommand {
    Console c = new Console();

    @Override
    public void execute(TransactionList tl) throws IOException {
        LinkedList<ICommand> searchCommands = CommandFactory.returnSearchCommands();
        searchCommands.printAll();
        int choice = c.readInt("\nSelect an option: ", 0, searchCommands.size());
        searchCommands.get(choice).execute(tl);
    }

    @Override
    public String toString() {
        return "Search in transactions";
    }
}
