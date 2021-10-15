package de.stylextv.maple.world.scan.block;

import java.util.function.Predicate;

import de.stylextv.maple.util.RegistryUtil;
import de.stylextv.maple.world.scan.block.filters.BlockTypeFilter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;

public class BlockFilter {
	
	public static final BlockFilter ALL = new BlockFilter((state) -> {
		return true;
	});
	
	public static final BlockFilter FULLY_GROWN = new BlockFilter((state) -> {
		Block block = state.getBlock();
		
		if(!(block instanceof CropBlock)) return true;
		
		CropBlock crop = (CropBlock) block;
		
		return crop.isMature(state);
	});
	
	private Predicate<BlockState> predicate;
	
	public BlockFilter() {
		this(null);
	}
	
	public BlockFilter(Predicate<BlockState> predicate) {
		this.predicate = predicate;
	}
	
	public boolean matches(BlockState state) {
		return predicate.test(state);
	}
	
	public static BlockFilter fromString(String s) {
		Block block = RegistryUtil.getBlock(s);
		
		if(block == null) return null;
		
		return new BlockTypeFilter(block);
	}
	
}
