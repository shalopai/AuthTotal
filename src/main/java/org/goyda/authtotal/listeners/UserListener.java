package org.goyda.authtotal.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.goyda.authtotal.AuthTotal;
import org.goyda.authtotal.models.User;

import javax.persistence.EntityManager;


public class UserListener implements Listener {

    private final EntityManager entityManager = AuthTotal.getEntityManager();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        User user = entityManager.find(User.class, player.getUniqueId());
        if (user != null) {
            if (!user.isLoginned()) player.sendMessage("/login <password>");
            return;
        }
        player.sendMessage("/register <password> <password>");
    }
}
