package de.littleprogrammer.lpmcranks.listeners;

import de.littleprogrammer.lpmcranks.CustomePlayer;
import de.littleprogrammer.lpmcranks.Database;
import de.littleprogrammer.lpmcranks.Main;
import de.littleprogrammer.lpmcranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.sql.SQLException;

public class ConnectionListener implements Listener {

    private String normalRank = "SPIELER";



    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException, InterruptedException {

        Player player = event.getPlayer();
        PermissionAttachment attachment;
        CustomePlayer playerData = new CustomePlayer(player.getUniqueId());

            if (playerData.getRank() == null){
                playerData.setRank(normalRank);
            }else {
                for (String perm : Rank.valueOf(playerData.getRank()).getPermissions()){
                    if (playerData.getPerms().containsKey(player.getUniqueId())){
                        attachment = playerData.getPerms().get(player.getUniqueId());
                        attachment.setPermission(perm, true);
                    }else {
                        attachment = player.addAttachment(Main.getInstance());
                        playerData.getPerms().put(player.getUniqueId(), attachment);
                        attachment.setPermission(perm, true);
                    }
                }
        }

        /*try {

            if (!player.hasPlayedBefore()){
                playerData.setRank(normalRank, true);
            }else {
                if (playerData.getPerms().containsKey(player.getUniqueId())){
                    attachment = playerData.getPerms().get(player.getUniqueId());
                }else {
                    attachment = player.addAttachment(Main.getInstance());
                    playerData.getPerms().put(player.getUniqueId(), attachment);
                }
                if (playerData.getRank() != null){
                    if (Rank.valueOf(playerData.getRank()).getPermissions() != null){
                        for (String perm : Rank.valueOf(playerData.getRank()).getPermissions()){
                            attachment.setPermission(perm, true);
                            player.sendMessage(perm);
                        }
                    }else {
                        System.out.println(ChatColor.RED + "Rank.valueOf(playerData.getRank()).getPermissions() also returned null");
                    }
                }else {
                    System.out.println(ChatColor.RED + "playerData.getRank() returned null");
                }
            }
        } catch (SQLException e) {
            player.kickPlayer("Deine Daten konnten nicht geladen werden!\nBitte versuche es noch einmal,\nsollte dieser Fehler häufiger auftreten,\nkontaktiere bitte einen Admin");
        }


        /*try {



        } catch (SQLException e) {
            e.printStackTrace();
        }*/


        Main.getInstance().getNametagManager().setNametags(player);
        Main.getInstance().getNametagManager().newTag(player);

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) throws SQLException {

        Player player = event.getPlayer();
        CustomePlayer playerData = new CustomePlayer(player.getUniqueId());

        Main.getInstance().getNametagManager().removeTag(event.getPlayer());
        playerData.getPerms().remove(player.getUniqueId());


    }
}
