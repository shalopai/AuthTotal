package org.goyda.authtotal.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.permissions.PermissionAttachment;
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
            else {
                if (user.loginUnavailable()) {
                    user.setLoginned(false);
                    return;
                }
                PermissionAttachment attachment = player.addAttachment(AuthTotal.getInstance());
                attachment.setPermission("authtotal.player.authorized", true);
            }
            return;
        }
        player.sendMessage("/register <password> <password>");

    }



    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("authtotal.player.authorized")) return;
        event.setCancelled(true);

    }
}
