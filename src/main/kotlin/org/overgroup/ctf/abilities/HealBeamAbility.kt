package org.overgroup.ctf.abilities

import org.bukkit.Color
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.AreaEffectCloud
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

fun healBeamAbility(player: Player, item: ItemStack) {
    val ray = player.rayTraceBlocks(64.0)
    val hitBlock = ray?.hitBlock
    if (hitBlock != null) {
        val targetLocation = hitBlock.location.add(0.0, 1.0, 0.0)
        val effectCloud = player.world.spawn(targetLocation, AreaEffectCloud::class.java)
        effectCloud.addCustomEffect(PotionEffect(PotionEffectType.REGENERATION, 100, 1), true)
        effectCloud.color = Color.RED
        effectCloud.duration = 200
        effectCloud.durationOnUse = -20

        player.world.spawnParticle(Particle.INSTANT_EFFECT, targetLocation, 10, 0.0, 0.0, 0.0, 1.0)

        player.world.playSound(player.location, Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 1.0f)
        player.world.spawnParticle(Particle.WITCH, player.location, 50, 0.1, 0.1, 0.1, 0.05)
        player.setCooldown(item, 300)
    }
}
