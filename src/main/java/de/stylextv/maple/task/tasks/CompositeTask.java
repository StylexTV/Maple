package de.stylextv.maple.task.tasks;

import de.stylextv.maple.pathing.PathingCommand;
import de.stylextv.maple.pathing.PathingCommandType;
import de.stylextv.maple.pathing.PathingStatus;
import de.stylextv.maple.pathing.calc.goal.CompositeGoal;
import de.stylextv.maple.task.Task;

public abstract class CompositeTask extends Task {
	
	private boolean completeAtGoal;
	
	public CompositeTask(boolean completeAtGoal) {
		this.completeAtGoal = completeAtGoal;
	}
	
	public abstract CompositeGoal onRenderTick();
	
	public void onComplete() {}
	
	public abstract void onFail();
	
	public abstract void onEmptyGoal();
	
	@Override
	public PathingCommand onRenderTick(PathingStatus status) {
		CompositeGoal goal = onRenderTick();
		
		if(goal == null) return PathingCommand.PAUSE;
		
		boolean empty = goal.isEmpty();
		
		if(empty) {
			
			onEmptyGoal();
			
			return super.onRenderTick(status);
		}
		
		boolean atGoal = status.isAtGoal() && status.destinationMatches(goal);
		
		if(atGoal) {
			
			if(!completeAtGoal) return PathingCommand.DEFER;
			
			onComplete();
			
			return super.onRenderTick(status);
		}
		
		if(!status.goalMatches(goal)) return new PathingCommand(PathingCommandType.REVALIDATE_GOAL, goal);
		
		if(status.isPathing()) return PathingCommand.DEFER;
		
		onFail();
		
		return super.onRenderTick(status);
	}
	
	public boolean isCompleteAtGoal() {
		return completeAtGoal;
	}
	
}
