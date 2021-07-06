package de.stylextv.lynx.task;

import de.stylextv.lynx.input.PlayerContext;
import de.stylextv.lynx.input.controller.MovementController;
import de.stylextv.lynx.pathing.Path;
import de.stylextv.lynx.pathing.PathFinder;
import de.stylextv.lynx.pathing.goal.Goal;

public class MoveTask extends Task {
	
	private Goal goal;
	
	public MoveTask(Goal goal) {
		this.goal = goal;
	}
	
	@Override
	public void start() {
		PathFinder finder = new PathFinder(goal);
		
		Path path = finder.find(PlayerContext.blockPosition());
		
		MovementController.followPath(path);
	}
	
	@Override
	public void stop() {
		MovementController.stop();
	}
	
	@Override
	public boolean run() {
		return !MovementController.isMoving();
	}
	
}