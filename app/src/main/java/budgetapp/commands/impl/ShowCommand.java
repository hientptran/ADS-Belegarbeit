package budgetapp.commands.impl;

import budgetapp.budget.TransactionList;
import budgetapp.commands.ICommand;

import java.io.IOException;

public class ShowCommand implements ICommand {
    @Override
    public void execute(TransactionList tl) throws IOException {
        tl.printAll();
    }

    @Override
    public String toString() {
        return "Show all transactions";
    }
}
