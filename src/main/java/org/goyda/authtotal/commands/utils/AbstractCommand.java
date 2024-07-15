package org.goyda.authtotal.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.goyda.authtotal.AuthTotal;
import org.jetbrains.annotations.NotNull;


public abstract class AbstractCommand implements CommandExecutor {


    public AbstractCommand(String command) {
        PluginCommand pluginCommand = AuthTotal.getInstance().getCommand(command);
        if (pluginCommand != null) {
            pluginCommand.setExecutor(this);
        }
    }

    abstract public void execute(CommandSender sender, String[] args);


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        execute(sender, args);
        return true;
    }

    protected Player playerRequired(CommandSender sender) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.ONLY_USERS);
            return null;
        }
        return player;
    }

}
