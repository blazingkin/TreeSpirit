package me.blazingkin.TreeSpirit;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TreeSpirit extends JavaPlugin {
    private final TreeSpiritPlayerListener playerListener = new TreeSpiritPlayerListener(this);
    private final TreeSpiritBlockListener blockListener = new TreeSpiritBlockListener(this);
	public void onEnable(){
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_PLACE, blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Event.Priority.Normal, this);
		blocks = new HashMap<Integer, HashMap<Integer, Block>>();
		playingPlayers = new HashMap<Integer, Player>();
		choosingTreePlayers = new HashMap<Integer, Player>();
	}
	
	
	
	
	public void onDisable(){
		Logger log = Logger.getLogger("Minecraft");
		log.warning("The TreeSpirit Plugin is now disabled! Watch out for cheating players!");
	}
	
	public HashMap<Integer, Player> playingPlayers;
	public HashMap<Integer, Player> choosingTreePlayers;
	public HashMap<Integer, HashMap<Integer, Block>> blocks;
}
