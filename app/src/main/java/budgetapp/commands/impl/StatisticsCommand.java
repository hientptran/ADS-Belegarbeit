package budgetapp.commands.impl;

import budgetapp.budget.TransactionList;
import budgetapp.commands.ICommand;

public class StatisticsCommand implements ICommand {

    @Override
    public void execute(TransactionList tl) {
        tl.showStatistics();
    }

    @Override
    public String toString() {
        return "Show statistics";
    }
}
