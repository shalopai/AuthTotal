package org.goyda.authtotal.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.goyda.authtotal.AuthTotal;
import org.goyda.authtotal.commands.utils.AbstractCommand;
import org.goyda.authtotal.commands.utils.Messages;
import org.goyda.authtotal.models.User;
import org.goyda.authtotal.repositories.UserDAO;

import java.util.Date;

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
        if (user == null) {
            sender.sendMessage(Messages.NOT_REGISTERED);
            sender.sendMessage(Messages.REG_COMMAND);
            return;
        }

        if (user.isLoginned()) {
            sender.sendMessage(Messages.ALREADY_LOGINED);
            return;
        }

        if (!user.checkPassword(args[0])) {
            sender.sendMessage(Messages.PASSWORDS_DOES_NOT_MATCH);
            return;
        }
        user.setLoginned(true);
        user.setLastLogin(new Date());
        new UserDAO().update(user);
        sender.sendMessage("Вы успешно вошли");
        PermissionAttachment attachment = player.addAttachment(AuthTotal.getInstance());
        attachment.setPermission("authtotal.player.authorized", true);
    }

}
