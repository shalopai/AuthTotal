package org.goyda.authtotal.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.goyda.authtotal.AuthTotal;
import org.goyda.authtotal.commands.utils.AbstractCommand;
import org.goyda.authtotal.commands.utils.Messages;
import org.goyda.authtotal.models.User;
import org.goyda.authtotal.repositories.UserDAO;

import java.util.Date;

public class RegisterCommand extends AbstractCommand {
    public RegisterCommand() {
        super("register");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) return;
        Player player = playerRequired(sender);
        if (args.length < 2) {
            sender.sendMessage(Messages.REG_COMMAND);
            return;
        }
        if (!args[0].equals(args[1])) {
            sender.sendMessage(Messages.PASSWORDS_DOES_NOT_MATCH);
            sender.sendMessage(Messages.REG_COMMAND);
            return;
        }
        UserDAO dao = new UserDAO();
        User user = AuthTotal.getEntityManager().find(User.class, player.getUniqueId());
        if (user != null) {
            sender.sendMessage(Messages.ALREADY_REGISTERED);
            if (!user.isLoginned()) sender.sendMessage(Messages.LOGIN_COMMAND);
            return;
        }
        user = new User();
        user.setName(player.getName());
        user.setId(player.getUniqueId());
        user.setPassword(args[0]);
        user.setRegistrationDate(new Date());
        dao.insert(user);
        sender.sendMessage(Messages.REGISTERED_SUCCESSFULLY);
        sender.sendMessage(Messages.LOGIN_COMMAND);
    }
}
