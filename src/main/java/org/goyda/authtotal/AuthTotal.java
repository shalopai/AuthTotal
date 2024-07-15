package org.goyda.authtotal;

import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.goyda.authtotal.commands.LoginCommand;
import org.goyda.authtotal.commands.RegisterCommand;
import org.goyda.authtotal.listeners.UserListener;
import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.persistence.EntityManager;
import java.util.Collections;

public final class AuthTotal extends JavaPlugin implements Listener {
    @Getter
    private static AuthTotal instance;

    @Getter
    private static final EntityManager entityManager = new HibernatePersistenceProvider()
            .createEntityManagerFactory("persistence", Collections.emptyMap()).createEntityManager();

    @Override
    public void onEnable() {
        instance = this;
        new LoginCommand();
        new RegisterCommand();
        getServer().getPluginManager().registerEvents(new UserListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
