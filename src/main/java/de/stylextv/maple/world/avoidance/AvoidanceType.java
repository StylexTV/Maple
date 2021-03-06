package de.stylextv.maple.world.avoidance;

import de.stylextv.maple.context.PlayerContext;
import de.stylextv.maple.util.math.MathUtil;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.Monster;

public class AvoidanceType {
	
	public static final AvoidanceType WEAK = new AvoidanceType(6, 1.2);
	
	public static final AvoidanceType NORMAL = new AvoidanceType(8, 1.5);
	
	public static final AvoidanceType STRONG = new AvoidanceType(10, 1.9);
	
	public static final AvoidanceType BEAST = new AvoidanceType(12, 2.5);
	
	private static final AvoidanceType[] TYPES = new AvoidanceType[] {
			WEAK, NORMAL, STRONG, BEAST
	};
	
	private static final double LOG_TWO_OF_TEN = MathUtil.log2(10);
	
	private int radius;
	
	private double coefficient;
	
	public AvoidanceType(int radius, double coefficient) {
		this.radius = radius;
		this.coefficient = coefficient;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public double getCoefficient() {
		return coefficient;
	}
	
	public static AvoidanceType fromEntity(Entity e) {
		if(!(e instanceof LivingEntity)) return null;
		
		LivingEntity entity = (LivingEntity) e;
		
		if(isAggressive(entity)) {
			
			if(AvoidanceFilter.shouldIgnore(e)) return null;
			
			float health = entity.getMaxHealth();
			
			return fromHealth(health);
		}
		
		return null;
	}
	
	private static boolean isAggressive(LivingEntity e) {
		if(e instanceof Monster) return true;
		
		ClientPlayerEntity p = PlayerContext.player();
		
		LivingEntity adversary = e.getPrimeAdversary();
		
		return p.equals(adversary);
	}
	
	public static AvoidanceType fromHealth(float health) {
		double d = MathUtil.log2(health);
		
		int i = (int) Math.round(d - LOG_TWO_OF_TEN);
		
		int max = TYPES.length - 1;
		
		i = MathUtil.clamp(i, 0, max);
		
		return TYPES[i];
	}
	
}
