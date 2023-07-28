package de.littleprogrammer.lpmcranks.tabCompleters;

import de.littleprogrammer.lpmcranks.CustomePlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RankTab implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            try {
                CustomePlayer customePlayer = new CustomePlayer(player.getUniqueId());

                if (customePlayer.getRank().equalsIgnoreCase("ADMIN") || customePlayer.getRank().equalsIgnoreCase("MOD") || customePlayer.getRank().equalsIgnoreCase("DEV")) {
                    if (args.length == 1) {
                        List<String> names = new ArrayList<>();

                        for (Player player1 : Bukkit.getOnlinePlayers()) {
                            names.add(player1.getName());
                        }
                        return StringUtil.copyPartialMatches(args[0], names, new ArrayList<>());
                    } else if (args.length == 2) {
                        return StringUtil.copyPartialMatches(args[1], Arrays.asList("Admin", "Mod", "Content", "Developer", "Youtuber", "Supporter", "Spieler"), new ArrayList<>());
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

}
