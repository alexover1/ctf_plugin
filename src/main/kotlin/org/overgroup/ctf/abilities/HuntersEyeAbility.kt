package org.overgroup.ctf.abilities

import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object HuntersEyeAbility : SelfRegisteringAbility("Hunter's Eye") {
    override fun use(player: Player, item: ItemStack) {
        val entities = player.world.getNearbyLivingEntities(player.location, 50.0)
        entities.remove(player)
        entities.forEach { it.addPotionEffect(PotionEffect(PotionEffectType.GLOWING, 200, 0)) }

        player.world.playSound(player.location, Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 1.0f)
        player.world.spawnParticle(Particle.INSTANT_EFFECT, player.location, 10, 0.0, 0.0, 0.0, 1.0)
        player.setCooldown(item, 400)
    }
}
