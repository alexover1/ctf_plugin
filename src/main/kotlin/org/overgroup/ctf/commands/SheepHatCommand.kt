package org.overgroup.ctf.commands

import org.bukkit.entity.Player
import org.bukkit.entity.Sheep

fun sheepHat(player: Player) {
    val sheep = player.world.spawn(player.location, Sheep::class.java)
    val lastPassenger = generateSequence(player.passengers.lastOrNull()) { it.passengers.lastOrNull() }.lastOrNull()
    val addTo = lastPassenger ?: player
    addTo.addPassenger(sheep)
}

