package org.overgroup.ctf.abilities

import org.bukkit.Sound
import org.bukkit.entity.Fireball
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun fireWandAbility(player: Player, item: ItemStack) {
    player.world.playSound(player.location, Sound.ENTITY_BLAZE_SHOOT, 1.0f, 1.0f)

    val fireball = player.launchProjectile(Fireball::class.java, player.location.direction)
    fireball.yield = 0.0f

    player.setCooldown(item, 35)
}
