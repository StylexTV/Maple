package de.stylextv.maple.pathing.movement.helper;

import de.stylextv.maple.event.events.RenderWorldEvent;
import de.stylextv.maple.input.target.BlockTarget;
import de.stylextv.maple.input.target.TargetList;
import de.stylextv.maple.pathing.calc.Node;
import de.stylextv.maple.pathing.movement.Movement;
import de.stylextv.maple.render.ShapeRenderer;
import de.stylextv.maple.scheme.Color;
import net.minecraft.util.math.BlockPos;

public abstract class TargetHelper<T extends BlockTarget> extends MovementHelper<Movement> {
	
	private TargetList<T> targets = new TargetList<>();
	
	public TargetHelper(Movement m) {
		super(m);
	}
	
	public void collectBlocks(Node n, int height) {
		collectBlocks(n, 0, height);
	}
	
	public void collectBlocks(Node n, int offset, int height) {
		int x = n.getX();
		int y = n.getY();
		int z = n.getZ();
		
		collectBlocks(x, y, z, offset, height);
	}
	
	public void collectBlocks(int x, int y, int z, int offset, int height) {
		for(int i = 0; i < height; i++) {
			
			collectBlock(x, y + offset + i, z);
		}
	}
	
	public void collectBlock(Node n) {
		collectBlock(n, 0);
	}
	
	public void collectBlock(Node n, int offset) {
		int x = n.getX();
		int y = n.getY() + offset;
		int z = n.getZ();
		
		collectBlock(x, y, z);
	}
	
	public abstract void collectBlock(int x, int y, int z);
	
	public void render(RenderWorldEvent event, Color color) {
		for(BlockTarget target : targets) {
			
			BlockPos pos = target.getPos();
			
			ShapeRenderer.drawBox(event, pos, color, 2);
		}
	}
	
	public void addTarget(T target) {
		targets.add(target);
	}
	
	public void removeTarget(T target) {
		targets.remove(target);
	}
	
	public boolean hasTarget(BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		return hasTarget(x, y, z);
	}
	
	public boolean hasTarget(int x, int y, int z) {
		return getTarget(x, y, z) != null;
	}
	
	public T getTarget(int x, int y, int z) {
		for(T target : targets) {
			
			BlockPos pos = target.getPos();
			
			if(pos.getX() == x && pos.getY() == y && pos.getZ() == z) return target;
		}
		
		return null;
	}
	
	public boolean hasTargets() {
		return !targets.isEmpty();
	}
	
	public TargetList<T> getTargets() {
		return targets;
	}
	
}
