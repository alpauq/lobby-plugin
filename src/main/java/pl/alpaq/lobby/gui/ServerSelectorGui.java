package pl.alpaq.lobby.gui;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.alpaq.lobby.flat.Messages;
import pl.alpaq.lobby.server.ServerManager;
import pl.alpaq.lobby.server.ServerProfile;
import pl.alpaq.lobby.util.ChatUtil;
import pl.alpaq.lobby.util.ItemBuilder;

public class ServerSelectorGui {

    @Getter private Inventory inventory;
    private ServerManager serverManager;

    public ServerSelectorGui(ServerManager serverManager){
        this.serverManager = serverManager;
        this.inventory = Bukkit.createInventory(null, 27, ChatUtil.fixColor(Messages.SERVER_SELECTOR_NAME));

        ItemStack whitePane = new ItemBuilder(Material.STAINED_GLASS_PANE, "").getItem();
        ItemStack greyPane = new ItemBuilder(Material.STAINED_GLASS_PANE, "", (short)8).getItem();

        for(int i = 0; i < 27; i++) {
            if (i%2==0) { this.inventory.setItem(i, whitePane); }
            else { this.inventory.setItem(i, greyPane); }
        }
    }

    public void init(){
        if(serverManager.getServerProfileList().isEmpty())return;
        for(ServerProfile serverProfile : serverManager.getServerProfileList()) {
            int slot = serverProfile.getInventorySlot();
            ItemStack item = serverProfile.getInventoryItem();
            this.inventory.setItem(slot, item);
        }
    }



}
