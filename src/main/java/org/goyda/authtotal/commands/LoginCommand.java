package org.goyda.authtotal.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import org.goyda.authtotal.AuthTotal;
import org.goyda.authtotal.commands.utils.AbstractCommand;
import org.goyda.authtotal.commands.utils.Messages;
import org.goyda.authtotal.commands.utils.UserCheck;
import org.goyda.authtotal.models.User;
import org.goyda.authtotal.repositories.UserDAO;
import org.jetbrains.annotations.NotNull;

import javax.swing.plaf.SplitPaneUI;
import java.util.Date;
import java.util.UUID;

public class LoginCommand extends AbstractCommand {
    public LoginCommand() {
        super("login");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = playerRequired(sender);
        if (args.length < 1) {
            sender.sendMessage(Messages.LOGIN_COMMAND);
            return;
        }
        User user = AuthTotal.getEntityManager().find(User.class, player.getUniqueId());
        UserCheck userCheck = new UserCheck(user, sender);

        if (!userCheck.loginable(args[0])) return;

        user.setLoginned(true);
        user.setLastLogin(new Date());
        new UserDAO().update(user);
        PermissionAttachment attachment = player.addAttachment(AuthTotal.getInstance());
        attachment.setPermission("authtotal.player.authorized",true);
    }

}
