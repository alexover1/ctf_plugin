package org.overgroup.ctf.listeners

import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.potion.PotionEffectType
import org.overgroup.ctf.abilities.LevitationWandAbility

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

    @EventHandler
    fun onEntityHitByLevitationWand(event: EntityDamageByEntityEvent) {
        val entity = event.entity as? LivingEntity ?: return
        if (entity.hasPotionEffect(PotionEffectType.LEVITATION)) return
        val damager = event.damager as? LivingEntity ?: return
        val usedItem = damager.equipment?.itemInMainHand ?: return
        if (LevitationWandAbility.matches(usedItem)) {
            LevitationWandAbility.launch(entity)
        }
    }
}
