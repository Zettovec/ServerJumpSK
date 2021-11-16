package ga.nikd0.ServerJumpSK.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandServerJump implements CommandExecutor {

    public CommandServerJump(){

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {

        } else if (sender instanceof Player) {
            Player player = (Player) sender;
            
        }
        return false;
    }
}
