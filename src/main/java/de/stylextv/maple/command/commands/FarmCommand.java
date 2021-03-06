package de.stylextv.maple.command.commands;

import de.stylextv.maple.command.ArgumentList;
import de.stylextv.maple.command.Command;
import de.stylextv.maple.task.Task;
import de.stylextv.maple.task.TaskManager;
import de.stylextv.maple.task.tasks.FarmTask;
import de.stylextv.maple.util.chat.ChatUtil;

public class FarmCommand extends Command {
	
	public FarmCommand() {
		super("farm", "Starts harvesting nearby crops.");
	}
	
	@Override
	public boolean execute(ArgumentList args) {
		Task task = new FarmTask();
		
		TaskManager.startTask(task);
		
		ChatUtil.send("Started farming.");
		
		return true;
	}
	
}
