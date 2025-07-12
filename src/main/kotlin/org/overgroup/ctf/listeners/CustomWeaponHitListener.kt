package org.overgroup.ctf.listeners

import org.bukkit.event.Listener
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffectType
import org.overgroup.ctf.abilities.*

class CustomWeaponHitListener : Listener {
    @EventHandler
    fun onEntityDamageByEntity(event: EntityDamageByEntityEvent) {
        val entity = event.entity as? LivingEntity ?: return
        if (entity.hasPotionEffect(PotionEffectType.LEVITATION)) return
        val damager = event.damager as? LivingEntity ?: return
        val usedItem = damager.equipment?.itemInMainHand ?: return

        val itemName = usedItem.getItemNameAsString()
        if (abilities.containsKey(itemName)) {
            when (itemName) {
                LEVITATION_WAND -> {
                    levitationWandLaunch(entity)
                }
            }
        }
    }
}
