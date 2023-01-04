package budgetapp.commands.impl;

import budgetapp.budget.*;
import budgetapp.commands.ICommand;

public class RemoveCommand implements ICommand {
    @Override
    public void execute(TransactionList tl) {
        tl.removeTransaction();
    }

    @Override
    public String toString() {
        return "Remove a transaction";
    }
}
