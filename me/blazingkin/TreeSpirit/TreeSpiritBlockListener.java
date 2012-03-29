package me.blazingkin.TreeSpirit;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;


public class TreeSpiritBlockListener extends BlockListener {
	TreeSpirit Main;
	public TreeSpiritBlockListener(TreeSpirit mn){
		Main = mn;
	}
	
	public void onBlockPlace(BlockPlaceEvent event){
		if (event.getBlock().getTypeId() == 17){
		
			int playerid = 0;
			boolean flag = false;
			for (int i = 0; i < Main.playingPlayers.size(); i++){
				if (Main.playingPlayers.get(i).getDisplayName().equals(event.getPlayer().getDisplayName())){
					flag = true;
					playerid = i;
				}
			}
			if (!flag){
				return;
			}
				HashMap<Integer, Block> hm = Main.blocks.get(playerid);
				int bPos = event.getBlock().getX() + event.getBlock().getZ() + event.getBlock().getY()/3;
				for (int b = 0; b < hm.size(); b++){
				int obPos = hm.get(b).getX() + hm.get(b).getY()/3 + hm.get(b).getZ();
					if (Math.abs(obPos - bPos) < 2){
							flag = false;
					}else{
						event.setCancelled(true);
					}
				}
			
			if (flag){
				event.setCancelled(true);
			}else{
				event.setCancelled(false);
				Main.blocks.get(playerid).put(Main.blocks.get(playerid).size(), event.getBlock());
			}
			
		
		}
		
	}
	
	
	public void onBlockBreak(BlockBreakEvent event){
		if (event.getBlock().getTypeId() == 17){
			for (int i = 0; i < Main.choosingTreePlayers.size(); i++){
				if (Main.choosingTreePlayers.get(i).getDisplayName().equals(event.getPlayer().getDisplayName())){
					event.getPlayer().sendMessage("Block Chosen");
					Main.choosingTreePlayers.remove(i);
					int z = Main.blocks.size();
					Main.blocks.put(z, new HashMap<Integer, Block>());
					Main.playingPlayers.put(z, event.getPlayer());
					Main.blocks.get(z).put(0, event.getBlock());
					//TODO uncomment
					//event.getPlayer().getInventory().clear();
					event.setCancelled(true);
					return;
				}
				
			}
		}
		
		for (int i = 0; i < Main.playingPlayers.size(); i++){
			if (Main.playingPlayers.get(i).getDisplayName().equals(event.getPlayer().getDisplayName())){
				if (Main.blocks.get(i).get(0).getLocation().equals(event.getBlock().getLocation())){
					event.setCancelled(true);
					event.getPlayer().sendMessage("You can't break your own heart block!");
					return;
				}
				
				ItemStack is = new ItemStack(event.getBlock().getType(), 1, event.getBlock().getData());
				event.getPlayer().getInventory().addItem(is);
				event.getBlock().setTypeId(0);
				for (int b = 0; b < Main.blocks.get(i).size(); b++){
					if (Main.blocks.get(i).get(b).equals(event.getBlock())){
						//Main.blocks.get(i).remove(b);
						break;
					}
				}
				
			}
		}
	
		
		
	}
	
}
