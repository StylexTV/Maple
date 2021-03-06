package de.stylextv.maple.world.scan.entity;

import java.util.ArrayList;
import java.util.List;

import de.stylextv.maple.context.PlayerContext;
import de.stylextv.maple.context.WorldContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;

public class EntityScanner {
	
	public static List<Entity> scanWorld() {
		EntityFilters filters = EntityFilters.fromFilter(EntityFilter.ALL);
		
		return scanWorld(filters);
	}
	
	public static List<Entity> scanWorld(EntityFilters filters) {
		List<Entity> entities = new ArrayList<>();
		
		ClientWorld world = WorldContext.world();
		
		ClientPlayerEntity p = PlayerContext.player();
		
		for(Entity e : world.getEntities()) {
			
			if(e.equals(p)) continue;
			
			if(filters.matches(e)) entities.add(e);
		}
		
		return entities;
	}
	
}
