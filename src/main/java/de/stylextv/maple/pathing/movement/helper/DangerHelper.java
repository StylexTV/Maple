package de.stylextv.maple.pathing.movement.helper;

import de.stylextv.maple.cache.CacheManager;
import de.stylextv.maple.cache.block.BlockType;
import de.stylextv.maple.pathing.calc.Cost;
import de.stylextv.maple.pathing.calc.Node;
import de.stylextv.maple.pathing.movement.Movement;

public class DangerHelper extends MovementHelper<Movement> {
	
	public DangerHelper(Movement m) {
		super(m);
	}
	
	@Override
	public double cost() {
		Node source = getSource();
		Node destination = getDestination();
		
		if(isNearDanger(destination)) return Cost.INFINITY;
		
		int x = destination.getX();
		int y = destination.getY();
		int z = destination.getZ();
		
		BlockType type = CacheManager.getBlockType(x, y + 1, z);
		
		if(type == BlockType.WATER) return Cost.INFINITY;
		
		Movement m = getMovement();
		
		if(!m.isDiagonal()) return 0;
		
		int x2 = source.getX();
		int z2 = source.getZ();
		
		int y2 = Math.max(source.getY(), destination.getY());
		
		boolean b = isNearDanger(x, y2, z2) || isNearDanger(x2, y2, z);
		
		return b ? Cost.INFINITY : 0;
	}
	
	private static boolean isNearDanger(Node n) {
		int x = n.getX();
		int y = n.getY();
		int z = n.getZ();
		
		return isNearDanger(x, y, z);
	}
	
	private static boolean isNearDanger(int x, int y, int z) {
		return isDangerous(x, y, z, -1, 3);
	}
	
	private static boolean isDangerous(int x, int y, int z, int offset, int height) {
		for(int i = 0; i < height; i++) {
			
			if(isDangerous(x, y + offset + i, z)) return true;
		}
		
		return false;
	}
	
	private static boolean isDangerous(int x, int y, int z) {
		BlockType type = CacheManager.getBlockType(x, y, z);
		
		return type == BlockType.DANGER;
	}
	
}
