package budgetapp.commands;

import java.io.IOException;

import budgetapp.console.Console;
import budgetapp.linkedlist.LinkedList;
import budgetapp.budget.TransactionList;

public class CommandInvoker {
	final String choicePrompt = "Select an option: ";
	final String header = String.format("Console-Application: Budget App %58s", "Hien Tran - s0585489");
	final String separator = "-------------------------------------------------------------------------------------------";
	final Console console;

	public CommandInvoker(Console console) {
		super();
		this.console = console;
	}

	public void cli(LinkedList<ICommand> commands) throws IOException {
		TransactionList tl = new TransactionList();
		tl.importTransactions();
		console.printString(separator);
		console.printString(header);
		console.printString(separator);
		do {
			printCommandLineMenu(commands);
			ICommand cmd = selectAnOption(console, commands);
			cmd.execute(tl);
		} while (true);
	}

	private ICommand selectAnOption(Console console, LinkedList<ICommand> commands) {
		int choice = console.readInt(choicePrompt, 0, commands.size()-1);
		return commands.get(choice);
	}

	private void printCommandLineMenu(LinkedList<ICommand> commands) {
		commands.printAll();
		console.printString("\n" + separator);
	}

}
