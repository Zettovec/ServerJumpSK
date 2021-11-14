package ga.nikd0.ServerJumpSK;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerJumper {

    Plugin main;

    public PlayerJumper(){
        this.main = ServerJumpSK.getInstance();
    }

    public void jumpPlayer(Player target, String server) {
        ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();
        dataOutput.writeUTF("Connect");
        dataOutput.writeUTF(server);
        target.sendPluginMessage(main, "BungeeCord", dataOutput.toByteArray());
        alertAboutJump(target, server);
    }

    private void alertAboutJump(Player player, String server){
        if (main.getConfig().getString("configVersion") != "1.1"){
            Bukkit.getConsoleSender().sendMessage("§9[§bServerJumpSK§9] §cYour config file is outdated. Please, remove it and reload the plugin.");
        }
        if (main.getConfig().getBoolean("alertConsoleOnJump") && main.getConfig().getString("consoleMsg") != null) {
            String CONSOLE_ALERT = main.getConfig().getString("consoleMsg").replace("{player}", player.getDisplayName()).replace("{server}", server);
            Bukkit.getConsoleSender().sendMessage(CONSOLE_ALERT);
        }
        if (main.getConfig().getBoolean("alertPlayerOnJump") && main.getConfig().getString("playerMsg") != null) {
            String PLAYER_ALERT = main.getConfig().getString("playerMsg").replace("{player}", player.getDisplayName()).replace("{server}", server);
            player.sendMessage(PLAYER_ALERT);
        }
    }

}
