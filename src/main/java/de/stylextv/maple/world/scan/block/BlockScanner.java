package de.stylextv.maple.world.scan.block;

import java.util.ArrayList;
import java.util.List;

import de.stylextv.maple.context.PlayerContext;
import de.stylextv.maple.context.WorldContext;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.WorldChunk;

public class BlockScanner {
	
	private static final int SCAN_DISTANCE = 32;
	
	private static final int SCAN_LIMIT = 128;
	
	public static List<BlockPos> scanWorld(BlockFilter filter) {
		return scanWorld(filter, SCAN_LIMIT);
	}
	
	// TODO search in spiral pattern
	public static List<BlockPos> scanWorld(BlockFilter filter, int limit) {
		List<BlockPos> positions = new ArrayList<>();
		
		ChunkPos pos = PlayerContext.chunkPosition();
		
		int centerX = pos.x;
		int centerZ = pos.z;
		
		int r = SCAN_DISTANCE;
		
		for(int x = -r; x <= r; x++) {
			for(int z = -r; z <= r; z++) {
				
				int cx = centerX + x;
				int cz = centerZ + z;
				
				boolean failed = scanChunk(cx, cz, filter, limit, positions);
				
				if(failed) break;
			}
		}
		
		return positions;
	}
	
	private static boolean scanChunk(int chunkX, int chunkZ, BlockFilter filter, int limit, List<BlockPos> positions) {
		if(!WorldContext.isChunkLoaded(chunkX, chunkZ)) return true;
		
		WorldChunk chunk = WorldContext.getChunk(chunkX, chunkZ);
		
		int height = chunk.getHeight();
		
		int bottomY = chunk.getBottomY();
		
		ChunkPos pos = chunk.getPos();
		
		int startX = pos.getStartX();
		int startZ = pos.getStartZ();
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < 16; x++) {
				for(int z = 0; z < 16; z++) {
					
					int bx = startX + x;
					int by = bottomY + y;
					int bz = startZ + z;
					
					BlockPos p = new BlockPos(bx, by, bz);
					
					BlockState state = chunk.getBlockState(p);
					
					boolean matches = filter.matches(state);
					
					if(matches) {
						
						positions.add(p);
						
						int l = positions.size();
						
						if(l == limit) return true;
					}
				}
			}
		}
		
		return false;
	}
	
}