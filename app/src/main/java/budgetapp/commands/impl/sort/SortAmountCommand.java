package budgetapp.commands.impl.sort;

import budgetapp.budget.TransactionList;
import budgetapp.commands.ICommand;

public class SortAmountCommand implements ICommand {
    @Override
    public void execute(TransactionList tl) {
        TransactionList sorted = tl.sortByAmountAscending();
        sorted.printAll();
    }

    @Override
    public String toString() {
        return "Sort transactions by amount";
    }
}
