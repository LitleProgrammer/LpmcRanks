package de.littleprogrammer.lpmcranks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CustomePlayer {

    private UUID uuid;
    private String rank;
    private byte mute;

    private HashMap<UUID, PermissionAttachment> perms = new HashMap<>();
    private List<String> playersInDB = new ArrayList<>();


    public CustomePlayer(UUID uuid) throws SQLException {
        this.uuid = uuid;

        Main.getInstance().getDatabase().connect();

        PreparedStatement statement = null;
        try {
            statement = Main.getInstance().getDatabase().getConnection().prepareStatement("SELECT RANK, MUTE FROM players WHERE UUID = ?;");
            statement.setString(1, uuid.toString());
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                rank = rs.getString("RANK");
                mute = rs.getByte("MUTE");
            }else {
                rank = "SPIELER";
                mute = 0;
                PreparedStatement statement1 = Main.getInstance().getDatabase().getConnection().prepareStatement("INSERT INTO players (ID, UUID, RANK, MUTE) VALUES (" +
                        "default," + "'" + uuid + "'," + "'" + rank + "'," + "'" + mute + "'" + ");");
                statement1.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //AllPlayers
        PreparedStatement statement1 = null;

        statement1 = Main.getInstance().getDatabase().getConnection().prepareStatement("SELECT UUID FROM players");

        ResultSet rs1 = statement1.executeQuery();
        while (rs1.next()){
            playersInDB.add(rs1.getString("UUID"));
        }

        Main.getInstance().getDatabase().disconnect();
    }

    public String getRank(){
        return rank;
    }

    public void setRank(String rank) throws SQLException {
        this.rank = rank;

        Main.getInstance().getDatabase().connect();

        if (Bukkit.getOfflinePlayer(uuid).isOnline()){
            Player player = Bukkit.getPlayer(uuid);
            PermissionAttachment attachment;
            if (perms.containsKey(uuid)){
                attachment = perms.get(uuid);
            }else {
                attachment = player.addAttachment(Main.getInstance());
                perms.put(uuid, attachment);
            }
            for (String perm : Rank.valueOf(getRank()).getPermissions()){
                if (player.hasPermission(perm)){
                    attachment.unsetPermission(perm);
                }
            }

            for (String perm : Rank.valueOf(rank).getPermissions()){
                attachment.setPermission(perm, true);
                player.sendMessage(perm);
            }
        }

         try {
             PreparedStatement statement = Main.getInstance().getDatabase().getConnection().prepareStatement("UPDATE players SET RANK = '" + rank + "' WHERE UUID = '" + uuid + "';");
             statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
         Main.getInstance().getDatabase().disconnect();
    }

    public byte getMute() {
        return mute;
    }

    public HashMap<UUID, PermissionAttachment> getPerms() {
        return perms;
    }

    public List<String> getPlayersInDB() {
        return playersInDB;
    }
}
