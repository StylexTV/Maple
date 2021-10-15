package de.stylextv.maple.task.tasks;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import de.stylextv.maple.input.target.BreakableTarget;
import de.stylextv.maple.pathing.calc.goal.CompositeGoal;
import de.stylextv.maple.util.async.AsyncUtil;
import de.stylextv.maple.world.scan.block.BlockFilter;
import de.stylextv.maple.world.scan.block.BlockScanner;
import net.minecraft.util.math.BlockPos;

public abstract class BreakTask extends CompositeTask {
	
	private BlockFilter[] filters;
	
	private Set<BreakableTarget> targets = ConcurrentHashMap.newKeySet();
	
	private boolean scanned;
	
	private boolean scanning;
	
	public BreakTask(BlockFilter... filters) {
		this.filters = filters;
	}
	
	@Override
	public CompositeGoal onTick() {
		rescan();
		
		for(BreakableTarget target : targets) {
			
			if(target.isBroken()) {
				
				removeTarget(target);
				
				continue;
			}
			
			if(target.isInReach()) {
				
				target.continueBreaking();
				
				return null;
			}
		}
		
		if(targets.isEmpty() && !scanned) return null;
		
		return CompositeGoal.fromTargets(targets);
	}
	
	private void rescan() {
		if(scanning) return;
		
		scanning = true;
		
		AsyncUtil.runAsync(() -> {
			
			List<BlockPos> positions = BlockScanner.scanWorld(filters);
			
			for(BlockPos pos : positions) {
				
				if(hasTarget(pos)) continue;
				
				addTarget(pos);
			}
			
			scanned = true;
			
			scanning = false;
		});
	}
	
	public void addTarget(BlockPos pos) {
		BreakableTarget target = new BreakableTarget(pos);
		
		targets.add(target);
	}
	
	public void removeTarget(BreakableTarget target) {
		targets.remove(target);
	}
	
	public boolean hasTarget(BlockPos pos) {
		for(BreakableTarget target : targets) {
			
			BlockPos p = target.getPos();
			
			if(p.equals(pos)) return true;
		}
		
		return false;
	}
	
	public BlockFilter[] getFilters() {
		return filters;
	}
	
	public Set<BreakableTarget> getTargets() {
		return targets;
	}
	
}
