package pl.alpaq.lobby.command;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import pl.alpaq.lobby.profile.LobbyProfile;
import pl.alpaq.lobby.profile.ProfileManager;
import pl.alpaq.lobby.util.ChatUtil;
import pl.alpaq.lobby.util.InventoryUtil;
import pl.alpaq.lobby.util.PlayerUtil;
import pl.alpaq.lobby.util.TitleAPI;
import pl.alpaq.lobby.flat.Messages;

import java.util.Collections;

public class BuildModeCommand extends BukkitCommand {

    public BuildModeCommand(String name){
        super(name);
        this.setAliases(Collections.singletonList("bud"));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("Komenda dostepna wylacznie dla graczy!");
            return false;
        }

        Player player = (Player)sender;

        if(!player.hasPermission("lobby.build")){
            player.sendMessage(ChatUtil.fixColor(Messages.NO_PERMISSION_MESSAGE));
            return false;
        }

        LobbyProfile profile = ProfileManager.getInstance().getProfile(player);
        profile.toggleBuildMode();

        PlayerUtil.reset(player);

        if(!profile.isBuildMode()){
            player.getInventory().setContents(InventoryUtil.getLobbyInventory());
            player.getInventory().setArmorContents(null);
        } else {
            player.setGameMode(GameMode.CREATIVE);
        }

        TitleAPI.sendTitle(player, Messages.TITLE_MAIN,
                profile.isBuildMode() ? Messages.BUILDMODE_ENABLED_SUBTITLE : Messages.BUILDMODE_DISABLED_SUBTITLE);


        return false;
    }
}
