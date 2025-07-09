package org.overgroup.ctf.listeners

import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerInteractEvent
import org.overgroup.ctf.abilities.ItemAbilityRegistry

class ItemUseListener : Listener {
    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val item = event.item ?: return
        if (event.action.isRightClick) {
            event.clickedBlock?.let {
                if (it.type.hasBlockInteraction()) {
                    return
                }
            }
            if (!player.hasCooldown(item)) {
                item.getItemNameAsString()?.let { ItemAbilityRegistry.get(it)?.use(player, item) }
            }
        }
    }
}
