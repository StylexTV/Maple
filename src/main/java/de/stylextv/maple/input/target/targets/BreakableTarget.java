package de.stylextv.maple.input.target.targets;

import de.stylextv.maple.gui.ToolSet;
import de.stylextv.maple.input.InputAction;
import de.stylextv.maple.input.controller.AwarenessController;
import de.stylextv.maple.input.controller.BreakController;
import de.stylextv.maple.input.controller.GuiController;
import de.stylextv.maple.input.controller.InputController;
import de.stylextv.maple.input.target.BlockTarget;
import de.stylextv.maple.world.BlockInterface;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class BreakableTarget extends BlockTarget {
	
	public BreakableTarget(int x, int y, int z) {
		super(x, y, z);
	}
	
	public BreakableTarget(BlockPos pos) {
		super(pos);
	}
	
	public boolean continueBreaking(boolean onlyIfSafe) {
		boolean unsafe = onlyIfSafe && !AwarenessController.isSafeToBreak(getPos());
		
		if(unsafe || !lookAt(false)) return false;
		
		BlockPos pos = AwarenessController.getTargetedPos();
		
		if(pos == null) return true;
		
		BlockState state = BlockInterface.getState(pos);
		
		ToolSet tools = ToolSet.getTools();
		
		ItemStack stack = tools.getBestTool(state);
		
		GuiController.selectItem(stack);
		
		InputController.setPressed(InputAction.LEFT_CLICK, true);
		
		return true;
	}
	
	public boolean isBroken() {
		BlockPos pos = getPos();
		
		return !BreakController.isBreakable(pos);
	}
	
}
