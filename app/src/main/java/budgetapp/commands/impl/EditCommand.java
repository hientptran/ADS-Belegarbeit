package budgetapp.commands.impl;

import budgetapp.budget.*;
import budgetapp.commands.ICommand;

public class EditCommand implements ICommand {
    @Override
    public void execute(TransactionList tl) {
        tl.editTransaction();
    }

    @Override
    public String toString() {
        return "Edit a transaction";
    }
}
