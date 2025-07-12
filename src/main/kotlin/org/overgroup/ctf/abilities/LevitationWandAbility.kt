package org.overgroup.ctf.abilities

import org.bukkit.Sound
import org.bukkit.Particle
import org.bukkit.entity.Player
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

val LEVITATION_WAND = "Levitation Wand"

fun levitationWandAbility(player: Player, item: ItemStack) {
    levitationWandLaunch(player)

    player.addScoreboardTag("IgnoreNextFallDamage")
    player.setCooldown(item, 100)
}

fun levitationWandLaunch(entity: LivingEntity) {
    entity.world.playSound(entity.location, Sound.ENTITY_SHULKER_SHOOT, 1.0f, 1.0f)

    entity.addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, 20, 10))
    entity.world.spawnParticle(Particle.CLOUD, entity.location, 50, 0.1, 0.1, 0.1, 0.05)
}
