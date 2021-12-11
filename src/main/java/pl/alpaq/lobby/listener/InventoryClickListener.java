package pl.alpaq.lobby.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import pl.alpaq.lobby.profile.LobbyProfile;
import pl.alpaq.lobby.profile.ProfileManager;
import pl.alpaq.lobby.server.ServerManager;
import pl.alpaq.lobby.server.ServerProfile;
import pl.alpaq.lobby.util.BungeeUtil;
import pl.alpaq.lobby.util.ChatUtil;
import pl.alpaq.lobby.flat.Messages;
import pl.alpaq.lobby.util.TitleAPI;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){

        Player player = (Player)event.getWhoClicked();
        LobbyProfile profile = ProfileManager.getInstance().getProfile(player);

        if(profile.isBuildMode())return;

        event.setCancelled(true);

        if(event.getClickedInventory() == null)return;
        if(event.getClickedInventory().getType().equals(InventoryType.PLAYER))return;
        if(!ChatUtil.fixColor(Messages.SERVER_SELECTOR_NAME).equalsIgnoreCase(event.getView().getTitle()))return;

        int slot = event.getSlot();

        ServerProfile serverProfile = ServerManager.getInstance().getServerBySlot(slot);

        if(serverProfile == null)return;

        player.closeInventory();
        TitleAPI.sendTitle(player,10, 300, 10,
                Messages.TITLE_MAIN, Messages.SERVER_CONNECTING_SUBTITLE.replace("{SERVER}", serverProfile.getName()));

        String server = serverProfile.getBungeeName();
        BungeeUtil.connectToServer(player, server);



    }



}
