package de.littleprogrammer.lpmcranks.commands;

import de.littleprogrammer.lpmcranks.Database;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DatabaseCommand implements CommandExecutor {

    private Database database;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        database = new Database();
        if (database.isConnected()){
            sender.sendMessage(ChatColor.GREEN + "Database is connected = true" );
        }else {
            sender.sendMessage(ChatColor.RED + "Database is connected = false");
        }
        return false;
    }

}
