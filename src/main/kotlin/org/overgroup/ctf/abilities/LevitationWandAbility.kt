package org.overgroup.ctf.abilities

import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object LevitationWandAbility : SelfRegisteringAbility("Levitation Wand") {
    override fun use(player: Player, item: ItemStack) {
        player.world.playSound(player.location, Sound.ENTITY_SHULKER_SHOOT, 1.0f, 1.0f)

        player.addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, 20, 10))
        player.spawnParticle(Particle.CLOUD, player.location, 50, 0.1, 0.1, 0.1, 0.05)

        player.addScoreboardTag("IgnoreNextFallDamage")
        player.setCooldown(item, 100)
    }
}
