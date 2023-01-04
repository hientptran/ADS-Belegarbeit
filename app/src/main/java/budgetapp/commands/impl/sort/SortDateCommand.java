package budgetapp.commands.impl.sort;

import budgetapp.budget.TransactionList;
import budgetapp.commands.ICommand;

public class SortDateCommand implements ICommand {
    @Override
    public void execute(TransactionList tl) {
        TransactionList sorted = tl.sortByDateAscending();
        sorted.printAll();
    }

    @Override
    public String toString() {
        return "Sort transactions by date";
    }
}