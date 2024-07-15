package org.goyda.authtotal.commands.utils;

import org.bukkit.command.CommandSender;
import org.goyda.authtotal.models.User;

public class UserCheck {

    private final User user;
    private final CommandSender sender;

    public UserCheck(User user, CommandSender sender) {
        this.user = user;
        this.sender = sender;
    }

    public boolean loginable(String password) {
        if (!isRegistered()) return false;
        if (isLoginned()) return false;
        return passwordsMatches(password);
    }

    private boolean isLoginned() {
        if (user.isLoginned()) {
            sender.sendMessage(Messages.ALREADY_LOGINED);
            return true;
        }
        sender.sendMessage("Вы не вошли");
        return false;
    }

    private boolean isRegistered() {
        if (user == null) {
            sender.sendMessage(Messages.NOT_REGISTERED);
            sender.sendMessage(Messages.REG_COMMAND);
            return false;
        }
        return true;
    }
    private boolean passwordsMatches(String password) {
        if (!user.checkPassword(password)) {
            sender.sendMessage(Messages.PASSWORDS_DOES_NOT_MATCH);
            return false;
        }
        return true;
    }

}
