package pl.alpaq.lobby.profile;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.alpaq.lobby.util.TablistUtil;

import java.util.*;

public class ProfileManager {

    @Getter private Map<UUID, LobbyProfile> lobbyProfileMap;
    @Getter private static ProfileManager instance;

    public ProfileManager(){
        instance = this;
        this.lobbyProfileMap = new HashMap<>();
        Bukkit.getOnlinePlayers().forEach(player -> {
            this.createProfile(player);
            TablistUtil.applyTab(player);
        });
    }

    public void createProfile(Player player){
        LobbyProfile lobbyProfile = new LobbyProfile(player);
        this.lobbyProfileMap.put(player.getUniqueId(), lobbyProfile);
    }

    public LobbyProfile getProfile(Player player){
        return this.lobbyProfileMap.get(player.getUniqueId());
    }



}
