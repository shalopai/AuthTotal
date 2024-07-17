package org.goyda.authtotal.commands.utils;

import org.bukkit.ChatColor;

public class Messages {
    public static final String prefix =  ChatColor.BOLD + "" + ChatColor.GOLD + "T" + ChatColor.WHITE + "M"
                                        + ChatColor.DARK_GRAY + " >" + ChatColor.RESET + "" + ChatColor.GRAY;
    public static final String REG_COMMAND = prefix + "Используйте кмоанду /register <пароль> <пароль>";
    public static final String ALREADY_REGISTERED = prefix + " Вы уже зарегистрированы!";
    public static final String REGISTERED_SUCCESSFULLY = prefix + "Вы успешно зарегистрированы!";
    public static final String NOT_REGISTERED = prefix + "Вы не зарегистрированы!";
    public static final String NOT_LOGIN = prefix + "Вы не вошли!";
    public static final String ONLY_USERS = prefix + "Только игроки могут пользоваться этой командой!";
    public static final String PASSWORDS_DOES_NOT_MATCH = prefix + "Пароли не совпадают!";
    public static final String ALREADY_LOGINED = prefix + "Вы уже вошли!";
    public static final String LOGIN_COMMAND = prefix + "Используйте команду /login <пароль>";
}
