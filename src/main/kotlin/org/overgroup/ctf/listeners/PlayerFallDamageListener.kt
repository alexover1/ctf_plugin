package org.overgroup.ctf.listeners

import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageEvent

class PlayerFallDamageListener : Listener {
    @EventHandler
    fun onPlayerFallDamage(event: EntityDamageEvent) {
        val player = event.entity as? Player ?: return
        if (event.cause == EntityDamageEvent.DamageCause.FALL) {
            if (player.scoreboardTags.remove("IgnoreNextFallDamage")) {
                event.isCancelled = true
            }
        }
    }
}
