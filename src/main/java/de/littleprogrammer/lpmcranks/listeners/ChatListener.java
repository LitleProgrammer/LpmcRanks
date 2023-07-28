package de.littleprogrammer.lpmcranks.listeners;

import de.littleprogrammer.lpmcranks.CustomePlayer;
import de.littleprogrammer.lpmcranks.Rank;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

import java.sql.SQLException;

public class ChatListener implements Listener {

    private CustomePlayer customePlayer;

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) throws SQLException {
       if (event.getPlayer().getServer().getName().startsWith("Vocab")){return;}

        Player player = event.getPlayer();
        customePlayer = new CustomePlayer(player.getUniqueId());

        event.setCancelled(true);

        if (!(customePlayer.getMute() == 1)) {

            for (Rank rank : Rank.values()) {
                if (rank.name().equalsIgnoreCase(customePlayer.getRank())) {
                    Bukkit.broadcastMessage(rank.getDisplay() + player.getName() + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + event.getMessage());

                }
            }
        } else {
            event.setCancelled(true);
        }
    }
}
