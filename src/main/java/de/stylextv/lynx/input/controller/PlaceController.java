package de.stylextv.lynx.input.controller;

import de.stylextv.lynx.context.GameContext;
import de.stylextv.lynx.input.InputAction;
import de.stylextv.lynx.mixin.MinecraftClientInvoker;
import de.stylextv.lynx.util.world.Offset;
import net.minecraft.util.math.BlockPos;

public class PlaceController {
	
	public static boolean canPlaceAt(BlockPos pos) {
		for(Offset o : Offset.DIRECT_BLOCK_NEIGHBOURS) {
			
			int x = pos.getX() + o.getBlockX();
			int y = pos.getY() + o.getBlockY();
			int z = pos.getZ() + o.getBlockZ();
			
			if(canPlaceAgainst(x, y, z)) return true;
		}
		
		return false;
	}
	
	public static boolean canPlaceAgainst(int x, int y, int z) {
		return BreakController.isBreakable(x, y, z);
	}
	
	public static void onTick() {
		boolean b = InputController.isPressed(InputAction.RIGHT_CLICK);
		
		if(!b) return;
		
		InputController.setPressed(InputAction.RIGHT_CLICK, false);
		
		MinecraftClientInvoker invoker = GameContext.clientInvoker();
		
		invoker.invokeDoItemUse();
	}
	
}
