package budgetapp.commands.impl.search;

import budgetapp.budget.TransactionList;
import budgetapp.commands.ICommand;

public class SearchDateCommand implements ICommand {
    @Override
    public void execute(TransactionList tl) {
        TransactionList result = tl.searchByDate();
        result.printAll();
    }

    @Override
    public String toString() {
        return "Search transactions by date";
    }
}
