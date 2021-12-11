package pl.alpaq.lobby.task;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import pl.alpaq.lobby.flat.Messages;
import pl.alpaq.lobby.util.ChatUtil;
import pl.alpaq.lobby.util.TitleAPI;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionbarRunnable extends BukkitRunnable {

    @Override
    public void run() {
        String date = new SimpleDateFormat("HH:mm:ss").format(new Date());
        Bukkit.getOnlinePlayers().forEach(player ->
                TitleAPI.sendActionBar(player, ChatUtil.fixColor(Messages.ACTIONBAR_MESSAGE.replace("{HOUR}", date))));

    }

}
