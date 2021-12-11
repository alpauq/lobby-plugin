package pl.alpaq.lobby;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.alpaq.lobby.command.FlyCommand;
import pl.alpaq.lobby.command.BuildModeCommand;
import pl.alpaq.lobby.command.ConfigReloadCommand;
import pl.alpaq.lobby.flat.Messages;
import pl.alpaq.lobby.flat.ServerSelectorSettings;
import pl.alpaq.lobby.listener.InventoryClickListener;
import pl.alpaq.lobby.listener.PlayerInteractListener;
import pl.alpaq.lobby.listener.PlayerListener;
import pl.alpaq.lobby.profile.ProfileManager;
import pl.alpaq.lobby.server.ServerManager;
import pl.alpaq.lobby.task.ActionbarRunnable;

public class LobbyPlugin extends JavaPlugin {

    @Getter private static LobbyPlugin plugin;

    public void onEnable(){
        long startTime = System.currentTimeMillis();

        plugin = this;

        new ProfileManager();
        ServerManager serverManager = new ServerManager();

        this.loadConfiguration();
        this.loadListeners();
        this.loadCommands();
        this.loadTasks();

        serverManager.getServerSelectorGui().init();

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        long loadTime = System.currentTimeMillis() - startTime;
        getLogger().info("Zaladowano plugin w " + loadTime + "ms");
    }


    private void loadConfiguration(){
        new Messages();
        new ServerSelectorSettings();
    }

    private void loadListeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerListener(), this);
        pluginManager.registerEvents(new PlayerInteractListener(), this);
        pluginManager.registerEvents(new InventoryClickListener(), this);

    }

    private void loadCommands(){
        SimpleCommandMap commandMap = ((CraftServer)getServer()).getCommandMap();
        commandMap.register("build", new BuildModeCommand("build"));
        commandMap.register("config", new ConfigReloadCommand("config"));
        commandMap.register("fly", new FlyCommand("fly"));
    }

    private void loadTasks(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new ActionbarRunnable(), 0, 20);
    }


}
