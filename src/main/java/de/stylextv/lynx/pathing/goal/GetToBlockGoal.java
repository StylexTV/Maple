package de.stylextv.lynx.pathing.goal;

import de.stylextv.lynx.pathing.Cost;
import de.stylextv.lynx.pathing.Node;
import net.minecraft.util.math.BlockPos;

public class GetToBlockGoal extends Goal {
	
	private BlockPos pos;
	
	public GetToBlockGoal(BlockPos pos) {
		this.pos = pos;
	}
	
	@Override
	public int heuristic(Node n) {
		int disX = Math.abs(n.getX() - pos.getX());
		int disY = Math.abs(n.getY() - pos.getY());
		int disZ = Math.abs(n.getZ() - pos.getZ());
		
		int dis = disX + disY + disZ;
		
		return dis * Cost.COST_PER_UNIT;
	}
	
	@Override
	public boolean isFinalNode(Node n) {
		int disX = Math.abs(n.getX() - pos.getX());
		int disY = Math.abs(n.getY() - pos.getY());
		int disZ = Math.abs(n.getZ() - pos.getZ());
		
		int dis = disX + disY + disZ;
		
		return dis < 3;
	}
	
	@Override
	public String toString() {
		return "GetToBlockGoal{pos=" + pos + "}";
	}
	
}