package ga.nikd0.ServerJumpSK;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import ga.nikd0.ServerJumpSK.commands.CommandServerJump;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.IOException;

public class ServerJumpSK extends JavaPlugin implements PluginMessageListener {

    private static ServerJumpSK instance;
    private static SkriptAddon addon;
    FileConfiguration config = getConfig();

    public ServerJumpSK() {
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void onEnable(){
        tellConsole("§9[§bServerJumpSK§9] §bLoading ServerJumpSK v0.2.1 by Nikd0. Let's start jumping!");
        instance = this;
        addon = Skript.registerAddon(this);
        config.options().header("ServerJumpSK Skript Addon Config\n\nUse {player} and {server} in your messages.\nDO NOT CHANGE THE CONFIG VERSION.");
        config.addDefault("configVersion", "0.2.1");
        config.addDefault("disableSJSKCommand", false);
        config.addDefault("consoleMsg", "[!] Sending player {player} to server {server}.");
        config.addDefault("alertConsoleOnJump", true);
        config.addDefault("playerMsg", "[!] Sending you to server {server}...");
        config.addDefault("alertPlayerOnJump", true);
        config.options().copyDefaults(true);
        saveConfig();
        this.getCommand("serverjumpsk").setExecutor(new CommandServerJump());
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        try {
            addon.loadClasses("ga.nikd0.ServerJumpSK", "elements");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!config.getString("configVersion").equals("0.2.1")){
            Bukkit.getConsoleSender().sendMessage("§9[§bServerJumpSK§9] §cYour config file is outdated. Please, remove it and reload the plugin.");
        }
    }

    @Override
    public void onDisable(){
        tellConsole("§9[§bServerJumpSK§9] §bPlugin disabled.");
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
    }

    public static ServerJumpSK getInstance() {
        if (instance == null) {
            throw new IllegalStateException();
        }
        return instance;
    }

    public static SkriptAddon getAddonInstance() {
        return addon;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("RandomSubChannel")) {
            // Currently not used
        }
    }

    public void tellConsole(String message){
        Bukkit.getConsoleSender().sendMessage(message);
    }

}

