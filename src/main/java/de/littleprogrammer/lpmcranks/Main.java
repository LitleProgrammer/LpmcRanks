package de.littleprogrammer.lpmcranks;

import de.littleprogrammer.lpmcranks.commands.DatabaseCommand;
import de.littleprogrammer.lpmcranks.commands.RankCommand;
import de.littleprogrammer.lpmcranks.files.DatabaseConfig;
import de.littleprogrammer.lpmcranks.listeners.ChatListener;
import de.littleprogrammer.lpmcranks.listeners.ConnectionListener;
import de.littleprogrammer.lpmcranks.managers.NametagManager;
import de.littleprogrammer.lpmcranks.tabCompleters.RankTab;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Main extends JavaPlugin {

    private static Main instance;
    private DatabaseConfig databaseConfig;
    private Database database;
    private NametagManager nametagManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
         instance = this;

         //Listeners
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new ConnectionListener(), this);
        pluginManager.registerEvents(new ChatListener(), this);


         //Commands
        getCommand("rank").setExecutor(new RankCommand());
        getCommand("db").setExecutor(new DatabaseCommand());

        //TabComplete
        getCommand("rank").setTabCompleter(new RankTab());

        //Config
        databaseConfig = new DatabaseConfig();
        databaseConfig.createFile();

        //Database newConnect runnable


        //Database
        database = new Database();

        //Nametags
        nametagManager = new NametagManager();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        database.disconnect();
    }

    //Getters
    public DatabaseConfig getDatabaseConfig() {
        return databaseConfig;
    }

    public static Main getInstance() {
        return instance;
    }

    public Database getDatabase() {
        return database;
    }

    public NametagManager getNametagManager() {
        return nametagManager;
    }
}
