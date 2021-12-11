package pl.alpaq.lobby.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.configuration.InvalidConfigurationException;
import pl.alpaq.lobby.LobbyPlugin;
import pl.alpaq.lobby.flat.Messages;
import pl.alpaq.lobby.util.ChatUtil;
import pl.alpaq.lobby.util.TablistUtil;
import pl.alpaq.lobby.flat.ServerSelectorSettings;

import java.io.IOException;
import java.util.Arrays;

public class ConfigReloadCommand extends BukkitCommand {

    public ConfigReloadCommand(String name){
        super(name);
        this.setAliases(Arrays.asList("reloadconfig", "configreload"));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if(!sender.hasPermission("lobby.config")){
            sender.sendMessage(ChatUtil.fixColor(Messages.NO_PERMISSION_MESSAGE));
            return false;
        }

        Bukkit.getScheduler().runTaskAsynchronously(LobbyPlugin.getPlugin(), () -> {
            try {

                Messages.reload();
                ServerSelectorSettings.reload();
                Bukkit.getOnlinePlayers().forEach(TablistUtil::applyTab);

                sender.sendMessage(ChatUtil.fixColor("&aPomyslnie przeladowano pliki konfiguracyjne"));

            } catch (IOException | IllegalAccessException | InvalidConfigurationException e) {

                sender.sendMessage(ChatUtil.fixColor("&cWystapil blad podczas przeladowywania plikow konfiguracyjnych, sprawdz konsole!"));
                e.printStackTrace();

            }

        });


        return false;
    }
}
