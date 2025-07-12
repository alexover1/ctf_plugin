package org.overgroup.ctf.commands

import org.bukkit.entity.Player

fun dropSheep(player: Player) {
    val lastPassenger = generateSequence(player.passengers.lastOrNull()) { it.passengers.lastOrNull() }.lastOrNull()
    if (lastPassenger != null) {
        player.removePassenger(lastPassenger)
    }
}
