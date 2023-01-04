package budgetapp.commands.impl;

import budgetapp.budget.TransactionList;
import budgetapp.commands.ICommand;

import java.io.IOException;

public class SaveCommand implements ICommand {
    @Override
    public void execute(TransactionList tl) throws IOException {
        tl.saveTransactions();
    }

    @Override
    public String toString() {
        return "Save transactions";
    }
}
