package pl.alpaq.lobby.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import pl.alpaq.lobby.flat.Messages;
import pl.alpaq.lobby.profile.LobbyProfile;
import pl.alpaq.lobby.profile.ProfileManager;
import pl.alpaq.lobby.util.TitleAPI;

public class FlyCommand extends BukkitCommand {

    public FlyCommand(String name){
        super(name);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("Komenda dostepna tylko dla graczy");
            return false;
        }

        Player player = (Player)sender;
        LobbyProfile profile = ProfileManager.getInstance().getProfile(player);

        if(!player.hasPermission("lobby.fly")){
            TitleAPI.sendTitle(player, Messages.TITLE_MAIN, Messages.FLY_NO_PERMISSION_SUBTITLE);
            return false;
        }

        profile.toggleFlying();
        TitleAPI.sendTitle(player, Messages.TITLE_MAIN,
                profile.isFlying() ? Messages.FLY_ENABLED_SUBTITLE : Messages.FLY_DISABLED_SUBTITLE);



        return false;
    }

}
