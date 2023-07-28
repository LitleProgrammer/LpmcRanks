package de.littleprogrammer.lpmcranks.managers;

import de.littleprogrammer.lpmcranks.CustomePlayer;
import de.littleprogrammer.lpmcranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.sql.SQLException;


public class NametagManager {

    private CustomePlayer customePlayer;

    public void setNametags(Player player) throws SQLException {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        for (Rank rank : Rank.values()){
            Team team = player.getScoreboard().registerNewTeam(rank.name());
            team.setPrefix(rank.getDisplay());
        }

        for (Player target : Bukkit.getOnlinePlayers()){
            customePlayer = new CustomePlayer(target.getUniqueId());
            for (Rank rank : Rank.values()){
                if (rank.name().equalsIgnoreCase(customePlayer.getRank())){
                    player.getScoreboard().getTeam(rank.name()).addEntry(target.getName());
                }
            }
        }
    }

    public void newTag(Player player){
        for (Player target : Bukkit.getOnlinePlayers()){
            for (Rank rank : Rank.values()){
                if (rank.name().equalsIgnoreCase(customePlayer.getRank())){
                    if (player.getUniqueId() != target.getUniqueId()){
                        target.getScoreboard().getTeam(rank.name()).addEntry(player.getName());
                    }
                }
            }
        }
    }

    public void removeTag(Player player){
        for (Player target : Bukkit.getOnlinePlayers()){
            for (Rank rank : Rank.values()){
                if (rank.name().equalsIgnoreCase(customePlayer.getRank())){
                    target.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
                }
            }
        }

    }

}
