package org.overgroup.ctf.abilities

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.overgroup.ctf.App
import java.util.UUID

object ShadowstepAbility : SelfRegisteringAbility("Shadowstep") {
    private val markedLocations = mutableMapOf<UUID, Location>()

    override fun use(player: Player, item: ItemStack) {
        markedLocations[player.uniqueId] = player.location

        player.world.playSound(player.location, Sound.ENTITY_BAT_TAKEOFF, 1.0f, 1.0f)
        player.setCooldown(item, 60)

        Bukkit.getScheduler().runTaskLater(App.getInstance(), Runnable {
            val returnLocation = markedLocations.remove(player.uniqueId)
            if (returnLocation != null) {
                player.world.playSound(player.location, Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.0f)
                player.teleport(returnLocation)
                player.world.playSound(returnLocation.add(0.0, 0.5, 0.0), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.0f)
                player.world.spawnParticle(Particle.DRAGON_BREATH, returnLocation, 50, 0.5, 0.5, 0.5, 0.1)
            }
        }, 60)
    }
}