package de.littleprogrammer.lpmcranks.commands;

import de.littleprogrammer.lpmcranks.CustomePlayer;
import de.littleprogrammer.lpmcranks.Rank;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class RankCommand implements CommandExecutor{

    private CustomePlayer customePlayer;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
        if (player.hasPermission("lpmcranks.admin")) {
            if (args.length == 2) {
            if (Bukkit.getOfflinePlayer(args[0]) != null){
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                try {
                    customePlayer = new CustomePlayer(target.getUniqueId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                for (Rank rank : Rank.values()){
                    if (rank.name().equalsIgnoreCase(args[1])){
                        try {
                            customePlayer.setRank(rank.name());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        player.sendMessage(ChatColor.GREEN + "Der Rang von " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " wurde zu " + rank.getDisplay() + ChatColor.GREEN + " geändert.");
                        player.playNote(player.getLocation(), Instrument.PLING, new Note(9));
                    }
                }
            }else {
                player.sendMessage(ChatColor.RED + "Der Spieler war nie mal zuvor auf diesem Server!");
            }

            } else {
                player.sendMessage(ChatColor.RED + "Benutzung /rank <player> <rank>");
            }
        } else {
            player.sendMessage(ChatColor.RED + "Du hast nicht die benötigten berechtigungen");
        }
        }
        return false;
    }
}
