package org.overgroup.ctf.abilities

import org.bukkit.Bukkit
import org.bukkit.DyeColor
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.entity.Wolf
import org.bukkit.inventory.ItemStack
import org.overgroup.ctf.App

object WolfHornAbility : SelfRegisteringAbility("Wolf Horn") {
    override fun use(player: Player, item: ItemStack) {
        val wolf = player.world.spawn(player.location, Wolf::class.java)
        wolf.owner = player
        wolf.variant = Wolf.Variant.BLACK
        wolf.collarColor = DyeColor.BLACK
        wolf.clearLootTable()

        player.scoreboard.getEntityTeam(player)?.addEntity(wolf)

        player.setCooldown(item, 40)

        Bukkit.getScheduler().runTaskLater(App.getInstance(), Runnable {
            wolf.world.playSound(wolf.location, Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 1.0f)
            wolf.world.spawnParticle(Particle.LARGE_SMOKE, wolf.location, 10, 0.0, 0.1, 0.0, 0.05)

            wolf.remove()
        }, 300)
    }
}