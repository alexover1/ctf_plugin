package org.overgroup.ctf.listeners

import org.bukkit.GameMode
import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerDropItemEvent

class PlayerDropItemListener : Listener {
    @EventHandler
    fun onPlayerDropItem(event: PlayerDropItemEvent) {
        if (event.player.gameMode == GameMode.ADVENTURE) {
            event.isCancelled = true
        }
    }
}