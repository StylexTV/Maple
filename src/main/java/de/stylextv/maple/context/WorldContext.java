package de.stylextv.maple.context;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.dimension.DimensionType;

public class WorldContext {
	
	private static final MinecraftClient CLIENT = MinecraftClient.getInstance();
	
	public static ClientWorld world() {
		return CLIENT.world;
	}
	
	public static DimensionType dimension() {
		return world().getDimension();
	}
	
	public static String getLevelName() {
		String name = getWorldName() + "/" + getDimensionName();
		
		return name;
	}
	
	public static String getDimensionName() {
		RegistryKey<World> key = world().getRegistryKey();
		
		Identifier identifier = key.getValue();
		
		return identifier.getPath();
	}
	
	public static String getWorldName() {
		if(GameContext.isInSinglePlayer()) {
			
			IntegratedServer server = CLIENT.getServer();
			
			Optional<Path> optional = server.getIconFile();
			
			Path path = optional.get();
			
			File f = path.toFile();
			
			f = f.getParentFile();
			
			return "local/" + f.getName();
			
		} else {
			
			ServerInfo info = CLIENT.getCurrentServerEntry();
			
			return "remote/" + info.address;
		}
	}
	
	public static boolean isInWorld() {
		return PlayerContext.player() != null && world() != null;
	}
	
	public static int getHeight() {
		return world().getHeight();
	}
	
	public static int getTopY() {
		return world().getTopY();
	}
	
	public static int getBottomY() {
		return world().getBottomY();
	}
	
	public static boolean isOutOfHeightLimit(BlockPos pos) {
		return world().isOutOfHeightLimit(pos);
	}
	
	public static boolean isOutOfHeightLimit(int y) {
		return world().isOutOfHeightLimit(y);
	}
	
	public static Block getBlock(BlockPos pos) {
		BlockState state = getBlockState(pos);
		
		return state.getBlock();
	}
	
	public static BlockState getBlockState(BlockPos pos) {
		return world().getBlockState(pos);
	}
	
	public static WorldChunk getChunk(int chunkX, int chunkZ) {
		return world().getChunk(chunkX, chunkZ);
	}
	
	public static boolean isPosFullyLoaded(BlockPos pos) {
		WorldChunk chunk = world().getWorldChunk(pos);
		
		return chunk != null && !chunk.isEmpty();
	}
	
	public static boolean isPosLoaded(BlockPos pos) {
		int x = pos.getX();
		int z = pos.getZ();
		
		return isPosLoaded(x, z);
	}
	
	@SuppressWarnings("deprecation")
	public static boolean isPosLoaded(int x, int z) {
		return world().isPosLoaded(x, z);
	}
	
	public static boolean isChunkLoaded(int chunkX, int chunkZ) {
		return world().isChunkLoaded(chunkX, chunkZ);
	}
	
}
