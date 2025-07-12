package org.overgroup.ctf.listeners

import org.overgroup.ctf.Keys
import org.overgroup.ctf.abilities.*

import org.bukkit.event.Listener
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffectType

class CustomWeaponHitListener : Listener {
    @EventHandler
    fun onEntityDamageByEntity(event: EntityDamageByEntityEvent) {
        val entity = event.entity as? LivingEntity ?: return
        if (entity.hasPotionEffect(PotionEffectType.LEVITATION)) return
        val damager = event.damager as? LivingEntity ?: return
        val usedItem = damager.equipment?.itemInMainHand ?: return

        val meta = usedItem.itemMeta ?: return
        if (meta.persistentDataContainer.has(Keys.LEVITATION_WAND)) {
            levitationWandLaunch(entity)
        }
    }
}
