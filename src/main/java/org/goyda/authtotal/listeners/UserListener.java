package org.goyda.authtotal.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;
import org.bukkit.permissions.PermissionAttachment;
import org.goyda.authtotal.AuthTotal;
import org.goyda.authtotal.commands.utils.Messages;
import org.goyda.authtotal.models.User;
import org.goyda.authtotal.repositories.UserDAO;
import java.util.List;


import javax.persistence.EntityManager;


public class UserListener implements Listener {
    private final EntityManager entityManager = AuthTotal.getEntityManager();
    private final UserDAO userDAO = new UserDAO();
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        User user = entityManager.find(User.class, player.getUniqueId());
        if (user != null) {
            if (!user.isLoginned()) player.sendMessage("/login <password>");
            else {
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
    @EventHandler
    public void onBed(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("authtotal.player.authorized")) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("authtotal.player.authorized")) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        List<String> allowedcommands = List.of("/l", "/login", "/reg", "/register", "/log", "/r");
        if (allowedcommands.contains(message.split(" ")[0])) return;
        event.setCancelled(true);
        player.sendMessage(Messages.NOT_LOGIN);
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("authtotal.player.authorized")) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void onItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("authtotal.player.authorized")) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("authtotal.player.authorized")) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Player player = (Player) event.getEntity();
        if (player.hasPermission("authtotal.player.authorized")) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void onEntityDropItem(EntityDropItemEvent event){
        Player player = (Player) event.getEntity();
        if (player.hasPermission("authtotal.player.authorized")) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void onSwapHandItem(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("authtotal.player.authorized")) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("authtotal.player.authorized")) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void onInvnentoryOpen(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        if (player.hasPermission("authtotal.player.authorized")) return;
        event.setCancelled(true);
    }
}