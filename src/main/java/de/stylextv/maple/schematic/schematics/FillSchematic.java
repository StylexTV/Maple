package de.stylextv.maple.schematic.schematics;

import de.stylextv.maple.schematic.Schematic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

public class FillSchematic extends Schematic {
	
	private Block block;
	
	public FillSchematic(int width, int height, int length, Block block) {
		super(width, height, length);
		
		this.block = block;
	}
	
	@Override
	public Block getBlock(int x, int y, int z, BlockState state) {
		return block;
	}
	
	public Block getBlock() {
		return block;
	}
	
}
