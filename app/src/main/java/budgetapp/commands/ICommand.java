package budgetapp.commands;

import budgetapp.budget.TransactionList;

import java.io.IOException;

public interface ICommand {
	public void execute(TransactionList tl) throws IOException;
}
