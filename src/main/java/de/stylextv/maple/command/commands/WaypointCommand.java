package de.stylextv.maple.command.commands;

import java.util.ArrayList;
import java.util.List;

import de.stylextv.maple.command.ArgumentList;
import de.stylextv.maple.command.Command;
import de.stylextv.maple.context.PlayerContext;
import de.stylextv.maple.context.WorldContext;
import de.stylextv.maple.util.chat.ChatUtil;
import de.stylextv.maple.util.time.TimeFormat;
import de.stylextv.maple.util.world.CoordUtil;
import de.stylextv.maple.waypoint.Waypoint;
import de.stylextv.maple.waypoint.WaypointTag;
import de.stylextv.maple.waypoint.Waypoints;
import net.minecraft.util.math.BlockPos;

public class WaypointCommand extends Command {
	
	private static final String[] USAGES = new String[] {
			"create <name> [x y z]",
			"delete <name>",
			"list [page]",
			"info <name>",
			"goto <name>"
	};
	
	public WaypointCommand() {
		super("waypoint", "Used to create and travel to waypoints.", "wp");
	}
	
	@Override
	public boolean execute(ArgumentList args) {
		if(args.hasExactly(0)) return false;
		
		String s = args.get(0);
		
		if(s.equalsIgnoreCase("list")) {
			
			List<Waypoint> waypoints = Waypoints.getWaypoints();
			
			if(waypoints.isEmpty()) {
				ChatUtil.send("§cNo waypoints available!");
				
				return true;
			}
			
			int page = 1;
			
			if(args.hasAtLeast(2)) {
				
				page = args.getInt(1);
			}
			
			List<String> list = new ArrayList<>();
			
			for(Waypoint p : waypoints) {
				
				if(!p.isInWorld()) continue;
				
				String name = p.getDisplayName();
				
				list.add(name);
			}
			
			ChatUtil.send("Waypoints:");
			
			String command = getName() + " list";
			
			ChatUtil.sendList(list, 7, page, command);
			
			return true;
		}
		
		if(args.hasLessThan(2)) return false;
		
		String name = args.get(1);
		
		String levelName = WorldContext.getLevelName();
		
		Waypoint p = Waypoints.getWaypointByName(name, levelName);
		
		if(s.equalsIgnoreCase("create")) {
			
			if(p != null) {
				ChatUtil.send("§cThat name is already in use!");
				
				return true;
			}
			
			BlockPos pos = PlayerContext.feetPosition();
			
			if(args.hasMoreThan(2)) {
				
				int x = args.getCoordinate(2, 0);
				int y = args.getCoordinate(3, 1);
				int z = args.getCoordinate(4, 2);
				
				pos = new BlockPos(x, y, z);
			}
			
			boolean b = Waypoints.addWaypoint(name, WaypointTag.USER, levelName, pos);
			
			if(b) {
				ChatUtil.send("Waypoint §o" + name + " §7added.");
				
				return true;
			}
			
			ChatUtil.send("§cA waypoint already exists at this position.");
			
			return true;
		}
		
		if(p == null) {
			ChatUtil.send("§cWaypoint not found!");
			
			return true;
		}
		
		name = p.getName();
		
		if(s.equalsIgnoreCase("info")) {
			
			double dis = p.distance();
			
			String s2 = CoordUtil.formatDistance(dis);
			
			String s3 = String.format("This waypoint is §o%s§7 away.", s2);
			
			String timeStamp = TimeFormat.formatDate(p.getTimeStamp());
			
			String s4 = "Tag: " + p.getTag();
			String s5 = "Timestamp: " + timeStamp;
			
			ChatUtil.send(name + ":", s3, "", s4, s5);
			
			return true;
		}
		
		if(s.equalsIgnoreCase("delete")) {
			
			Waypoints.removeWaypoint(p);
			
			ChatUtil.send("Waypoint §o" + name + " §7removed.");
			
			return true;
		}
		
		if(s.equalsIgnoreCase("goto")) {
			
			Waypoints.gotoWaypoint(p);
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public String[] getUsages() {
		return USAGES;
	}
	
}
