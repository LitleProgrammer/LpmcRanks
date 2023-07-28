package de.littleprogrammer.lpmcranks;


import org.bukkit.ChatColor;

public enum Rank {

    ADMIN(ChatColor.DARK_RED + "Admin" + ChatColor.GRAY + " | " + ChatColor.DARK_RED, new String[]{"lpmcranks.admin", "lpmcranks.yt", "lpmcranks.supporter", "lpmcranks.player", "cloudnet.syncproxy.maintenance", "bungeecord.*", "cloudnet.chat.color", "cloudnet.command.cloud.*", "cloudnet.command.cloudsign", "cloudnet.command.cloudnpc", "cloudnet.syncproxy.fulljoin", "cloudnet.syncproxy.notify", "cloudnet.bridge.maintenance", "cloudnet.signs.knockback.bypass", "cloudnet.npcs.knockback.bypass", "bungeecord.command.server", "bungeecord.command.list", "bungeecord.command.alert", "bungeecord.command.end", "bungeecord.command.ip", "bungeecord.command.reload", "cloudnet.command.cloudnet", "cloudnet.syncproxy.fulljoin", "cloudnet.syncproxy.maintenance"}),
    MOD(ChatColor.RED + "Mod" + ChatColor.GRAY + " | " + ChatColor.RED, new String[]{"lpmcranks.mod", "lpmcranks.yt", "lpmcranks.supporter", "lpmcranks.player", "cloudnet.syncproxy.maintenance", "cloudnet.syncproxy.fulljoin"}),
    CONTENT(ChatColor.RED + "Content" + ChatColor.GRAY + " | " + ChatColor.RED, new String[]{"lpmcranks.content", "lpmcranks.yt", "lpmcranks.supporter", "lpmcranks.player", "cloudnet.syncproxy.fulljoin"}),
    DEVELOPER(ChatColor.BLUE + "Dev" + ChatColor.GRAY + " | " + ChatColor.BLUE, new String[]{"lpmcranks.dev", "lpmcranks.yt", "lpmcranks.supporter", "lpmcranks.player", "cloudnet.syncproxy.fulljoin", "cloudnet.syncproxy.maintenance", "cloudnet.syncproxy.notify", "cloudnet.bridge.maintenance"}),
    YOUTUBER(ChatColor.DARK_PURPLE + "Youtuber" + ChatColor.GRAY + " | " + ChatColor.DARK_PURPLE, new String[]{"lpmcranks.yt", "lpmcranks.supporter", "lpmcranks.player", "cloudnet.syncproxy.fulljoin"}),
    SUPPORTER(ChatColor.AQUA + "Supporter" + ChatColor.GRAY + " | " + ChatColor.AQUA, new String[]{"lpmcranks.supporter", "lpmcranks.player", "cloudnet.chat.color", "cloudnet.syncproxy.fulljoin"}),
    SPIELER(ChatColor.GREEN + "Spieler" + ChatColor.GRAY + " | " + ChatColor.GREEN, new String[]{"lpmcranks.player"});

    private String display;
    private String [] permissions;

    Rank(String display, String[] permissions){
        this.display = display;
        this.permissions = permissions;


    }

    public String getDisplay() {
        return display;
    }

    public String[] getPermissions() {
        return permissions;
    }
}
