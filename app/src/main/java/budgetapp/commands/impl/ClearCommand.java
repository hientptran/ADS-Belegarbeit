package budgetapp.commands.impl;

import budgetapp.budget.TransactionList;
import budgetapp.commands.ICommand;

public class ClearCommand implements ICommand {

    @Override
    public void execute(TransactionList tl) {
        tl.clear();
    }

    @Override
    public String toString() {
        return "Clear all transactions";
    }
}
