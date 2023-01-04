package budgetapp.commands.impl.search;

import budgetapp.budget.TransactionList;
import budgetapp.commands.ICommand;

public class SearchCategoryCommand implements ICommand {
    @Override
    public void execute(TransactionList tl) {
        TransactionList result = tl.searchByCategory();
        result.printAll();
    }

    @Override
    public String toString() {
        return "Search transactions by category";
    }
}

