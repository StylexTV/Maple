package de.stylextv.maple.task.tasks;

import de.stylextv.maple.util.chat.ChatUtil;
import de.stylextv.maple.world.scan.block.BlockFilter;

public class MineTask extends BreakTask {
	
	public MineTask(BlockFilter filter) {
		super(filter);
	}
	
	@Override
	public void onFail() {
		ChatUtil.send("Can't get any closer to block.");
	}
	
	@Override
	public void onComplete() {
		ChatUtil.send("Can't find any matching blocks nearby.");
	}
	
}
