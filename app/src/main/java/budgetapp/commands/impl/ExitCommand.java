package budgetapp.commands.impl;

import budgetapp.budget.TransactionList;
import budgetapp.commands.ICommand;
import budgetapp.console.Console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExitCommand implements ICommand {
	Console c = new Console();

	@Override
	public void execute(TransactionList tl) {
		String choice = c.readString("Are you sure? (Remember to save) [y/n] [default: no]", "^(?:Yes|no|y|n)\\b");
		Pattern yes = Pattern.compile("^(?:Yes|y)\\b", Pattern.CASE_INSENSITIVE);
		Matcher matcher = yes.matcher(choice);
		if (matcher.find()) {
			System.out.println("Program closed!");
			System.exit(0);
		}
	}

	@Override
	public String toString() {
		return "Exit program";
	}

}
