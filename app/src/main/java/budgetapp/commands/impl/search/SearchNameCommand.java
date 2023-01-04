package budgetapp.commands.impl.search;

import budgetapp.budget.TransactionList;
import budgetapp.commands.ICommand;

public class SearchNameCommand implements ICommand {
    @Override
    public void execute(TransactionList tl) {
        TransactionList result = tl.searchByName();
        result.printAll();
    }

    @Override
    public String toString() {
        return "Search transactions by name";
    }
}

