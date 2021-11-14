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
        alertConsole(target.getDisplayName());
    }

    private void alertConsole(String player){
        if (main.getConfig().getBoolean("alertConsoleOnJump") && main.getConfig().getString("consoleMsg") != null) {
            Bukkit.getConsoleSender().sendMessage(main.getConfig().getString("consoleMsg").replace("{player}", player));
        }
    }

}
