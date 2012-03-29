package me.blazingkin.TreeSpirit;

import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TreeSpiritPlayerListener extends PlayerListener {
TreeSpirit Main;
	public TreeSpiritPlayerListener(TreeSpirit mn){
		Main = mn;
	}
	
	public void onPlayerMove(PlayerMoveEvent event){
		
	}
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event){

		if (event.getMessage().toLowerCase().equals("/starttreespirit")){
			for (int i = 0; i < Main.playingPlayers.size(); i++){
				if (Main.playingPlayers.get(i).equals(event.getPlayer())){
					event.setCancelled(true);
					event.getPlayer().sendMessage("You already are doing the tree spirit challenge!");
					return;
				}
			}
			event.getPlayer().sendMessage("Please break a block to set it as your heart block");
			Main.choosingTreePlayers.put(Main.choosingTreePlayers.size(), event.getPlayer());
			event.setCancelled(true);
			
		}
	}
	
}
