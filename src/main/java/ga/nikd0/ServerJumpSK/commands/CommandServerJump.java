package ga.nikd0.ServerJumpSK.commands;

import ga.nikd0.ServerJumpSK.PlayerJumper;
import ga.nikd0.ServerJumpSK.ServerJumpSK;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CommandServerJump implements CommandExecutor {

    Plugin main;

    public CommandServerJump(){
        this.main = ServerJumpSK.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            if (args.length == 0) {
                Bukkit.getConsoleSender().sendMessage("§9[§bServerJumpSK§9] §bUse §f/sjsk help§b to get a list of possible commands.");
            } else if (args[0].equalsIgnoreCase("configreload") && args.length == 1) {
                reloadPluginConfig();
            } else if (args[0].equalsIgnoreCase("jump")) {
                if (args.length != 3){
                    Bukkit.getConsoleSender().sendMessage("§9[§bServerJumpSK§9] §cUse §f/sjsk jump <player> <server>§c!");
                    return true;
                }

                jumpPlayer(args[1], args[2]);

            } else if (args[0].equalsIgnoreCase("help")) {
                showCommandHelp();
            } else {
                Bukkit.getConsoleSender().sendMessage("§9[§bServerJumpSK§9] §cUnrecognized or wrongly used command.");
            }
            return true;

        } else if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!canUseCommand(player)){
                return true;
            }

            if (args.length == 0) {
                player.sendMessage("§9[§bServerJumpSK§9] §bUse §f/sjsk help§b to get a list of possible commands.");
            } else if (args[0].equalsIgnoreCase("configreload") && args.length == 1){
                reloadPluginConfig(player);
            } else if (args[0].equalsIgnoreCase("jump")) {
                player.sendMessage("§9[§bServerJumpSK§9] §cThis command may only be executed from console.");

            } else if (args[0].equalsIgnoreCase("help")) {
                showCommandHelp(player);
            } else {
                player.sendMessage("§9[§bServerJumpSK§9] §cUnrecognized or wrongly used command.");
            }
            return true;

        } else {
            return false;
        }
    }

    private void reloadPluginConfig(Player player){
        main.reloadConfig();
        player.sendMessage("§9[§bServerJumpSK§9] §bConfig successfully reloaded.");
    }

    private void reloadPluginConfig(){
        main.reloadConfig();
        Bukkit.getConsoleSender().sendMessage("§9[§bServerJumpSK§9] §bConfig successfully reloaded.");
    }

    private boolean canUseCommand(Player player){
        if (player.hasPermission("serverjumpsk.admin")) {
            return true;
        } else {
            player.sendMessage("§9[§bServerJumpSK§9] §cYou don't have permission to use this command.");
            return false;
        }
    }

    private void jumpPlayer(String player, String server){
        Player target = Bukkit.getPlayer(player);
        if (target != null) {
            PlayerJumper pj = new PlayerJumper();
            pj.jumpPlayer(target, server);
        } else {
            Bukkit.getConsoleSender().sendMessage("§9[§bServerJumpSK§9] §cSpecified player is not online!");
        }
    }

    private void showCommandHelp(){
        Bukkit.getConsoleSender().sendMessage("§1===== §9[§bServerJumpSK§9] §1=====");
        Bukkit.getConsoleSender().sendMessage("§f/sjsk configreload §b- Reload plugin config");
        Bukkit.getConsoleSender().sendMessage("§f/sjsk jump <player> <server> §b- Jump player to another server (console only)");
        Bukkit.getConsoleSender().sendMessage("§1==================================");
    }

    private void showCommandHelp(Player player){
        player.sendMessage("§1===== §9[§bServerJumpSK§9] §1=====");
        player.sendMessage("§f/sjsk configreload §b- Reload plugin config");
        player.sendMessage("§f/sjsk jump <player> <server> §b- Jump player to another server (console only)");
        player.sendMessage("§1==================================");
    }
}
