package budgetapp.commands;

import budgetapp.commands.impl.*;
import budgetapp.commands.impl.search.*;
import budgetapp.commands.impl.sort.*;
import budgetapp.linkedlist.LinkedList;

public class CommandFactory {

	public static LinkedList<ICommand> returnCommands() {
		LinkedList<ICommand> commands = new LinkedList<>();
		commands.add(new ShowCommand());
		commands.add(new AddCommand());
		commands.add(new RemoveCommand());
		commands.add(new ClearCommand());
		commands.add(new EditCommand());
		commands.add(new SortCommand());
		commands.add(new SearchCommand());
		commands.add(new StatisticsCommand());
		commands.add(new SaveCommand());
		commands.add(new ExitCommand());
		return commands;
	}
	public static LinkedList<ICommand> returnSortCommands() {
		LinkedList<ICommand> commands = new LinkedList<>();
		commands.add(new SortAmountCommand());
		commands.add(new SortDateCommand());
		commands.add(new SortNameCommand());
		commands.add(new SortCategoryCommand());
		return commands;
	}
	public static LinkedList<ICommand> returnSearchCommands() {
		LinkedList<ICommand> commands = new LinkedList<>();
		commands.add(new SearchNameCommand());
		commands.add(new SearchCategoryCommand());
		commands.add(new SearchDateCommand());
		return commands;
	}
}
