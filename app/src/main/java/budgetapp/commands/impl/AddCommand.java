package budgetapp.commands.impl;

import budgetapp.commands.ICommand;
import budgetapp.budget.*;

public class AddCommand implements ICommand {

    @Override
    public void execute(TransactionList tl) {
        tl.addTransaction();
    }

    @Override
    public String toString() {
        return "Add a transaction";
    }
}
