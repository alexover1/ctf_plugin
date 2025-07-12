package org.overgroup.ctf.abilities

import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable
import org.overgroup.ctf.App
import java.util.UUID

private val markedLocations = mutableMapOf<UUID, Location>()

fun shadowStepAbility(player: Player, item: ItemStack) {
    markedLocations[player.uniqueId] = player.location

    player.world.playSound(player.location, Sound.ENTITY_BAT_TAKEOFF, 1.0f, 1.0f)
    player.setCooldown(item, 60)

    ShadowStepTick(player).runTaskTimer(App.getInstance(), 0, 1)
}

class ShadowStepTick(val player: Player) : BukkitRunnable() {
    var ticks = 0
    val duration: Int = 60

    override fun run() {
        val returnLocation = markedLocations[player.uniqueId]
        if (!player.isOnline || returnLocation == null) {
            cancel()
            return
        }

        if (++ticks >= duration) {
            markedLocations.remove(player.uniqueId)

            player.world.playSound(player.location, Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.0f)
            player.teleport(returnLocation)
            player.world.playSound(returnLocation.add(0.0, 0.5, 0.0), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.0f)
            player.world.spawnParticle(Particle.DRAGON_BREATH, returnLocation, 50, 0.5, 0.5, 0.5, 0.1)

            cancel()
        } else {
            player.spawnParticle(Particle.LARGE_SMOKE, returnLocation, 1, 0.0, 0.1, 0.0, 0.05)
        }
    }
}
