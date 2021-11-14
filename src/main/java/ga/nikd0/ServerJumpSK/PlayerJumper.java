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
        Bukkit.getConsoleSender().sendMessage("[!] Sending player " + target.toString() + " between servers.");
    }

}
